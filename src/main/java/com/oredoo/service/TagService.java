package com.oredoo.service;

import com.oredoo.dto.request.TagRequestDTO;
import com.oredoo.response.Response;

public interface TagService {
    Response getAll();

    Response saveOrUpdate(TagRequestDTO dto);

    Response getById(TagRequestDTO dto);

    Response delete(TagRequestDTO dto);
}
