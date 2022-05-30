package com.oredoo.service;

import com.oredoo.dto.request.PostRequestDTO;
import com.oredoo.response.Response;

public interface PostService {

    Response getAll();

    Response getTop4ByRate();

    Response getAllByUserId(PostRequestDTO dto);

    Response saveOrUpdate(PostRequestDTO dto);

    Response delete(PostRequestDTO dto);

    Response getById(Integer id);

    Response search(PostRequestDTO dto);
}
