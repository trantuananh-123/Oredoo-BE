package com.oredoo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDTO {
    @NotBlank(message = "User's username is required")
    private String username;

    @NotBlank(message = "User's password is required")
    private String password;
}
