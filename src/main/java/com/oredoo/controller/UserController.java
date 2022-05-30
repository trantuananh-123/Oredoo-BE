package com.oredoo.controller;

import com.oredoo.dto.request.LoginRequestDTO;
import com.oredoo.dto.request.SignUpRequestDTO;
import com.oredoo.dto.request.UserRequestDTO;
import com.oredoo.model.Error;
import com.oredoo.response.Response;
import com.oredoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Response login(@Valid @RequestBody LoginRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        } else {
            return userService.login(dto);
        }
    }

    @PostMapping(value = "/sign-up")
    public Response save(@Valid @RequestBody SignUpRequestDTO dto, Errors errors) {
        if (errors.hasErrors()) {
            return getErrorResponse(errors);
        } else {
            return userService.signUp(dto);
        }
    }

    @GetMapping(value = "/get-all")
    public Response getById() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Response getById(@PathVariable("id") String id) {
        return userService.getUser(id);
    }

    @PostMapping(value = "/update")
    public Response update(@RequestBody UserRequestDTO dto) {
        return userService.update(dto);
    }

    @PostMapping(value = "/delete")
    public Response delete(@RequestBody UserRequestDTO dto) {
        return userService.delete(dto);
    }

    @GetMapping(value = "/check-admin/{id}")
    public Response checkAdmin(@PathVariable("id") String id) {
        return userService.checkAdmin(id);
    }

    @GetMapping(value = "/all-authors")
    public Response getAllAuthors() {
        return userService.getAllAuthors();
    }

    @GetMapping("/unique-name/{username}")
    public Response getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/unique-email/{email}")
    public Response getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping(value = "/search")
    public Response search(@RequestBody UserRequestDTO dto) {
        return userService.search(dto);
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
