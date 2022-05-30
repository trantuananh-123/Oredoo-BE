package com.oredoo.service.impl;

import com.oredoo.dto.request.LoginRequestDTO;
import com.oredoo.dto.request.SignUpRequestDTO;
import com.oredoo.dto.request.UserRequestDTO;
import com.oredoo.dto.response.LoginResponseDTO;
import com.oredoo.dto.response.UserPostResponseDTO;
import com.oredoo.model.Role;
import com.oredoo.model.User;
import com.oredoo.model.UserDetail;
import com.oredoo.repository.RoleRepository;
import com.oredoo.repository.UserRepository;
import com.oredoo.response.Response;
import com.oredoo.service.UserService;
import com.oredoo.util.JwtUtil;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
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
import java.time.ZoneId;
import java.util.*;
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

    @Autowired
    private ModelMapper mapper;

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
        user.setAvatar("../../../assets/img/default_avatar.png");
        userRepository.save(user);
        return new Response(HttpStatus.OK.value(), user, "Sign up successfully");
    }

    @Override
    public Response getAll() {
        List<User> userList = userRepository.findAll();
        return new Response(HttpStatus.OK.value(), userList, "Get all users successfully");
    }

    @Override
    public Response getUser(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(user -> new Response(HttpStatus.OK.value(), user, "Get user successfully"))
            .orElseGet(() -> new Response(HttpStatus.NOT_FOUND.value(), null, "User not found"));
    }

    @Override
    public Response checkAdmin(String userId) {
        Set<Role> roles = new LinkedHashSet<>();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            roles = optionalUser.get().getRoles();
        } else {
            return new Response(HttpStatus.NOT_FOUND.value(), null, "User not found");
        }
        for (Role i : roles) {
            if (i.getName().equals("ROLE_ADMIN")) {
                return new Response(HttpStatus.OK.value(), true, "User is admin");
            }
        }
        return new Response(HttpStatus.OK.value(), false, "User is not admin");
    }

    @Override
    public Response getAllAuthors() {
        List<User> userList = userRepository.findAll();
        List<UserPostResponseDTO> response = new ArrayList<>();
        for (User i : userList) {
            UserPostResponseDTO dto = new UserPostResponseDTO();
            dto.setUser(i);
            dto.setTotalPost(userRepository.countUserPost(i.getId()));
            response.add(dto);
        }
        return new Response(HttpStatus.OK.value(), response, "Fetch successfully");
    }

    @Override
    public Response getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(user -> new Response(HttpStatus.OK.value(), user, "Get user successfully"))
            .orElseGet(() -> new Response(HttpStatus.NOT_FOUND.value(), null, "User not found"));
    }

    @Override
    public Response getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.map(user -> new Response(HttpStatus.OK.value(), user, "Get user successfully"))
            .orElseGet(() -> new Response(HttpStatus.NOT_FOUND.value(), null, "User not found"));
    }

    @Override
    public Response delete(UserRequestDTO dto) {
        Optional<User> optionalUser = userRepository.findById(dto.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setIsActive(false);
            userRepository.save(user);
            return new Response(HttpStatus.OK.value(), null, "Delete user successfully");
        }
        return new Response(HttpStatus.NOT_FOUND.value(), null, "User not found");
    }

    @Override
    public Response update(UserRequestDTO dto) {
        Optional<User> optionalUser = userRepository.findById(dto.getId());
        if (optionalUser.isPresent()) {
            User user = mapper.map(dto, User.class);
            userRepository.save(user);
            return new Response(HttpStatus.OK.value(), user, "Update user successfully");
        }
        return new Response(HttpStatus.NOT_FOUND.value(), null, "User not found");
    }

    @Override
    public Response search(UserRequestDTO dto) {
        LocalDateTime startDate = dto.getStartDate() != null ? LocalDateTime.ofInstant(dto.getStartDate().toInstant(),
            ZoneId.systemDefault()) : null;
        LocalDateTime endDate = dto.getEndDate() != null ? LocalDateTime.ofInstant(dto.getEndDate().toInstant(),
            ZoneId.systemDefault()) : null;
        List<User> userList =
            userRepository.search(dto.getUsername(), dto.getEmail(), dto.getPhone(), dto.getIsActive(),
                startDate, endDate, dto.getRoles());
        return new Response(HttpStatus.OK.value(), userList, "Search user successfully");
    }
}
