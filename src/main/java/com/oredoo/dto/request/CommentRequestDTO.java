package com.oredoo.dto.request;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentRequestDTO {
	  	private Integer id;
	    private String content;
	    private LocalDateTime createdDate;
	    private LocalDateTime updatedDate;
}
