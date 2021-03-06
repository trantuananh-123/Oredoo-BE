package com.oredoo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oredoo.dto.request.CommentRequestDTO;
import com.oredoo.model.Error;
import com.oredoo.response.Response;
import com.oredoo.service.CommentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/get-all/{postId}")
    public Response getAllByPostId(@PathVariable("postId") Integer postId) {
        return commentService.getAllByPostId(postId);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "/save")
    public Response saveOrUpdate(@Valid @RequestBody CommentRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return commentService.saveOrUpdate(dto);
    }

    @GetMapping(value = "{id}")
    public Response getById(@PathVariable("id") Integer id) {
        return commentService.getById(id);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "/delete")
    public Response delete(@Valid @RequestBody CommentRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return commentService.delete(dto);
    }

    @GetMapping(value = "/search")
    public Response search(@Valid @RequestBody CommentRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return commentService.search(dto);
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
