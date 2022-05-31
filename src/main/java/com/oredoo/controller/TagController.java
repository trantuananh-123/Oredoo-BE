package com.oredoo.controller;

import com.oredoo.dto.request.TagRequestDTO;
import com.oredoo.model.Error;
import com.oredoo.response.Response;
import com.oredoo.service.TagService;
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
@RequestMapping(value = "api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping(value = "/get-all")
    public Response getAll() {
        return tagService.getAll();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "/save")
    public Response saveOrUpdate(@Valid @RequestBody TagRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return tagService.saveOrUpdate(dto);
    }

    @PostMapping(value = "/get-by-id")
    public Response getById(@Valid @RequestBody TagRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return tagService.getById(dto);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "/delete")
    public Response delete(@Valid @RequestBody TagRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return tagService.delete(dto);
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
