package com.bharath.college_management.config.jwt;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
