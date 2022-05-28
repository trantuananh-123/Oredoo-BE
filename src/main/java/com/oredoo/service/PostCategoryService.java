package com.oredoo.service;

import com.oredoo.dto.request.PostCategoryRequestDTO;
import com.oredoo.response.Response;

public interface PostCategoryService {
    Response getAll();

    Response saveOrUpdate(PostCategoryRequestDTO dto);

    Response getById(PostCategoryRequestDTO dto);

    Response delete(PostCategoryRequestDTO dto);
}
