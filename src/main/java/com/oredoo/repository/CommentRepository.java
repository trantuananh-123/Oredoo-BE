package com.oredoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oredoo.model.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
