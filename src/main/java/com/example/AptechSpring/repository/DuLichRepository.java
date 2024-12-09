package com.example.AptechSpring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.AptechSpring.controller.DTO.DuLichResultDTO;
import com.example.AptechSpring.entity.DuLich;

@Repository
public interface DuLichRepository extends JpaRepository<DuLich, Long>{
	//Method để hiển thị chính xác id `DuLich`
	
	//Cách 1: Dùng Map để nhận kết quả
	
//	@Query(value = "SELECT dl.ID, dl.DIA_DIEM, t.TEN_TINH, q.TEN_QUAN, x.TEN_XA, dl.DIA_CHI_CU_THE, dl.MOTA"
//	+ " FROM dbo.DULICH dl"
//		+ " INNER JOIN dbo.TINH t ON dl.ID_TINH = t.ID_TINH"
//		+ " INNER JOIN dbo.QUAN q ON dl.ID_QUAN = q.ID_QUAN"
//		+ " INNER JOIN dbo.XA x ON dl.ID_XA = x.ID_XA"
//	+ " WHERE"
//		+ " ( ?1 is NULL OR dl.ID = ?1) AND "
//		+ "( ?2 is NULL OR dl.DIA_DIEM LIKE %?2%) AND "
//		+ "( ?3 is NULL OR dl.ID_TINH = ?3) AND"
//		+ "( ?4 is NULL OR dl.ID_QUAN = ?4) AND"
//		+ "( ?5 is NULL OR dl.ID_XA = ?5) AND"
//		+ "( ?6 is NULL OR dl.DIA_CHI_CU_THE LIKE %?6%) AND"
//		+ "( ?7 is NULL OR dl.MOTA LIKE %?7%)", nativeQuery = true)
//List<Map<String, Object>>  findId(Long id,String DiaDiem, Long idTinh, Long idQuan, Long idXa, String DiaChiCuThe, String Mota );
	
	//Cách 2: Dùng interface DTO để nhận kết quả
	@Query(value = "SELECT dl.ID, dl.DIA_DIEM, t.TEN_TINH, q.TEN_QUAN, x.TEN_XA, dl.DIA_CHI_CU_THE, dl.MOTA"
			+ " FROM dbo.DULICH dl"
			+ " INNER JOIN dbo.TINH t ON dl.ID_TINH = t.ID_TINH"
			+ " INNER JOIN dbo.QUAN q ON dl.ID_QUAN = q.ID_QUAN"
			+ " INNER JOIN dbo.XA x ON dl.ID_XA = x.ID_XA"
			+ " WHERE"
			+ " ( ?1 is NULL OR dl.ID = ?1) AND "
			+ "( ?2 is NULL OR dl.DIA_DIEM LIKE %?2%) AND "
			+ "( ?3 is NULL OR dl.ID_TINH = ?3) AND"
			+ "( ?4 is NULL OR dl.ID_QUAN = ?4) AND"
			+ "( ?5 is NULL OR dl.ID_XA = ?5) AND"
			+ "( ?6 is NULL OR dl.DIA_CHI_CU_THE LIKE %?6%) AND"
			+ "( ?7 is NULL OR dl.MOTA LIKE %?7%)", nativeQuery = true)
	List<DuLichResultDTO> selectAllInfo(Long id,String DiaDiem, Long idTinh, Long idQuan, Long idXa, String DiaChiCuThe, String Mota );

}



