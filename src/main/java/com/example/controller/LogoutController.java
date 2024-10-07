package com.example.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {
    public void logout(){
        System.out.println("added logout feature");
    }
}
