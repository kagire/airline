package com.kagire.controllers;

import com.kagire.webServices.MailServiceWebImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    MailServiceWebImpl mailServiceWebImpl;

    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        return "home";
    }

    @GetMapping(value = "mail/info")
    public String infoToEmailPage(Model model) {
        model.addAttribute("email", "");
        return "info_email_form";
    }

    @PostMapping(value = "mail/info")
    public String sendInfoToEmail(@RequestParam String email) {
        mailServiceWebImpl.sendInfoEmail(email);
        return "redirect:/";
    }
}
