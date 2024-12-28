package com.example.AptechSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AptechSpring.entity.Xa;
import java.util.List;

@Repository
public interface XaRepository extends JpaRepository<Xa, Long> {
    // Tạo phương thức tùy chỉnh để tìm danh sách xã theo id Quận
    List<Xa> findXaByIdQuan(Long idQuan);
}
