package com.kagire.controllers;

import com.kagire.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("mail")
public class MailerRestController {

    @Autowired
    MailServiceImpl mailServiceImpl;

    @PostMapping("/info")
    public ResponseEntity<String> sendInfoMail(@RequestBody String email) {
        mailServiceImpl.sendInfoEmail(email);
        return new ResponseEntity<>("Sent successfully", HttpStatus.OK);
    }
}
