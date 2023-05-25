package com.project.elibrary.repositories;

import com.project.elibrary.models.Borrow;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByUser_Id(Long userID);
}
