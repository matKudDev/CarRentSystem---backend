package com.carrentsystem.carrentsystem.model.repository;

import com.carrentsystem.carrentsystem.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
