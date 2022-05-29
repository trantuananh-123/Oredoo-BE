package com.oredoo.controller;

import com.oredoo.dto.request.PostRequestDTO;
import com.oredoo.model.Error;
import com.oredoo.response.Response;
import com.oredoo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/get-all")
    public Response getAll() {
        return postService.getAll();
    }

    @GetMapping(value = "/get-by-rate")
    public Response getTop4ByRate() {
        return postService.getTop4ByRate();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "/get-all-by-user-id")
    public Response getAllByUserId(@Valid @RequestBody PostRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return postService.getAllByUserId(dto);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "/save")
    public Response saveOrUpdate(@Valid @RequestBody PostRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return postService.saveOrUpdate(dto);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "/delete")
    public Response delete(@Valid @RequestBody PostRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return postService.delete(dto);
    }

    @PostMapping(value = "/search")
    public Response search(@Valid @RequestBody PostRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return postService.search(dto);
    }

    @GetMapping(value = "/{id}")
    public Response getById(@PathVariable("id") Integer id) {
        return postService.getById(id);
    }

    private Response getErrorResponse(Errors errors) {
        List<Error> list = new ArrayList<>();
        List<FieldError> fieldErrorList = errors.getFieldErrors();
        for (FieldError fieldError : fieldErrorList) {
            Error error = new Error(fieldError.getField(), fieldError.getDefaultMessage());
            list.add(error);
        }
        return new Response(HttpStatus.BAD_REQUEST.value(), list, "Error");
    }

}
