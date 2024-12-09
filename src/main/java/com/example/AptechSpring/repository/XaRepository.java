package com.example.AptechSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AptechSpring.entity.Xa;

@Repository
public interface XaRepository extends JpaRepository<Xa, Long>{

}
