package com.oredoo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oredoo.dto.request.RoleRequestDTO;
import com.oredoo.model.Role;
import com.oredoo.model.Tag;
import com.oredoo.repository.RoleRepository;
import com.oredoo.response.Response;
import com.oredoo.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	private final Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class);
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
    private ModelMapper mapper;
	
	@Override
    public Response getAll() {
        List<Role> roleList = roleRepository.findAll();
        return new Response(HttpStatus.OK.value(), roleList, "Get role successfully");
    }

	@Override
	public Response saveOrUpdate(RoleRequestDTO dto) {
		LocalDateTime currentTime = LocalDateTime.now();
        try {
            if (dto.getId() == null) {
                dto.setCreatedDate(currentTime);
            } else {
                Optional<Role> optionalRole = roleRepository.findById(dto.getId());
                if (optionalRole.isPresent()) {
                    dto.setUpdatedDate(currentTime);
                } else {
                    return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
                }
            }
            Role role = mapper.map(dto, Role.class);
            roleRepository.save(role);
            return new Response(HttpStatus.OK.value(), role, "Save role successfully");
        } catch (Exception e) {
            LOGGER.error(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error");
        }
	}

	@Override
	public Response getById(RoleRequestDTO dto) {
		try {
            if (dto.getId() == null) {
                return new Response(HttpStatus.NOT_FOUND.value(), null, "save failed");
            }
            Optional<Role> optionalRole = roleRepository.findById(dto.getId());
            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();
                return new Response(HttpStatus.OK.value(), role, "Get successfully");
            }
            return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
        } catch (Exception e) {
            LOGGER.error(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error");
        }
	}

	@Override
	public Response delete(RoleRequestDTO dto) {
		try {
            if (dto.getId() == null) {
                return new Response(HttpStatus.NOT_FOUND.value(), null, "Delete failed");
            }
            Optional<Role> optionalRole = roleRepository.findById(dto.getId());
            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();
                role.setIsActive(false);
                roleRepository.save(role);
                return new Response(HttpStatus.OK.value(), role, "Delete successfully");
            }
            return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
        } catch (Exception e) {
            LOGGER.error(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error");
        }
	}
	
}
