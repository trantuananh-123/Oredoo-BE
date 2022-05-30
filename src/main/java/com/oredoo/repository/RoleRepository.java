package com.oredoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oredoo.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
//    Optional<Role> findByName(String name);
}
