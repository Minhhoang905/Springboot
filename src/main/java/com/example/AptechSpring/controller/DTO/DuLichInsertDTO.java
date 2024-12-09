package com.example.AptechSpring.controller.DTO;

public interface DuLichInsertDTO {
	
	//Lấy gettter của ID của địa điểm du lịch (từ entity DuLich)
	Long getId();
	//Lấy gettter của địa điểm du lịch (từ entity DuLich)
	String getDiaDiem();
	//Lấy gettter củatên tỉnh (từ entity DuLich)
	Long getIdTinh();
	//Lấy gettter của tên quận (từ entity DuLich)
	Long getIdQuan();
	//Lấy gettter của tên xã (từ entity DuLich)
	Long getIdXa();
	//Lấy gettter của địa chỉ cụ thể (từ entity DuLich)
	String getDiaChiCuThe();
	//Lấy gettter của mô tả (từ entity DuLich)
	String getMoTa();

}
