package com.oredoo.dto.response;

import com.oredoo.model.User;
import lombok.Data;

@Data
public class UserPostResponseDTO {
    private User user;
    private Integer totalPost;
}
