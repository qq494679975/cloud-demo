package com.yihu.cwd.mail.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Administrator on 2016.10.27.
 */
@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.from}")
    private String from;;
    @Value("${spring.mail.to}")
    private String to;;

    @ResponseBody
    @RequestMapping("/send")
    public void sendMail(){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);//发送者.
        message.setSubject("实时统计失败");//邮件主题.
        message.setText("实时统计失败，redis数据生成失败 ");//邮件内容.
        String[] tos=to.split(",");
        message.setTo(tos);
        javaMailSender.send(message);
    }
}
