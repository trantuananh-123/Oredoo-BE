package com.oredoo.dto.request;

import java.sql.Date;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CommentRequestDTO {
    private Integer id;
    private String content;
    private String userId;
    private Integer postId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    @JsonFormat(timezone = "GMT+07:00")
    private Date startDate;
    @JsonFormat(timezone = "GMT+07:00")
    private Date endDate;
}
