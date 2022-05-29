package com.oredoo.repository;

import com.oredoo.dto.response.UserPostResponseDTO;
import com.oredoo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT COUNT(p.id) FROM Post p WHERE p.userId = :userId")
    Integer countUserPost(@Param("userId") String userId);
}
