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

import com.oredoo.dto.request.CommentRequestDTO;
import com.oredoo.model.Comment;
import com.oredoo.model.Post;
import com.oredoo.repository.CommentRepository;
import com.oredoo.response.Response;
import com.oredoo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	 private final Logger LOGGER = LogManager.getLogger(CommentServiceImpl.class);

	    @Autowired
	    private CommentRepository commentRepository;

	    @Autowired
	    private ModelMapper mapper;
	@Override
	public Response getAll() {
		List<Comment> commentList = commentRepository.findAll();
        return new Response(HttpStatus.OK.value(), commentList, "Get category successfully");
	}

	@Override
	public Response saveOrUpdate(CommentRequestDTO dto) {
		LocalDateTime currentTime = LocalDateTime.now();
        try {
            if (dto.getId() == null) {
                dto.setCreatedDate(currentTime);
            } else {
                Optional<Comment> optionalComment = commentRepository.findById(dto.getId());
                if (optionalComment.isPresent()) {
                    dto.setUpdatedDate(currentTime);
                } else {
                    return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
                }
            }
            Comment comment = mapper.map(dto, Comment.class);
            commentRepository.save(comment);
            return new Response(HttpStatus.OK.value(), comment, "Save category successfully");
        } catch (Exception e) {
            LOGGER.error(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error");
        }
	}

	@Override
	public Response getById(Integer id) {
		Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            return new Response(HttpStatus.OK.value(), comment, "Post fetched successfully");
        }
        return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
	}

	@Override
	public Response delete(CommentRequestDTO dto) {
		  try {
	            if (dto.getId() == null) {
	                return new Response(HttpStatus.NOT_FOUND.value(), null, "Delete failed");
	            }
	            Optional<Comment> optionalComment = commentRepository.findById(dto.getId());
	            if (optionalComment.isPresent()) {
	                Comment comment = optionalComment.get();
	                commentRepository.delete(comment);
	                return new Response(HttpStatus.OK.value(), comment, "Delete successfully");
	            }
	            return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
	        } catch (Exception e) {	
	            LOGGER.error(e);
	            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error");
	        }

	}

}
