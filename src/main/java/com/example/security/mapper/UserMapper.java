package com.example.security.mapper;

import com.example.security.dto.UserDto;
import com.example.security.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRole(userDto.getRole());
        return userDto;
    }
}
