package com.oredoo.repository;

import com.oredoo.model.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Integer> {

    @Query("SELECT DISTINCT(p) FROM PostCategory p WHERE " +
        "(:name IS NULL OR p.name = :name) AND " +
        "(:isActive is null or p.isActive = :isActive) AND" +
        "(:startDate IS NULL OR p.createdDate >= :startDate) AND " +
        "(:endDate IS NULL OR p.createdDate <= :endDate)")
    List<PostCategory> search(@Param("name") String name,
                              @Param("isActive") Boolean isActive,
                              @Param("startDate") Date startDate,
                              @Param("endDate") Date endDate);


}
