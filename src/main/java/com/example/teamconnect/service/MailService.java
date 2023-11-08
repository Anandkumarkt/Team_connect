package com.example.teamconnect.service;

import com.example.teamconnect.response.ResponseClass;
import com.example.teamconnect.response.StringConstants;
import javax.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private  String userName;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;

    @Autowired
    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public ResponseEntity<ResponseClass> sendEmailToEmployee(String email,String subject,String message) {


        /*Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });*/

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setFrom(userName);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
        ResponseClass responseClass = new ResponseClass(HttpStatus.OK, StringConstants.SIGN_UP_SUCCESS);
        return new ResponseEntity<ResponseClass>(responseClass,HttpStatus.OK);


    }
}
