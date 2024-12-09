package com.example.AptechSpring.controller.DTO;
//Tạo lớp DTO để chứa data cần hiển thị ở search và truyền data từ service sang controller
import java.util.List;

import com.example.AptechSpring.entity.Quan;
import com.example.AptechSpring.entity.Tinh;
import com.example.AptechSpring.entity.Xa;

public class DuLichHomepageDTO {
	//Danh sách tỉnh (để hiển thị trong dropdown)
	private List<Tinh> tinhList;
	//Danh sách quận (để hiển thị trong dropdown)
	private List<Quan> quanList;
	//Danh sách xã (để hiển thị trong dropdown)
	private List<Xa> xaList;
	//Danh sách kết quả tìm kiếm địa điểm du lịch
	private List<DuLichResultDTO> duLichResults;
	//Getter và setter	
	public List<Tinh> getTinhList() {
		return tinhList;
	}
	public void setTinhList(List<Tinh> tinhList) {
		this.tinhList = tinhList;
	}
	public List<Quan> getQuanList() {
		return quanList;
	}
	public void setQuanList(List<Quan> quanList) {
		this.quanList = quanList;
	}
	public List<Xa> getXaList() {
		return xaList;
	}
	public void setXaList(List<Xa> xaList) {
		this.xaList = xaList;
	}
	public List<DuLichResultDTO> getDuLichResults() {
		return duLichResults;
	}
	public void setDuLichResults(List<DuLichResultDTO> duLichResults) {
		this.duLichResults = duLichResults;
	}
	
}
