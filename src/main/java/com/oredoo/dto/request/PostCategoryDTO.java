package com.oredoo.dto.request;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PostCategoryDTO {
 private Integer id;
 private String name;
 private LocalDateTime createdDate;
 private LocalDateTime updatedDate;
 private boolean isActive;
}
