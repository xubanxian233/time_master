package com.example.team.controller;

import com.example.team.pojo.Pet;
import com.example.team.pojo.User;
import com.example.team.pojo.UserTodo;
import com.example.team.service.PetService;
import com.example.team.service.RecordService;
import com.example.team.service.UserService;
import com.example.team.service.UserTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private UserTodoService userTodoService;
    @Autowired
    private PetService petService;

    /**
     * login 登录
     *
     * @param param 登录的用户名和密码的map
     * @return List<userTodo> 待办集合
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public List<UserTodo> login(@RequestBody Map<String, Object> param) {
        String userName = param.get("userName").toString();
        String password = param.get("password").toString();
        if (userService.verify(userName, password)) {
            response.setHeader("Access-Control-Expose-Headers", "token");
            response.setHeader("Access-Control-Expose-Headers", "id");
            response.setHeader("token", userService.getToken());
            response.setHeader("id", String.valueOf(userService.getId()));
            return userTodoService.listUserTodo(0, userService.getId());
        }
        return null;
    }
    /**
     * quit 退出登录
     *
     * @param
     * @return 退出结果
     */
    @RequestMapping("/quit")
    @ResponseBody
    public String quit() {
        int userId = Integer.parseInt(request.getHeader("id"));
        userService.quit(userId);
        return "quit-success";
    }

    /**
     * sign 注册
     *
     * @param param 注册的用户信息参数 map
     * @return String 注册结果
     */
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    @ResponseBody
    public String sign(@RequestBody Map<String, Object> param) {
        User user = new User();
        user.setEmail(param.get("email").toString());
        user.setName(param.get("userName").toString());
        user.setTel(param.get("tel").toString());
        user.setPassword(param.get("password").toString());
        user.setSex(param.get("sex").toString());
        user.setCreate(new Date(new java.util.Date().getTime()));
        Pet pet = new Pet();
        pet.setBirth(new Date(new java.util.Date().getTime()));
        pet.setSex(1);
        pet.setName("简时");
        pet.setPetStatusId(1);
        pet.setSkinId(1);
        pet.setLevel(1);
        if (userService.sign(user, pet)) {
            return "sign-succees";
        }
        return "sign-fail";
    }

    /**
     * updateInfo 修改信息
     *
     * @param param 修改的用户信息参数 map
     * @return String 修改结果
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    @ResponseBody
    public String updateInfo(@RequestBody Map<String, Object> param) {
        int userId = Integer.parseInt(request.getHeader("id"));
        String userName = param.get("userName").toString();
        String tel = param.get("tel").toString();
        String email = param.get("email").toString();
        if (userService.updateUserInfo(userId, userName, email, tel)) {
            return "update-success";
        }
        return "update-fail";
    }

    /**
     * updatePassword 修改密码
     *
     * @param param 修改的密码参数 map
     * @return String 修改结果
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(@RequestBody Map<String, Object> param) {
        int userId = Integer.parseInt(request.getHeader("id"));
        String password = param.get("password").toString();
        if (userService.updateUserPassword(userId, password)) {
            return "update-success";
        }
        return "update-fail";
    }

    /**
     * getUserInfo 获取用户信息
     *
     * @param
     * @return User 对应用户
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo() {
        int userId = Integer.parseInt(request.getHeader("id"));
        return userService.getById(userId);
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test-success";
    }
}
