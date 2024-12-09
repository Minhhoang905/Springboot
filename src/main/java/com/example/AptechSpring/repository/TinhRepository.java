package com.example.AptechSpring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AptechSpring.entity.Tinh;

//Đánh dấu interface này là một Spring Data Repository của Tinh
@Repository
public interface TinhRepository extends JpaRepository<Tinh, Long>{
	
}
