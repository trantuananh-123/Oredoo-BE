package com.oredoo.dto.request;

import java.time.LocalDateTime;

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
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Boolean isActive;

}
