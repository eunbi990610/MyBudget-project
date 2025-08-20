package com.example.mybudget.controller;

import com.example.mybudget.dto.UserDTO;
import com.example.mybudget.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    public final UserService userService;

    @GetMapping("/join")
    public String join(){
        return "user/join";
    }

    @PostMapping("/join")
    public String join(UserDTO userDTO){
        System.out.println("userDTO.toString() = " + userDTO.toString());
        userService.addUser(userDTO);





        return "user/join";
    }



    @GetMapping("/login")
    public String login(){

        return "user/login";
    }

    @PostMapping("/login")
    public String login(String loginId, String password, HttpSession session){

        Long userId = userService.findUserId(loginId, password);
        session.setAttribute("userId", userId);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }




}
