package com.oredoo.service.impl;

import com.oredoo.dto.request.LoginRequestDTO;
import com.oredoo.dto.request.SignUpRequestDTO;
import com.oredoo.dto.response.LoginResponseDTO;
import com.oredoo.model.Role;
import com.oredoo.model.User;
import com.oredoo.model.UserDetail;
import com.oredoo.repository.RoleRepository;
import com.oredoo.repository.UserRepository;
import com.oredoo.response.Response;
import com.oredoo.service.UserService;
import com.oredoo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Response login(LoginRequestDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        if (authentication == null) {
            return new Response(HttpStatus.UNAUTHORIZED.value(), null, "Invalid username or password");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);
        UserDetail userDetails = (UserDetail) authentication.getPrincipal();
        if (userDetails == null) {
            return new Response(HttpStatus.UNAUTHORIZED.value(), null, "Invalid username or password");
        }
        Set<String> roles =
            userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        LocalDateTime expirationTime = jwtUtil.getExpirationTimeFromJwtToken(jwt);
        LoginResponseDTO response =
            new LoginResponseDTO(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
                expirationTime, roles);
        return new Response(HttpStatus.OK.value(), response, "Login successfully");
    }

    @Override
    public Response signUp(SignUpRequestDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            return new Response(HttpStatus.BAD_REQUEST.value(), null, "Username is already taken");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            return new Response(HttpStatus.BAD_REQUEST.value(), null, "Email is already taken");
        }
        User user = new User(dto.getUsername(), dto.getEmail(), encoder.encode(dto.getPassword()), dto.getType());
        Set<Role> roles = new HashSet<>();
        if (dto.getRole() == null) {
            Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");
            optionalRole.ifPresent(roles::add);
        }
        user.setId(UUID.randomUUID().toString());
        user.setRoles(roles);
        userRepository.save(user);
        return new Response(HttpStatus.OK.value(), user, "User registered successfully");
    }
}
