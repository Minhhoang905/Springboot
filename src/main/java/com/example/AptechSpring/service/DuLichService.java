package com.example.AptechSpring.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.AptechSpring.controller.DTO.DuLichHomepageDTO;
import com.example.AptechSpring.controller.DTO.DuLichResultDTO;
import com.example.AptechSpring.entity.DuLich;
import com.example.AptechSpring.repository.DuLichRepository;
import com.example.AptechSpring.repository.QuanRepository;
import com.example.AptechSpring.repository.TinhRepository;
import com.example.AptechSpring.repository.XaRepository;

//Đánh dấu class này là 1 service
@Service
public class DuLichService {
	// Inject instance của các Repository vào class này
	@Autowired
	private DuLichRepository duLichRepository;
	@Autowired
	private TinhRepository tinhRepository;
	@Autowired
	private QuanRepository quanRepository;
	@Autowired
	private XaRepository xaRepository;

	// Tạo method để hiển thị toàn bộ thông tin entity của DuLich
	public List<DuLich> getAllInfoDuLich() {
		return duLichRepository.findAll();
	}

	// Method hiển thị các dropdown
	public DuLichHomepageDTO getDuLichDropDown() {
		// Tạo DTO chứa các dữ liệu cần thiết cho trang home
		DuLichHomepageDTO duLichHomepageDTO = new DuLichHomepageDTO();

		// Lấy danh sách Tỉnh, quận, xã từ phương thức ở repository
		// Tỉnh
		duLichHomepageDTO.setTinhList(tinhRepository.findAll());
		// Quận
		duLichHomepageDTO.setQuanList(quanRepository.findAll());
		// Xã
		duLichHomepageDTO.setXaList(xaRepository.findAll());
		// Trả về đối tượng của DuLichHomepageDTO
		return duLichHomepageDTO;
	}

