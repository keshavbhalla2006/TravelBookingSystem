package org.example.travel.controller;

import org.example.travel.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService){ this.userService = userService; }

    @GetMapping("/login")
    public String loginPage(){ return "login"; }

    @GetMapping("/register")
    public String registerForm(){ return "register"; }

    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model){
        try{
            userService.registerUser(name, email, password);
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "register";
        }
        return "redirect:/login?registered";
    }
}