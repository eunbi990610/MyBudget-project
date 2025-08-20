package com.example.mybudget.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String main(HttpSession session){

        if(session.getAttribute("userId")==null){
            return "redirect:user/login";
        };

        return "mainPage";
    }


}
