package com.example.AptechSpring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
//Đánh dấu class này là 1 entity API, đại diện cho table "DuLich" trong database
@Entity
@Table(name="DULICH")
public class DuLich {
	//Tạo các thuộc tính = tên trường trong database

	// id (Tương đương với khóa chính)
	@Id
    // Tự động sinh giá trị cho khóa chính (ID) khi một bản ghi mới được tạo
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Địa điểm
	@Column(name = "DIA_DIEM")
	@NotNull
	private String diaDiem;
	
	@Column(name = "ID_TINH")
	private Long idTinh;
	
	@Column(name = "ID_QUAN")
	private Long idQuan;
	
	@Column(name = "ID_XA")
	private Long idXa;
	
	//Địa chỉ cụ thể
	@Column(name = "DIA_CHI_CU_THE")
	@NotNull
	private String diaChiCuThe;
	
	//Mô tả
	@Column(name = "MOTA")
	private String moTa;

	//Tạo Getter và setter
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getDiaDiem() {
		return diaDiem;
	}

	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}

	public Long getIdTinh() {
		return idTinh;
	}

	public void setIdTinh(Long idTinh) {
		this.idTinh = idTinh;
	}

	public Long getIdQuan() {
		return idQuan;
	}

	public void setIdQuan(Long idQuan) {
		this.idQuan = idQuan;
	}

	public Long getIdXa() {
		return idXa;
	}

	public void setIdXa(Long idXa) {
		this.idXa = idXa;
	}

	public String getDiaChiCuThe() {
		return diaChiCuThe;
	}

	public void setDiaChiCuThe(String diaChiCuThe) {
		this.diaChiCuThe = diaChiCuThe;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Override
	public String toString() {
		return "DuLich [id=" + id + ", diaDiem=" + diaDiem + ", idTinh=" + idTinh + ", idQuan=" + idQuan + ", idXa="
				+ idXa + ", diaChiCuThe=" + diaChiCuThe + ", moTa=" + moTa + "]";
	}	
}
