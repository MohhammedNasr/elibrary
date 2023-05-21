package com.project.elibrary.repositories;

import org.springframework.stereotype.Repository;

import com.project.elibrary.models.Borrow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository


public interface BorrowRepository extends JpaRepository<Borrow, Long>
 {
    List<Borrow> findByUserID(Long userID);
}

