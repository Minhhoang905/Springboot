package com.example.AptechSpring.form;

// Tạo thuộc tính cho thực thể DuLich (và tên phải khớp với thuộc tính bên entity)
public class DuLichForm {	
	// Id
	private Long id;
	// Địa điểm
	private String diaDiem;
	// Id Tỉnh
	private Long idTinh;
	// Id Quận
	private Long idQuan;
	// Id Xã
	private Long idXa;
	// Địa chỉ cụ thể
	private String diaChiCuThe;
	// Mô tả
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
