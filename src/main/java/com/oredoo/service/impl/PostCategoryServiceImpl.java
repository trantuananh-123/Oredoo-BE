package com.oredoo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oredoo.dto.request.PostCategoryDTO;
import com.oredoo.model.PostCategory;
import com.oredoo.repository.PostCategoryRepository;
import com.oredoo.response.Response;
import com.oredoo.service.PostCategoryService;



import org.apache.logging.log4j.Logger;


@Service
public class PostCategoryServiceImpl implements PostCategoryService {
	 Logger logger = LogManager.getLogger(PostCategoryServiceImpl.class);
	 
	 @Autowired
	 private PostCategoryRepository postCategoryRepository;
	 
	 @Autowired
	 private ModelMapper mapper;
	@Override
	public Response getAll() {
		// TODO Auto-generated method stub
		List<PostCategory> postCategoryList = postCategoryRepository.findAll();
		
		return new Response(HttpStatus.OK.value(), postCategoryList, "get successfully");
	}

	@Override
	public Response saveOrUpdate(PostCategoryDTO dto) {
		// TODO Auto-generated method stub
		LocalDateTime currentTime = LocalDateTime.now();
		try {
            if (dto.getId() == null) {
                dto.setCreatedDate(currentTime);
            } else {
                dto.setUpdatedDate(currentTime);
            }
            PostCategory postCategory = mapper.map(dto, PostCategory.class);
            postCategoryRepository.save(postCategory);
            return new Response(HttpStatus.OK.value(),postCategory , "save successfully");
        } catch (Exception e) {
            logger.error(e);
            return new Response(HttpStatus.NOT_IMPLEMENTED.value(),null , "save failed");
        }
	}

	@Override
	public Response getById(PostCategoryDTO dto) {
		 try {
	            if (dto.getId() == null) {
	                return new Response(HttpStatus.NOT_IMPLEMENTED.value(),null , "save failed");
	            }
	            Optional<PostCategory> optionalPostCategory = postCategoryRepository.findById(dto.getId());
	            if (optionalPostCategory.isPresent()) {
	                PostCategory postCategory = optionalPostCategory.get();
	                return new Response(HttpStatus.OK.value(),postCategory , "get successfully");
	            }
	            return new Response(HttpStatus.NOT_FOUND.value(),null , "not found");
	        } catch (Exception e) {
	            logger.error(e);
	            return new Response(HttpStatus.BAD_REQUEST.value(),null , "Exception");
	        }
	}

	@Override
	public Response delete(PostCategoryDTO dto) {
		try {
            if (dto.getId() == null) {
            	return new Response(HttpStatus.NOT_IMPLEMENTED.value(),null , "delete failed");
            }
            Optional<PostCategory> optionalPostCategory = postCategoryRepository.findById(dto.getId());
            if (optionalPostCategory.isPresent()) {
            	 PostCategory postCategory = optionalPostCategory.get();
                postCategory.setActive(false);
                postCategoryRepository.save(postCategory);
                return new Response(HttpStatus.OK.value(),postCategory , "delete successfully");
            }
            return null;
        } catch (Exception e) {
            logger.error(e);
            return new Response(HttpStatus.BAD_REQUEST.value(),null , "Exception");
        }
	}
	
}
