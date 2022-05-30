package com.oredoo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class PostRequestDTO {
    private Integer id;
    private String title;
    private String description;
    private String content;
    private String userId;
    private String authorName;
    private Integer categoryId;
    private String image;
    private Float rate;
    private Boolean isActive;
    private Date createdDate;
    private Date updatedDate;
    private List<Integer> tags;
    @JsonFormat(timezone = "GMT+07:00")
    private Date startDate;
    @JsonFormat(timezone = "GMT+07:00")
    private Date endDate;

}
