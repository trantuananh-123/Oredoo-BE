package com.oredoo.repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oredoo.model.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("SELECT DISTINCT(c) FROM Comment c  WHERE " +
	        "(:content IS NULL OR c.content = :content) AND " +
	        "(:userId IS NULL OR c.userId = :userId) AND " +
	        "(:startDate IS NULL OR c.createdDate >= :startDate) AND " +
	        "(:endDate IS NULL OR c.createdDate <= :endDate)")
	    List<Comment> search(@Param("content") String content,
	    					@Param("userId") String userId,
	                     @Param("startDate") Date startDate,
	                      @Param("endDate") Date endDate);



}
