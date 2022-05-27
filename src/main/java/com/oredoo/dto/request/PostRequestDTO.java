package com.oredoo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class PostRequestDTO {

    private String id;

    private String description;

    @NotBlank(message = "Post's title is required")
    private String title;

    @NotBlank(message = "Post's content is required")
    private String content;

    @NotBlank(message = "Post's user is required")
    private String userId;

    private String image;

    private float rate;

    private boolean isActive;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
