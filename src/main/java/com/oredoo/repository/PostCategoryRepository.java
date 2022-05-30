package com.oredoo.repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oredoo.model.PostCategory;
import com.oredoo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Integer> {

	 @Query("SELECT DISTINCT(p) FROM PostCategory p WHERE " +
		        "(:name IS NULL OR p.name = :name) AND " +
			 "(:isActive is null or p.isActive = :isActive) AND"+
		        "(:startDate IS NULL OR p.createdDate >= :startDate) AND " +
		        "(:endDate IS NULL OR p.createdDate <= :endDate)")
		    List<PostCategory> search(@Param("name") String name,
		    						@Param("isActive")Boolean isActive,
		                     @Param("startDate") Date startDate,
		                      @Param("endDate") Date endDate);

	
}
