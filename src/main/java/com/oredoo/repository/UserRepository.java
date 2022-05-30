package com.oredoo.repository;

import com.oredoo.dto.response.UserPostResponseDTO;
import com.oredoo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT COUNT(p.id) FROM Post p WHERE p.userId = :userId")
    Integer countUserPost(@Param("userId") String userId);

    @Query(value = "SELECT DISTINCT(u) FROM User u JOIN u.roles r ON (:roles IS NULL OR r.id = :roles) WHERE " +
        "(:username IS NULL OR u.username = :username) AND " +
        "(:email IS NULL OR u.email = :email) AND " +
        "(:phone IS NULL OR u.phone = :phone) AND " +
        "(:isActive IS NULL OR u.isActive = :isActive) AND " +
        "(:startDate IS NULL OR u.createdDate >= :startDate) AND " +
        "(:endDate IS NULL OR u.createdDate < :endDate)")
    List<User> search(@Param("username") String username, @Param("email") String email, @Param("phone") String phone,
                      @Param("isActive") Boolean isActive, @Param("startDate") LocalDateTime startDate,
                      @Param("endDate") LocalDateTime endDate, @Param("roles") Integer roles);
}
