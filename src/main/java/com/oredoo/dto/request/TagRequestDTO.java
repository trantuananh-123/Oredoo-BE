package com.oredoo.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TagRequestDTO {
    private Integer id;

    private String name;

    private String image;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Boolean isActive;
}
