package com.carrentsystem.carrentsystem.model.repository;

import com.carrentsystem.carrentsystem.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<Role, Long> {
}
