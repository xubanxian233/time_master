package com.example.team.service;

import com.example.team.dao.PetDAO;
import com.example.team.dao.TeamDAO;
import com.example.team.dao.UserDAO;
import com.example.team.mail.MailSenderInfo;
import com.example.team.mail.SimpleMailSender;
import com.example.team.pojo.Pet;
import com.example.team.pojo.Team;
import com.example.team.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Service(value = "userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    private int id;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RedisService redisService;
    @Autowired
    private PetDAO petDAO;
    @Autowired
    private TeamDAO teamDAO;

    /**
     * verify 验证登录并设置登录
     *
     * @param userName,password 登录的用户名和密码
     * @return 验证结果
     */
    public boolean verify(String userName, String password) {
        User user = userDAO.getByEmail(userName);
        if (user != null && password.equals(user.getPassword())) {
            id = user.getUserId();
            return true;
        }
        return false;
    }

    @Override
    public void quit(int userId) {
        redisService.delete(String.valueOf(userId));
    }

    /**
     * sign 注册用户并为其添加宠物
     *
     * @param user,pet 注册的用户和宠物
     * @return 注册结果
     */
    public boolean sign(User user, Pet pet) {
        String email = user.getEmail();
        String tel = user.getTel();
        String name = user.getName();
        User user_1 = userDAO.getByEmail(email);
        User user_2 = userDAO.getByTel(tel);
        User user_3 = userDAO.getByName(name);
        if (user_1 == null && user_2 == null && user_3 == null) {
            pet.setUser(user);
            petDAO.add(pet);
            user.setPet(pet);
            userDAO.update(user);
            return true;
        }
        return false;
    }

    /**
     * getUserId 登录
     *
     * @param tel,email,name tel/email/name
     * @return 对应的用户Id
     */
    @Override
    public int getUserId(String tel, String email, String name) {
        if (tel != null) {
            if (userDAO.getByTel(tel) != null) {
                return userDAO.getByTel(tel).getUserId();
            }
        }
        if (email != null) {
            if (userDAO.getByEmail(email) != null) {
                return userDAO.getByEmail(email).getUserId();
            }
        }
        if (name != null) {
            if (userDAO.getByName(name) != null) {
                return userDAO.getByName(name).getUserId();
            }
        }
        return 0;
    }

    /**
     * getId 获得登录成功后的UserId
     *
     * @param
     * @return userId
     */
    public int getId() {
        return id;
    }


    /**
     * updateEmail 修改用户信息
     *
     * @param userId,email 修改对应的email以及对应用户的Id
     * @return 修改结果
     */
    @Override
    public boolean updateEmail(int userId, String email) {
        User user = userDAO.getById(userId);
        User user1 = userDAO.getByEmail(email);
        if (user1 != null && !email.equals(user.getEmail())) {
            return false;
        }
        if (email.equals("")) {
            user.setEmail(email);
        }
        userDAO.update(user);
        return true;
    }

    /**
     * updateTel 修改用户信息
     *
     * @param userId,tel 修改对应的tel，以及对应用户的Id
     * @return 修改结果
     */
    @Override
    public boolean updateTel(int userId, String tel) {
        User user = userDAO.getById(userId);
        User user1 = userDAO.getByEmail(tel);
        if (user1 != null && !tel.equals(user.getTel())) {
            return false;
        }
        if (tel.equals("")) {
            user.setTel(tel);
        }
        userDAO.update(user);
        return true;
    }

    /**
     * updateUserName 修改用户信息
     *
     * @param userId,userName 修改对应的userName，以及对应用户的Id
     * @return 修改结果
     */
    @Override
    public boolean updateUserName(int userId, String userName) {
        User user = userDAO.getById(userId);
        User user1 = userDAO.getByEmail(userName);
        if (user1 != null && !userName.equals(user.getName())) {
            return false;
        }
        if (userName.equals("")) {
            user.setName(userName);
        }
        userDAO.update(user);
        return true;
    }

    /**
     * updateUserPassword 修改用户密码
     *
     * @param userId,password 修改对应的userName、email、tel，以及对应用户的Id
     * @return 修改结果
     */
    @Override
    public boolean updateUserPassword(int userId, String password) {
        User user = userDAO.getById(userId);
        if (password.length() >= 6 && password.length() <= 20) {
            user.setPassword(password);
            userDAO.update(user);
            return true;
        }
        return false;
    }

    /**
     * getById 获取User通过Id
     *
     * @param userId
     * @return 对应用户
     */
    @Override
    public User getById(int userId) {
        return userDAO.getById(userId);
    }

    @Override
    public Set<User> getMembers(int teamId) {
        Team team = teamDAO.getByTeamId(teamId);
        if (team != null) {
            return team.getUsers();
        }
        return null;
    }

    @Override
    public Set<Team> getTeams(int userId) {
        User user = userDAO.getById(userId);
        if (user != null) {
            return user.getTeams();
        }
        return null;
    }

    @Override
    public Team joinTeam(int teamId, int userId) {
        Team team = teamDAO.getByTeamId(teamId);
        User user = userDAO.getById(userId);
        if (team != null && user != null) {
            for (User user1 : team.getUsers()) {
                if (user1.getUserId() == userId) {
                    team.setTeamId(-1);
                    return team;
                }
            }
            team.getUsers().add(user);
            teamDAO.update(team);
            return team;
        }
        return null;
    }

    @Override
    public boolean quitTeam(int teamId, int userId) {
        boolean flag = false;
        Team team = teamDAO.getByTeamId(teamId);
        if (team != null) {
            for (User user : team.getUsers()) {
                if (user.getUserId() == userId) {
                    team.getUsers().remove(user);
                    flag = true;
                    break;
                }
            }
            teamDAO.update(team);
            if (team.getUsers().size() == 0) {
                teamDAO.delete(teamId);
            }
        }
        return flag;
    }

}
