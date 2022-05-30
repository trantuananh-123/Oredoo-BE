package com.oredoo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TagRequestDTO {
    private Integer id;
    private String name;
    @JsonFormat(timezone = "GMT+07:00")
    private Date createdDate;
    @JsonFormat(timezone = "GMT+07:00")
    private Date updatedDate;
    private Boolean isActive;
    private String image;
}
