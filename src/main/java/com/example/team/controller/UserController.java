package com.example.team.controller;

import com.example.team.pojo.*;
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
import java.util.Random;
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
    public String login(@RequestBody Map<String, Object> param) {
        String userName = param.get("userName").toString();
        String password = param.get("password").toString();
        if (userService.verify(userName, password)) {
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            int id = userService.getId();
            redisService.set(String.valueOf(id), token);
            redisService.setExpire(String.valueOf(id), 21600);
            response.setHeader("Access-Control-Expose-Headers", "token");
            response.setHeader("Access-Control-Expose-Headers", "id");
            response.setHeader("token", token);
            response.setHeader("id", String.valueOf(id));
            return "login-success";
        }
        return "login-fail";
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
        PetStatus petStatus=new PetStatus();
        petStatus.setPetStatusId(1);
        pet.setPetStatus(petStatus);
        Skin skin=new Skin();
        skin.setSkinId(1);
        pet.setSkin(skin);
        pet.setLevel(1);
        String verification = redisService.get(user.getEmail());
        if (verification != null) {
            if (verification.equals(param.get("verification").toString())) {
                if (userService.sign(user, pet)) {
                    return "sign-succees";
                }
                return "sign-fail";
            }
            return "verify-fail";
        }
        return "overtime";
    }

    /**
     * updateEamil 修改信息
     *
     * @param param 修改的用户信息参数 map
     * @return String 修改结果
     */
    @RequestMapping(value = "/updateEmail", method = RequestMethod.POST)
    @ResponseBody
    public String updateEmail(@RequestBody Map<String, Object> param) {
        int userId = Integer.parseInt(request.getHeader("id"));
        String email = param.get("email").toString();
        String verification = redisService.get(email);
        if (verification != null) {
            if (verification.equals(param.get("verification").toString())) {
                if (userService.updateEmail(userId, email)) {
                    return "update-success";
                }
                return "update-fail";
            }
            return "verify-fail";
        }
        return "overtime";
    }

    /**
     * updateUserName 修改信息
     *
     * @param param 修改的用户信息参数 map
     * @return String 修改结果
     */
    @RequestMapping(value = "/updateUserName", method = RequestMethod.POST)
    @ResponseBody
    public String updateUserName(@RequestBody Map<String, Object> param) {
        int userId = Integer.parseInt(request.getHeader("id"));
        String userName = param.get("userName").toString();
        if (userService.updateUserName(userId, userName)) {
            return "update-success";
        }
        return "update-fail";

    }

    /**
     * updateTel 修改信息
     *
     * @param param 修改的用户信息参数 map
     * @return String 修改结果
     */
    @RequestMapping(value = "/updateTel", method = RequestMethod.POST)
    @ResponseBody
    public String updateTel(@RequestBody Map<String, Object> param) {
        int userId = Integer.parseInt(request.getHeader("id"));
        String tel = param.get("tel").toString();
        if (userService.updateTel(userId, tel)) {
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
     * @param email 邮箱账号
     * @return 找回邮件发送结果
     */
    @RequestMapping("/findPassword")
    @ResponseBody
    public String findPassword(@RequestParam String email) {
        int userId = userService.getUserId("", email, "");
        String address="http://3554ff4f3e94.ngrok.io";
        String content = "点击下方重置链接重置密码<br><a href = \""+address+"/user/gotoReset?key="
                + userId + "@" + new java.util.Date().getTime() + "\">重置链接</a><br>有效时长10分钟。";
        if (userId != 0) {
            if (MailUtil.sendEmail(email, "重置密码",content)) {
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
        User user = userService.getById(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("email", user.getEmail());
        if (date.getTime() - time <= 600000) {
            model.addAttribute("flag", "true");
            return "resetPassword";
        }
        model.addAttribute("flag", "false");
        return "resetPassword";
    }

    /**
     * resetPassword 重置密码
     *
     * @param userId password1 修改的密码参数
     * @return String 修改结果
     */
    @RequestMapping(value = "/resetPassword")
    public String resetPassword(@RequestParam String userId, @RequestParam String password1, Model model) {
        int userId1 = Integer.parseInt(userId);
        User user = userService.getById(userId1);
        model.addAttribute("email", user.getEmail());
        if (userService.updateUserPassword(userId1, password1)) {
            model.addAttribute("flag", "true");
            return "result";
        }
        model.addAttribute("flag", "false");
        return "result";
    }

    /**
     * sendVerification 找回密码
     *
     * @param email 邮箱账号
     * @return 找回邮件发送结果
     */
    @RequestMapping("/sendVerification")
    @ResponseBody
    public String sendVerification(@RequestParam String email) {
        StringBuilder content = new StringBuilder();
        StringBuilder code = new StringBuilder();
        content.append("有效时长：5分钟<br>");
        String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
        Random rand = new Random();
        for (int j = 0; j < 6; j++) {
            code.append(sources.charAt(rand.nextInt(9)));
        }
        content.append(code);
        if (MailUtil.sendEmail(email, "验证码",content.toString())) {
            redisService.set(email,code.toString());
            redisService.setExpire(email, 300);
            return "send-success";
        }
        return "send-fail";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test-success";
    }
}