	// Tạo method để xác định role của người đăng nhập
	public String getCurrentUserRole() {
		// Lấy đối tượng người dùng hiện tại từ SecurityContextHolder
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// Tạo tham số với kiểu String chứa danh sách quyền (authorities) của user,
		// sau đó chuyển đổi bằng 1 chuỗi các quyền phân tách bằng dấu phẩy
		String role = currentUser.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority())
				.collect(Collectors.joining(","));
		// Trả về role của người dùng
		return role;
	}

	// Tạo method để tìm kiếm các địa điểm du lịch dựa trên các tham số (parameter)
	// truyền vào
	public List<DuLichResultDTO> findId(Long id, String DiaDiem, Long idTinh, Long idQuan, Long idXa,
			String DiaChiCuThe, String Mota) {
		// Cách 1: Dùng Map để nhận kết quả
		// Ở cách này, kết quả trả về là một danh sách Map, trong đó mỗi Map đại diện
		// cho 1 record từ Database.
		// Cách này linh hoạt hơn vì không yêu cầu một DTO cụ thể, nhưng việc truy xuất
		// giá trị sẽ phức tạp hơn và dễ gặp lỗi kiểu dữ liệu.

		// List<Map<String, Object>> duLich = dulichRepository.findId(id, DiaDiem,
		// idTinh, idQuan, idXa, DiaChiCuThe, Mota);

		// Cách 2: Dùng DTO để nhận kết quả
		// Trả về danh sách DTO, mỗi DTO chứa dữ liệu của một record với các getter.
		// Cách này an toàn hơn về mặt kiểu dữ liệu và giúp tránh lỗi kiểu dữ liệu khi
		// truy xuất các giá trị trong kết quả.
		List<DuLichResultDTO> duLich = duLichRepository.selectAllInfo(id, DiaDiem, idTinh, idQuan, idXa, DiaChiCuThe,
				Mota);
		return duLich;
	}

	// Tạo phương thức để chuẩn bị dữ liệu cho trang home/search
	public DuLichHomepageDTO getDuLichHomepage(DuLich dulich) {
		// Gọi đối tượng từ lớp DuLichHomepageDTO và gán phương thức `getDuLichHomepage`
		// để lấy danh sách
		DuLichHomepageDTO duLichHomepageDTO = getDuLichDropDown();

		// Lấy danh sách của DuLichResultDTO theo từ entity của dulich
		// id
		Long idDuLich = dulich.getId();
		// Địa điểm
		String diaDiem = dulich.getDiaDiem();
		// if-else rút gọn của id Tinh (Nếu (điều kiện ?) thì lấy giá trị trước (:) ,
		// còn không thì lấy sau (:)
		Long idTinh = (dulich.getIdTinh() != null) ? dulich.getIdTinh() : null;
		// if-else rút gọn của idQuan (Nếu (điều kiện ?) thì lấy giá trị trước (:) , còn
		// không thì lấy sau (:)
		Long idQuan = (dulich.getIdQuan() != null) ? dulich.getIdQuan() : null;
		// if-else rút gọn của idXa (Nếu (điều kiện ?) thì lấy giá trị trước (:) , còn
		// không thì lấy sau (:)
		Long idXa = (dulich.getIdXa() != null) ? dulich.getIdXa() : null;
		// Địa chỉ cụ thể
		String diaChiCuThe = dulich.getDiaChiCuThe();
		// Mô tả
		String moTa = dulich.getMoTa();

		// Gọi phương thức findID ở service để hiển thị thông tin theo yêu cầu từ List
		List<DuLichResultDTO> duLichResultDTO = findId(idDuLich, diaDiem, idTinh, idQuan, idXa, diaChiCuThe, moTa);
		// Set danh sách trên vào danh sách duLichResults ở lớp DuLichHomepageDTO
		duLichHomepageDTO.setDuLichResults(duLichResultDTO);

		// Trả về đối tượng của DuLichHomepageDTO
		return duLichHomepageDTO;
	}

	// Tạo phương thức trả về điều kiện tìm kiếm khi nhấn button `search` ở trang
	// home/search
	private boolean isSearchValid(DuLich duLich) {
		// Trả về điều kiện tìm kiếm
		return (duLich.getId() != null) ||
				(duLich.getDiaDiem() != null && !duLich.getDiaDiem().trim().isEmpty()) ||
				(duLich.getIdTinh() != null) ||
				(duLich.getIdQuan() != null) ||
				(duLich.getIdXa() != null) ||
				(duLich.getDiaChiCuThe() != null && duLich.getDiaChiCuThe().trim().isEmpty()) ||
				(duLich.getMoTa() != null && duLich.getMoTa().trim().isEmpty());
	}

	// Phương thức để thực hiện lệnh tìm kiếm cho trang home/search
	public DuLichHomepageDTO duLichHomePageWithSearch(DuLich duLich) {
		// Gọi đối tượng từ lớp DuLichHomepageDTO và gán phương thức `getDuLichHomepage`
		// để lấy danh sách
		DuLichHomepageDTO duLichHomepageDTO = getDuLichDropDown();

		// Gọi phương thức isSearchValid ở Service để kiểm tra điều kiện
		if (isSearchValid(duLich)) {
			// Gọi phương thức findID ở service để hiển thị thông tin theo yêu cầu từ List
			List<DuLichResultDTO> duLichResultDTO = findId(duLich.getId(), duLich.getDiaDiem(), duLich.getIdTinh(),
					duLich.getIdQuan(), duLich.getIdXa(), duLich.getDiaChiCuThe(), duLich.getMoTa());
			// Set danh sách trên vào danh sách duLichResults ở lớp DuLichHomepageDTO
			duLichHomepageDTO.setDuLichResults(duLichResultDTO);
		}
		// Trả về đối tượng của DuLichHomepageDTO
		return duLichHomepageDTO;
	}

	// Method tìm địa điểm theo id
	public Optional<DuLich> findByid(Long id) {
		return duLichRepository.findById(id);
	}

	// Method cập nhập thông tin địa điểm du lịch
	public DuLich updateInfoById(Long id, String DiaDiem, Long idTinh, Long idQuan, Long idXa, String DiaChiCuThe,
			String Mota) {
		// Tạo đối tượng theo entity DuLich, trả về theo `id`, nếu không tìm thấy thì
		// trả về `null`
		DuLich duLich = duLichRepository.findById(id).orElse(null);
		// Cập nhập các thuộc tính từ entity `DuLich`
		// Set id
		duLich.setId(id);
		// Set địa điểm
		duLich.setDiaDiem(DiaDiem);
		// Set id Tỉnh
		duLich.setIdTinh(idTinh);
		// Set id Quận
		duLich.setIdQuan(idQuan);
		// Set id Xã
		duLich.setIdXa(idXa);
		// Set địa chỉ cụ thể
		duLich.setDiaChiCuThe(DiaChiCuThe);
		// Set mô tả
		duLich.setMoTa(Mota);
		// Lưu object vào repository
		return duLichRepository.save(duLich);
	}

	// Method insert thông tin
	public DuLich insertInfo(String DiaDiem, Long idTinh, Long idQuan, Long idXa, String DiaChiCuThe, String Mota) {
		// Tạo đối tượng du lịch
		DuLich duLich = new DuLich();
		// Cập nhập các thuộc tính từ entity `DuLich`
		// Set địa điểm
		duLich.setDiaDiem(DiaDiem);
		// Set id Tỉnh
		duLich.setIdTinh(idTinh);
		// Set id Quận
		duLich.setIdQuan(idQuan);
		// Set id Xã
		duLich.setIdXa(idXa);
		// Set địa chỉ cụ thể
		duLich.setDiaChiCuThe(DiaChiCuThe);
		// Set mô tả
		duLich.setMoTa(Mota);
		// Lưu object vào repository
		return duLichRepository.save(duLich);
	}

	// Method xóa thông tin với id
	public void deleteInfoById(Long id) {
		duLichRepository.deleteById(id);
	}

}
