package com.example.team.util;

import com.example.team.mail.MailSenderInfo;
import com.example.team.mail.SimpleMailSender;

import java.util.Date;

public class MailUtil {
    public static boolean sendEmail(String email,String title,String content) {
        Boolean flag = false;
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.163.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("wjchen1015@163.com");
        mailInfo.setPassword("QXCYUDMKNVCYKZWD");
        mailInfo.setFromAddress("wjchen1015@163.com");
        mailInfo.setToAddress(email);
        mailInfo.setSubject(title);
        //String content = "点击下方重置链接重置密码<br><a href = \"http://localhost:8080/user/gotoReset?key="
               // + userId + "@" + now.getTime() + "\">重置链接</a><br>有效时长10分钟。";
        mailInfo.setContent(content);
        //这个类主要来发送邮件
        try {
            SimpleMailSender sms = new SimpleMailSender();
            sms.sendTextMail(mailInfo);//发送文体格式
            SimpleMailSender.sendHtmlMail(mailInfo);//发送html格式
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
