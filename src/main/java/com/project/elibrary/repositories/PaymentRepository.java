package com.project.elibrary.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.elibrary.models.Pay;
@Repository
public interface PaymentRepository extends JpaRepository<Pay,Long> 
{
 
}