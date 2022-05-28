package com.oredoo.service;

import com.oredoo.dto.request.CommentRequestDTO;
import com.oredoo.response.Response;

public interface CommentService {
	Response getAll();

    Response saveOrUpdate(CommentRequestDTO dto);

    Response getById(CommentRequestDTO dto);


    Response delete(CommentRequestDTO dto);
}
