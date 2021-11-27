package com.challenge.getir.controller;

import com.challenge.getir.model.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin() {
        return "hello admin";
    }

    @GetMapping("/hello/user")
    public String helloUser() {
        UserPrincipal userPrincipal =
                (UserPrincipal) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        return "hello " + userPrincipal.getUsername();
    }
}