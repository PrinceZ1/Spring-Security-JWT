package com.example.security.controller;

import com.example.security.dto.LoginRequest;
import com.example.security.dto.Response;
import com.example.security.dto.UserDto;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<Response> register(@RequestBody UserDto registrationRequest) {
        System.out.println(registrationRequest);
        return ResponseEntity.ok(userService.register(registrationRequest));

    }
    @PostMapping("/auth/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

}
