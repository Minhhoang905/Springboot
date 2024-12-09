package com.example.AptechSpring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
@Entity
public class Xa {
	//Tạo các thuộc tính = tên trường trong database

	// id (Tương đương với khóa chính)
	//ID của xã
	@Id
	@NotNull
	@Column(name = "ID_XA")
	private Long idXa;
	//Tên quận
	@Column(name = "TEN_XA")
	@NotNull
	private String tenXa;
	//Mô tả
	@Column(name = "MOTA")
	@Null
	private String moTa;
	
	//Getter và Setter
	public Long getIdXa() {
		return idXa;
	}
	public void setIdXa(Long idXa) {
		this.idXa = idXa;
	}
	public String getTenXa() {
		return tenXa;
	}
	public void setTenXa(String tenXa) {
		this.tenXa = tenXa;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
	@Override
	public String toString() {
		return "Xa [idXa=" + idXa + ", tenXa=" + tenXa + ", moTa=" + moTa + "]";
	}	
}
