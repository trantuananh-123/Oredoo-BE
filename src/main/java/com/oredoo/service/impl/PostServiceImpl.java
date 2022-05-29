package com.oredoo.service.impl;

import com.oredoo.dto.request.PostRequestDTO;
import com.oredoo.model.Post;
import com.oredoo.model.Tag;
import com.oredoo.model.User;
import com.oredoo.repository.PostRepository;
import com.oredoo.repository.TagRepository;
import com.oredoo.repository.UserRepository;
import com.oredoo.response.Response;
import com.oredoo.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response getAll() {
        List<Post> list = postRepository.findAllByOrderByIdDesc();
        return new Response(HttpStatus.OK.value(), list, "Posts fetched successfully");
    }

    @Override
    public Response getTop4ByRate() {
        List<Post> list = postRepository.findTop4ByOrderByRateDesc();
        return new Response(HttpStatus.OK.value(), list, "Posts fetched successfully");
    }

    @Override
    public Response getAllByUserId(PostRequestDTO dto) {
        List<Post> list = postRepository.findAllByUserIdOrderByIdDesc(dto.getUserId());
        return new Response(HttpStatus.OK.value(), list, "Posts fetched successfully");
    }

    @Override
    public Response saveOrUpdate(PostRequestDTO dto) {
        Post post = mapper.map(dto, Post.class);
        List<Integer> tagIdList = dto.getTags();
        List<Tag> tags = new ArrayList<>();
        for (Integer tagId : tagIdList) {
            Optional<Tag> optionalTag = tagRepository.findById(tagId);
            optionalTag.ifPresent(tags::add);
        }
        Optional<User> optionalUser = userRepository.findById(dto.getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            post.setAuthorName(user.getUsername());
        } else {
            return new Response(HttpStatus.NOT_FOUND.value(), null, "Author not found");
        }
        post.setTags(tags);
        post = postRepository.save(post);
        return new Response(HttpStatus.OK.value(), post, "Post saved successfully");
    }

    @Override
    public Response delete(PostRequestDTO dto) {
        try {
            if (dto.getId() == null) {
                return new Response(HttpStatus.NOT_FOUND.value(), null, "Delete failed");
            }
            Optional<Post> optionalPost = postRepository.findById(dto.getId());
            if (optionalPost.isPresent()) {
                Post post = optionalPost.get();
                post.setIsActive(false);
                postRepository.save(post);
                return new Response(HttpStatus.OK.value(), post, "Delete successfully");
            }
            return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error");
        }
    }

    @Override
    public Response getById(Integer id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            return new Response(HttpStatus.OK.value(), post, "Post fetched successfully");
        }
        return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
    }

    @Override
    public Response search(PostRequestDTO dto) {
        List<Post> list =
            postRepository.search(dto.getAuthorName(), dto.getCategoryId(), dto.getTags(), dto.getStartDate(),
                dto.getEndDate());
        return new Response(HttpStatus.OK.value(), list, "Post fetched successfully");
    }
}
