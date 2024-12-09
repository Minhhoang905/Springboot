package com.example.AptechSpring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//Đánh dấu class này là 1 entity API, đại diện cho table "Tinh" trong database
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Tinh",schema = "dbo")
public class Tinh {
	
	public Tinh() {}
	//ID của tỉnh
	@Id
	@NotNull
	@Column(name = "ID_TINH")
	private Long idTinh;
	//Tên tỉnh
	@Column(name = "TEN_TINH")
	@NotNull
	private String tenTinh;
	//Mô tả
	@Column(name = "MOTA")
	private String moTa;
	
	//Getter và setter
	public Long getIdTinh() {
		return idTinh;
	}
	public void setIdTinh(Long idTinh) {
		this.idTinh = idTinh;
	}
	public String getTenTinh() {
		return tenTinh;
	}
	public void setTenTinh(String tenTinh) {
		this.tenTinh = tenTinh;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String mota) {
		this.moTa = mota;
	}
	
	@Override
	public String toString() {
		return "Tinh [idTinh=" + idTinh + ", tenTinh=" + tenTinh + ", moTa=" + moTa + "]";
	}
	
	
}
