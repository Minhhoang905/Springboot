package com.example.AptechSpring.controller.DTO;
/**
 * Interface DuLichResultDTO là một Data Transfer Object (DTO) dùng để truyền tải
 * các data cần thiết của đối tượng DuLich mà không cần toàn bộ entity
 * Interface này định nghĩa các phương thức getter cần có, giúp việc lấy data dễ dàng hơn.
 */

public interface DuLichResultDTO {
	
	//Lấy gettter của ID của địa điểm du lịch (từ entity DuLich)
	Long getId();
	//Lấy gettter của địa điểm du lịch (từ entity DuLich)
	String getDiaDiem();
	//Lấy gettter củatên tỉnh (từ entity Tinh)
	String getTenTinh();
	//Lấy gettter của tên quận (từ entity Quan)
	String getTenQuan();
	//Lấy gettter của tên xã (từ entity Xa)
	String getTenXa();
	//Lấy gettter của địa chỉ cụ thể (từ entity DuLich)
	String getDiaChiCuThe();
	//Lấy gettter của mô tả (từ entity DuLich)
	String getMoTa();
}
