package com.oredoo.controller;

import com.oredoo.dto.request.PostRequestDTO;
import com.oredoo.response.Response;
import com.oredoo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/post")
public class PostController {

    @Autowired
    private PostService postService;

//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "/get-all-by-user-id")
    public Response getAllByUserId(@RequestBody PostRequestDTO dto) {
        return postService.getAllByUserId(dto);
    }

//        @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "/save")
    public Response saveOrUpdate(@RequestBody PostRequestDTO dto) {
        return postService.saveOrUpdate(dto);
    }

}
