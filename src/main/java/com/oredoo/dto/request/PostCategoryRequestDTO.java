package com.oredoo.dto.request;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class PostCategoryRequestDTO {

    private Integer id;
    private String name;
    @JsonFormat(timezone = "GMT+07:00")
    private Date createdDate;
    @JsonFormat(timezone = "GMT+07:00")
    private Date updatedDate;
    private Boolean isActive;
    private String image;

}
