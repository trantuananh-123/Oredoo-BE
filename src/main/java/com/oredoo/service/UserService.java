package com.oredoo.service;

import com.oredoo.dto.request.LoginRequestDTO;
import com.oredoo.dto.request.SignUpRequestDTO;
import com.oredoo.response.Response;

public interface UserService {

    Response login(LoginRequestDTO dto);

    Response signUp(SignUpRequestDTO dto);

}
