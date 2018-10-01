package com.carrentsystem.carrentsystem.model.repository;

import com.carrentsystem.carrentsystem.model.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    //@Query("select u from ApplicationUser  u left join fetch u.role r where u.username=:username")
    ApplicationUser findByUsername(String username);

}
