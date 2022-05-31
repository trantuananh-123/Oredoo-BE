package com.oredoo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class PostCategoryRequestDTO {

    private Integer id;
    private String name;
    @JsonFormat(timezone = "GMT+07:00")
    private Date createdDate;
    @JsonFormat(timezone = "GMT+07:00")
    private Date updatedDate;
    private Boolean isActive;
    @JsonFormat(timezone = "GMT+07:00")
    private Date startDate;
    @JsonFormat(timezone = "GMT+07:00")
    private Date endDate;
    private String image;

}
