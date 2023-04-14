package com.project.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.elibrary.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}