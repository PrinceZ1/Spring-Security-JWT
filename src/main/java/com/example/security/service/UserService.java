package com.example.security.service;

import com.example.security.dto.LoginRequest;
import com.example.security.dto.Response;
import com.example.security.dto.UserDto;
import com.example.security.entity.User;


public interface UserService {
    Response register(UserDto registrationRequest);
    Response login(LoginRequest loginRequest);
    Response getAllUsers();
    User getLoginUser();
    Response getUserInfoAndOrderHistory();
}
