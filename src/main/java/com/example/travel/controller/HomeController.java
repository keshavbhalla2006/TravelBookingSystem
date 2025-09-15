package com.example.travel.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(Authentication auth, Model model) {
        model.addAttribute("auth", auth);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
