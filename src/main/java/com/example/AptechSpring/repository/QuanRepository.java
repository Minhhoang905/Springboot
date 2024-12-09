package com.example.AptechSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AptechSpring.entity.Quan;

@Repository
public interface QuanRepository extends JpaRepository<Quan, Long>{

}
