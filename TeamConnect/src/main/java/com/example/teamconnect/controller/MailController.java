package com.example.teamconnect.controller;

import com.example.teamconnect.dto.EmployeeDto;
import com.example.teamconnect.dto.MailDto;
import com.example.teamconnect.response.ResponseClass;
import com.example.teamconnect.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/sendMail")
    public ResponseEntity<ResponseClass> sendEmail(@RequestBody MailDto mailDto){
        return mailService.sendEmailToEmployee(mailDto.getEmailID(),mailDto.getSubject(),mailDto.getMessage());
    }
}
