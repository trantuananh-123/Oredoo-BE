package com.oredoo.service.impl;

import com.oredoo.dto.request.PostRequestDTO;
import com.oredoo.model.Post;
import com.oredoo.repository.PostRepository;
import com.oredoo.response.Response;
import com.oredoo.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Response getAllByUserId(PostRequestDTO dto) {
        List<Post> list = postRepository.findAllByUserIdOrderByCreatedDateDesc(dto.getUserId());
        return new Response(HttpStatus.OK.value(), list, "Posts fetched successfully");
    }

    @Override
    public Response saveOrUpdate(PostRequestDTO dto) {
        Post post = mapper.map(dto, Post.class);
        post.setId(UUID.randomUUID().toString());
        post = postRepository.save(post);
        return new Response(HttpStatus.OK.value(), post, "Post saved successfully");
    }

    @Override
    public Response delete(PostRequestDTO dto) {
        return null;
    }

    @Override
    public Response getById(PostRequestDTO dto) {
        return null;
    }
}
