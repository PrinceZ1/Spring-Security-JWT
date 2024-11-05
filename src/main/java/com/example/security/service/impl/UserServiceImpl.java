package com.example.security.service.impl;

import com.example.security.dto.LoginRequest;
import com.example.security.dto.Response;
import com.example.security.dto.UserDto;
import com.example.security.entity.User;
import com.example.security.exception.InvalidCredentialsException;
import com.example.security.exception.NotFoundException;
import com.example.security.mapper.UserMapper;
import com.example.security.repository.UserRepository;
import com.example.security.security.JWTUtils;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserMapper userMapper;


    @Override
    public Response register(UserDto registrationRequest) {
        String role = "USER";
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .role(role).build();
        User savedUser = userRepository.save(user);
        System.out.println(savedUser);

        UserDto userDto = userMapper.toUserDto(savedUser);
        return Response.builder()
                .status(200)
                .message("User successfully added")
                .user(userDto)
                .build();
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(()-> new NotFoundException("Email not found"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Password does not match");
        }
        String token = jwtUtils.generateToken(user);

        return Response.builder()
                .status(200)
                .message("User Successfully Logged In")
                .token(token)
                .expirationTime("1 Day")
                .role(user.getRole())
                .build();
    }

    @Override
    public Response getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(userMapper::toUserDto)
                .toList();

        return Response.builder()
                .status(200)
                .userList(userDtos)
                .build();
    }
    @Override
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Not found"));
    }

    @Override
    public Response getUserInfoAndOrderHistory() {
        User user = getLoginUser();
        UserDto userDto = userMapper.toUserDto(user);

        return Response.builder()
                .status(200)
                .user(userDto)
                .build();
    }

}
