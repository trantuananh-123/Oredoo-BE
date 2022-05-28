package com.oredoo.controller;

import com.oredoo.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oredoo.dto.request.PostCategoryRequestDTO;
import com.oredoo.response.Response;
import com.oredoo.service.PostCategoryService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/post_category")
public class PostCategoryController {

    @Autowired
    private PostCategoryService postCategoryService;

    @GetMapping(value = "/get-all")
    public Response getAll() {
        return postCategoryService.getAll();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "/save")
    public Response saveOrUpdate(@Valid @RequestBody PostCategoryRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return postCategoryService.saveOrUpdate(dto);
    }

    @PostMapping(value = "/get-by-id")
    public Response getById(@Valid @RequestBody PostCategoryRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return postCategoryService.getById(dto);
    }

    @PostMapping(value = "/delete")
    public Response delete(@Valid @RequestBody PostCategoryRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return postCategoryService.delete(dto);
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
