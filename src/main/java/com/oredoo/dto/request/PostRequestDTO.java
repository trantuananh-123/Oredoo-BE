package com.oredoo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class PostRequestDTO {
    private Integer id;
    private String description;
    private String title;
    private String content;
    private String userId;
    private String authorName;
    private Integer categoryId;
    private List<Integer> tags;
    private Integer tagId;
    private String image;
    private Float rate;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    @JsonFormat(timezone = "GMT+07:00")
    private Date startDate;
    @JsonFormat(timezone = "GMT+07:00")
    private Date endDate;

}
