package com.oredoo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oredoo.dto.request.RoleRequestDTO;
import com.oredoo.model.Error;
import com.oredoo.response.Response;
import com.oredoo.service.RoleService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/role")
public class RoleController {
	
	@Autowired
    private RoleService roleService;
	
	@GetMapping(value = "/get-all")
    public Response getAll() {
        return roleService.getAll();
    }
	
	@PostMapping(value = "/save")
    public Response saveOrUpdate(@Valid @RequestBody RoleRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return roleService.saveOrUpdate(dto);
    }
	
	@PostMapping(value = "/get-by-id")
    public Response getById(@Valid @RequestBody RoleRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return roleService.getById(dto);
    }
	
	@PostMapping(value = "/delete")
    public Response delete(@Valid @RequestBody RoleRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        }
        return roleService.delete(dto);
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
