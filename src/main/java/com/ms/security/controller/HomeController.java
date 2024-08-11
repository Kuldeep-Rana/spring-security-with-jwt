package com.ms.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/admin")
    public String admin(){
        return "i am the admin";
    }

    @GetMapping("/manager")
    public String manager(){
        return "i am the manager";
    }

}
