package com.fitness.userService.repositories;

import com.fitness.userService.schemas.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailId(String emailId);
}
