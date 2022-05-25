package com.oredoo.Table.DTO;
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
 private int id;
 private String name;
 private LocalDateTime createDate;
 private LocalDateTime updateDate;
 private boolean isActive;
}
