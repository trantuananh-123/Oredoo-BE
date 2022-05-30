package com.oredoo.dto.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RoleRequestDTO {
	private Integer id;
	private String name;
	private String description;
	private Boolean isActive;
	private Boolean isDelete;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
