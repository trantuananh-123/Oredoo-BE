package com.oredoo.dto.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
public class SignUpRequestDTO {
    @NotBlank(message = "User's username is required")
    private String username;

    @NotBlank(message = "User's email is required")
    @Pattern(regexp = "^[\\w._%+-]+@[a-zA-Z]+\\.[a-zA-Z]{2,6}$", message = "Invalid email address")
    private String email;

    private Set<String> role;

    @NotBlank(message = "User's password is required")
    private String password;

    @Min(value = 1)
    @Max(value = 3)
    private int type;
}
