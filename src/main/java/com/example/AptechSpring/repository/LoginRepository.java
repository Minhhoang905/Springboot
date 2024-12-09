package com.example.AptechSpring.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.AptechSpring.entity.LoginEntity;

//Đánh dấu interface này là một Spring Data Repository
@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long>{
	//Tạo query insert vào database
	//Tuy nhiên, đối với các câu lệnh customize thì phải @Query
	//Sau khi tạo @Query thì tạo method như sau (nếu là CRUD thì k cần method cũng ok)
	
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO dbo.LOGIN VALUES " +
            "( " +
                ":#{#me.name}, " +
                ":#{#me.pass}, " +
                " 'USER' " +
            ")", nativeQuery = true)
	int insert(@Param("me") LoginEntity me);
	
	//Tạo method để tìm kiếm tên của người dùng
	@Query(value = "SELECT * FROM dbo.LOGIN WHERE User_name = ?1" , nativeQuery = true)
	LoginEntity findByName(String name);
	

}



/*
 * Tại sao dùng interface thay vì class:
 * 1. Ánh Xạ và Tính Trừu Tượng 
 * (định nghĩa các phương thức mà không cần phải cung cấp chi tiết cài đặt)
 * 2. Tự Động Tạo Các Phương Thức 
 * (Spring Data JPA sẽ tự động tạo ra các phương thức CRUD mà không cần phải viết code cho chúng)
 * 3. Đơn Giản Hóa Mã Nguồn
 * (chỉ cần định nghĩa các phương thức thực sự cần, trong khi các phương thức chuẩn đã có sẵn thông qua kế thừa)
 * 4. Dễ Dàng Thay Thế Cài Đặt 
 * (dễ dàng thay thế hoặc mở rộng các cài đặt của interface này bằng cách tạo một class khác kế thừa từ nó,
 *  mà không cần phải thay đổi mã nguồn ở các nơi khác trong ứng dụng)
 * 5. Hỗ Trợ DI 
 * (Dependency Injection) (Spring dễ dàng quản lý và inject các beans thông qua interfaces)
 */
