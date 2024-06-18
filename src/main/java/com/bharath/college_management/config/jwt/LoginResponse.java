package com.bharath.college_management.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String jwtToken;
    private String username;
    private List<String> roles;
}

