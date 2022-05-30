package com.oredoo.service.impl;

import com.oredoo.dto.request.TagRequestDTO;
import com.oredoo.model.Tag;
import com.oredoo.repository.TagRepository;
import com.oredoo.response.Response;
import com.oredoo.service.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;


@Service
public class TagServiceImpl implements TagService {

    private final Logger LOGGER = LogManager.getLogger(TagServiceImpl.class);

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Response getAll() {
        List<Tag> tagList = tagRepository.findAll();
        return new Response(HttpStatus.OK.value(), tagList, "Get tag successfully");
    }

    @Override
    public Response saveOrUpdate(TagRequestDTO dto) {
        Tag tag = mapper.map(dto, Tag.class);
        try {
            if (dto.getId() == null) {
                tag.setCreatedDate(LocalDateTime.ofInstant(dto.getCreatedDate().toInstant(),
                    ZoneId.systemDefault()));
            } else {
                Optional<Tag> optionalPostCategory = tagRepository.findById(dto.getId());
                if (optionalPostCategory.isPresent()) {
                    tag.setCreatedDate(LocalDateTime.ofInstant(dto.getCreatedDate().toInstant(),
                        ZoneId.systemDefault()));
                    tag.setUpdatedDate(LocalDateTime.ofInstant(dto.getUpdatedDate().toInstant(),
                        ZoneId.systemDefault()));
                } else {
                    return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
                }
            }
            tagRepository.save(tag);
            return new Response(HttpStatus.OK.value(), tag, "Save tag successfully");
        } catch (Exception e) {
            LOGGER.error(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error");
        }
    }

    @Override
    public Response getById(TagRequestDTO dto) {
        try {
            if (dto.getId() == null) {
                return new Response(HttpStatus.NOT_FOUND.value(), null, "save failed");
            }
            Optional<Tag> optionalTag = tagRepository.findById(dto.getId());
            if (optionalTag.isPresent()) {
                Tag tag = optionalTag.get();
                return new Response(HttpStatus.OK.value(), tag, "Get successfully");
            }
            return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
        } catch (Exception e) {
            LOGGER.error(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error");
        }
    }

    @Override
    public Response delete(TagRequestDTO dto) {
        try {
            if (dto.getId() == null) {
                return new Response(HttpStatus.NOT_FOUND.value(), null, "Delete failed");
            }
            Optional<Tag> optionalTag = tagRepository.findById(dto.getId());
            if (optionalTag.isPresent()) {
                tagRepository.deleteById(dto.getId());
                return new Response(HttpStatus.OK.value(), null, "Delete successfully");
            }
            return new Response(HttpStatus.NOT_FOUND.value(), null, "Not found");
        } catch (Exception e) {
            LOGGER.error(e);
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error");
        }
    }

}
