package com.oredoo.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class LoginResponseDTO {
    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private LocalDateTime expirationTime;
    private Set<String> roles;

    public LoginResponseDTO(String token, String id, String username, String email, LocalDateTime expirationTime, Set<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.expirationTime = expirationTime;
        this.roles = roles;
    }
}
