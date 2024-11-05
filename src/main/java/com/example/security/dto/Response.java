package com.example.security.dto;

import com.example.security.entity.Product;
import com.example.security.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private int status;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private String token;
    private String role;
    private String expirationTime;
    private int totalPage;
    private int totalElement;

    private UserDto user;
    private List<UserDto> userList;

    private ProductDto product;
    private List<ProductDto> productList;

}
