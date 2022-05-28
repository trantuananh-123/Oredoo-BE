package com.oredoo.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostRequestDTO {

    private Integer id;

    private String description;

    private String title;

    private String content;

    private String userId;

    private String authorName;

    private int categoryId;

    private List<Integer> tags;

    private String image;

    private float rate;

    private Boolean isActive;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
