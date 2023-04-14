package com.project.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.elibrary.models.User;

public interface UserRepo extends JpaRepository<User, String> {
    
}