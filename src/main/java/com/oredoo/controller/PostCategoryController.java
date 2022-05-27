package com.oredoo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oredoo.dto.request.PostCategoryDTO;
import com.oredoo.response.Response;
import com.oredoo.service.PostCategoryService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/postcategory")
public class PostCategoryController {
	
	@Autowired
	private PostCategoryService postCategoryService;
	
	@GetMapping(value="/get-all")
	public Response getAll() {
		return postCategoryService.getAll();
	}
	
	@PutMapping(value="/save")
	public Response saveOrUpdate(@RequestBody PostCategoryDTO dto) {
		return postCategoryService.saveOrUpdate(dto);
	}
	@GetMapping(value="/get-by-id")
	public Response getById(@RequestBody PostCategoryDTO dto) {
		return postCategoryService.getById(dto);
	}
	
	@DeleteMapping(value="/delete")
	public Response delete(@RequestBody PostCategoryDTO dto) {
		return postCategoryService.delete(dto);
	}
	@PostMapping(value="/add")
	public Response add(@RequestBody PostCategoryDTO dto) {
		return postCategoryService.add(dto);
	}
	
	
	

}
