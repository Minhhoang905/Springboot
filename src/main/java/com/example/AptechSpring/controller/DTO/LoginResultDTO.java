package com.example.AptechSpring.controller.DTO;

public interface LoginResultDTO {	
	//Lấy getter của các thông tin sau từ entity Login
	//id
	Long getId();
	//name
	String getName();
	//pass
	String getPass();
	//role
	String getRole();
	
}
