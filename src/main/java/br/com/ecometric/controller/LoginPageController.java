package br.com.ecometric.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

    @GetMapping("/custom-login")
    public String loginPage() {
        return "login"; 
    }
}
