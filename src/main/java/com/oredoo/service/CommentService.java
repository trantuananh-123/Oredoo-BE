package com.oredoo.service;

import com.oredoo.dto.request.CommentRequestDTO;
import com.oredoo.response.Response;

public interface CommentService {
	Response getAllByPostId(Integer postId);

    Response saveOrUpdate(CommentRequestDTO dto);

    Response getById(Integer id);

    Response delete(CommentRequestDTO dto);
    
    Response search(CommentRequestDTO dto);
}
