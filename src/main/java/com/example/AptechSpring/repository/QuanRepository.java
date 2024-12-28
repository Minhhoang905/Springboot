package com.example.AptechSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AptechSpring.entity.Quan;
import java.util.List;

@Repository
public interface QuanRepository extends JpaRepository<Quan, Long> {
    // Tạo phương thức tùy chỉnh để tìm danh sách quận theo id Tỉnh
    List<Quan> findQuanByIdTinh(Long idTinh);
}
