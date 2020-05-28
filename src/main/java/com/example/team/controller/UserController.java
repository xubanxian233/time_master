package com.example.team.controller;

import com.example.team.pojo.Pet;
import com.example.team.pojo.User;
import com.example.team.pojo.UserTodo;
import com.example.team.service.*;
import com.example.team.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserTodoService userTodoService;
    @Autowired
    private RedisService redisService;

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
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            int id = userService.getId();
            redisService.set(String.valueOf(id), token);
            redisService.setExpire(String.valueOf(id), 100000);
            response.setHeader("Access-Control-Expose-Headers", "token");
            response.setHeader("Access-Control-Expose-Headers", "id");
            response.setHeader("token", token);
            response.setHeader("id", String.valueOf(id));
            return userTodoService.listUserTodo(0, id);
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
     * @param param 修改的密码参数
     * @return String 修改结果
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(@RequestBody Map<String, Object> param) {
        int userId = Integer.parseInt(request.getHeader("id"));
        String newPassword = param.get("newPassword").toString();
        String oldPassword = param.get("oldPassword").toString();
        User user = userService.getById(userId);
        if (userService.verify(user.getEmail(), oldPassword)) {
            if (userService.updateUserPassword(userId, newPassword)) {
                return "update-success";
            }
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

    /**
     * findPassword 找回密码
     *
     * @param  email 邮箱账号
     * @return  找回邮件发送结果
     */
    @RequestMapping("/findPassword")
    @ResponseBody
    public String findPassword(@RequestParam String email) {
        int userId = userService.getUserId("", email, "");
        if (userId != 0) {
            if (MailUtil.sendEmail(email, userId)) {
                return "send-success";
            }
        }
        return "send-fail";
    }

    /**
     * gotoReset 跳转重置密码页面
     *
     * @param key model 判定参数和传递参数
     * @return String 跳转页面
     */
    @RequestMapping(value = "/gotoReset")
    public String gotoReset(@RequestParam String key, Model model) {
        String[] keys = key.split("@");
        int userId = Integer.parseInt(keys[0]);
        Long time = Long.parseLong(keys[1]);
        java.util.Date date = new java.util.Date();
        User user=userService.getById(userId);
        model.addAttribute("userId",userId);
        model.addAttribute("email",user.getEmail());
        if (date.getTime() - time <= 600000) {
            model.addAttribute("flag","true");
            return "resetPassword";
        }
        model.addAttribute("flag","false");
        return "resetPassword";
    }

    /**
     * resetPassword 重置密码
     *
     * @param userId password1 修改的密码参数
     * @return String 修改结果
     */
    @RequestMapping(value = "/resetPassword")
    public String resetPassword(@RequestParam String userId, @RequestParam String password1,Model model) {
        int userId1 = Integer.parseInt(userId);
        User user = userService.getById(userId1);
        model.addAttribute("email",user.getEmail());
        if (userService.updateUserPassword(userId1, password1)) {
            model.addAttribute("flag","true");
            return "result";
        }
        model.addAttribute("flag","false");
        return "result";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test-success";
    }
}
