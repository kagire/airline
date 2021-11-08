package com.kagire.controllers;

import com.kagire.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("mail")
public class MailerRestController {

    @Autowired
    MailService mailService;

    @PostMapping("/info")
    public ResponseEntity<String> sendInfoMail(@RequestBody String email) {
        mailService.sendInfoEmail(email);
        return new ResponseEntity<>("Sent successfully", HttpStatus.OK);
    }
}
