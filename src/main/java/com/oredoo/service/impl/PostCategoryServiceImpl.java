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

import com.oredoo.dto.request.PostCategoryRequestDTO;
import com.oredoo.model.PostCategory;
import com.oredoo.repository.PostCategoryRepository;
import com.oredoo.response.Response;
import com.oredoo.service.PostCategoryService;


@Service
public class PostCategoryServiceImpl implements PostCategoryService {

    private final Logger LOGGER = LogManager.getLogger(PostCategoryServiceImpl.class);

    @Autowired
    private PostCategoryRepository postCategoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Response getAll() {
        List<PostCategory> postCategoryList = postCategoryRepository.findAll();
        return new Response(HttpStatus.OK.value(), postCategoryList, "Get category successfully");
    }

    @Override
    public Response saveOrUpdate(PostCategoryRequestDTO dto) {
        LocalDateTime currentTime = LocalDateTime.now();
        try {
            if (dto.getId() == null) {
                dto.setCreatedDate(currentTime);
            } else {
                Optional<PostCategory> optionalPostCategory = postCategoryRepository.findById(dto.getId());
                if (optionalPostCategory.isPresent()) {
                    dto.setUpdatedDate(currentTime);
                } else {
                    return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
                }
            }
            PostCategory postCategory = mapper.map(dto, PostCategory.class);
            postCategoryRepository.save(postCategory);
            return new Response(HttpStatus.OK.value(), postCategory, "Save category successfully");
        } catch (Exception e) {
            LOGGER.error(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error");
        }
    }

    @Override
    public Response getById(PostCategoryRequestDTO dto) {
        try {
            if (dto.getId() == null) {
                return new Response(HttpStatus.NOT_FOUND.value(), null, "save failed");
            }
            Optional<PostCategory> optionalPostCategory = postCategoryRepository.findById(dto.getId());
            if (optionalPostCategory.isPresent()) {
                PostCategory postCategory = optionalPostCategory.get();
                return new Response(HttpStatus.OK.value(), postCategory, "Get successfully");
            }
            return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
        } catch (Exception e) {
            LOGGER.error(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error");
        }
    }

    @Override
    public Response delete(PostCategoryRequestDTO dto) {
        try {
            if (dto.getId() == null) {
                return new Response(HttpStatus.NOT_FOUND.value(), null, "Delete failed");
            }
            Optional<PostCategory> optionalPostCategory = postCategoryRepository.findById(dto.getId());
            if (optionalPostCategory.isPresent()) {
                PostCategory postCategory = optionalPostCategory.get();
                postCategory.setIsActive(false);
                postCategoryRepository.save(postCategory);
                return new Response(HttpStatus.OK.value(), postCategory, "Delete successfully");
            }
            return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
        } catch (Exception e) {
            LOGGER.error(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error");
        }

    }

}
