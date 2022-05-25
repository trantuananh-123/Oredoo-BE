package com.oredoo.service;

import com.oredoo.Table.DTO.PostCategoryDTO;
import com.oredoo.response.Response;

public interface PostCategoryService {
	 	Response getAll();

	    Response saveOrUpdate(PostCategoryDTO dto);

	    Response getById(PostCategoryDTO dto);

	    Response delete(PostCategoryDTO dto);
}
