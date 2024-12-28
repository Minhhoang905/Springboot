package com.example.AptechSpring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

//Đánh dấu class này là 1 entity API, đại diện cho table "Quan" trong database
@Entity
@Table(name = "Quan", schema = "dbo")
public class Quan {
	// Tạo các thuộc tính = tên trường trong database

	// id (Tương đương với khóa chính)
	// ID của tỉnh
	@Id
	@NotNull
	@Column(name = "ID_QUAN")
	private Long idQuan;
	// Tên quận
	@Column(name = "TEN_QUAN")
	@NotNull
	private String tenQuan;
	// Mô tả
	@Column(name = "MOTA")
	@Null
	private String moTa;
	// Lấy id của Tỉnh từ table Tỉnh
	// ID của tỉnh
	@NotNull
	@Column(name = "ID_TINH")
	private Long idTinh;

	// Getter và Setter
	public Long getIdQuan() {
		return idQuan;
	}

	public void setIdQuan(Long idQuan) {
		this.idQuan = idQuan;
	}

	public String getTenQuan() {
		return tenQuan;
	}

	public void setTenQuan(String tenQuan) {
		this.tenQuan = tenQuan;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public Long getIdTinh() {
		return idTinh;
	}

	public void setIdTinh(Long idTinh) {
		this.idTinh = idTinh;
	}

	@Override
	public String toString() {
		return "Quan [idQuan=" + idQuan + ", tenQuan=" + tenQuan + ", moTa=" + moTa + "]";
	}
}
