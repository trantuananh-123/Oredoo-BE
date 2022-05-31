package com.oredoo.service;

import com.oredoo.dto.request.LoginRequestDTO;
import com.oredoo.dto.request.SignUpRequestDTO;
import com.oredoo.dto.request.UserRequestDTO;
import com.oredoo.response.Response;

public interface UserService {

    Response login(LoginRequestDTO dto);

    Response signUp(SignUpRequestDTO dto);

    Response getAll();

    Response getUser(String userId);

    Response getUserByUsername(String username);

    Response getUserByEmail(String email);

    Response checkAdmin(String userId);

    Response getAllAuthors();

    Response delete(UserRequestDTO dto);

    Response update(UserRequestDTO dto);

    Response search(UserRequestDTO dto);

    Response forgotPassword(UserRequestDTO dto);

}
