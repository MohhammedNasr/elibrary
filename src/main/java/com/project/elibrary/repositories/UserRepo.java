package com.project.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.google.common.base.Optional;
import com.project.elibrary.models.User;

public interface UserRepo extends JpaRepository<User, String> {

    public Optional<User> findByEmail(String email);
    Optional <User>findById(Long userID); 

}