package com.oredoo.repository;

import com.oredoo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findAllByUserIdOrderByCreatedDateDesc(String id);

    List<Post> findTop4ByOrderByRateDesc();

    List<Post> findAllByOrderByCreatedDateDesc();

    @Query("SELECT DISTINCT(p) FROM Post p JOIN p.tags t ON (COALESCE(:tags) IS NULL OR t.id IN (:tags)) WHERE " +
        "(:authorName IS NULL OR p.authorName = :authorName) AND " +
        "(:categoryId IS NULL OR p.categoryId = :categoryId) AND " +
        "(:startDate IS NULL OR p.createdDate >= :startDate) AND " +
        "(:endDate IS NULL OR p.createdDate <= :endDate)")
    List<Post> search(@Param("authorName") String authorName, @Param("categoryId") Integer categoryId,
                      @Param("tags") List<Integer> tags, @Param("startDate") Date startDate,
                      @Param("endDate") Date endDate);

//    @Query(value = "SELECT DISTINCT p.* FROM post p JOIN " +
//        "(post_tag pt JOIN tag t ON pt.tag_id = t.id) " +
//        "ON pt.post_id = p.id AND " +
//        "(:tags IS NULL OR t.id IN (:tags)) WHERE " +
//        "(:authorName IS NULL OR p.author_name = :authorName) AND " +
//        "(:categoryId IS NULL OR p.category_id = :categoryId)", nativeQuery = true)
//    List<Post> search(@Param("authorName") String authorName, @Param("categoryId") Integer categoryId,
//                      @Param("tags") List<Integer> tags);
}
