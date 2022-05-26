package com.oredoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oredoo.model.PostCategory;
import com.oredoo.model.User;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Integer> {
	
}
