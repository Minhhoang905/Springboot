package com.example.AptechSpring.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AptechSpring.controller.DTO.DuLichHomepageDTO;
import com.example.AptechSpring.controller.DTO.DuLichResultDTO;
import com.example.AptechSpring.entity.DuLich;
import com.example.AptechSpring.entity.Quan;
import com.example.AptechSpring.entity.Tinh;
import com.example.AptechSpring.entity.Xa;
import com.example.AptechSpring.form.DuLichForm;
import com.example.AptechSpring.repository.QuanRepository;
import com.example.AptechSpring.repository.XaRepository;
import com.example.AptechSpring.service.DuLichService;

@RestController
@RequestMapping("/api")
public class HomeControllerAPI {

	@Autowired
	private DuLichService duLichService;
	@Autowired
	private QuanRepository quanRepository;
	@Autowired
	private XaRepository xaRepository;

	// Tạo method để get all Quận
	@GetMapping(path = "/getQuanDropdown")
	public List<Quan> getAllQuan(@RequestParam("idTinh") Long idTinh) {
		System.out.println("Id Tỉnh được nhận từ front-end: " + idTinh);
		List<Quan> quanList = quanRepository.findQuanByIdTinh(idTinh);
		System.out.println("Danh sách quận: " + quanList);
		return quanList;
	}

	// Tạo method để get all Xã
	@GetMapping(path = "/getXaDropdown")
	public List<Xa> getAllXa(@RequestParam("idQuan") Long idQuan) {
		System.out.println("Id Tỉnh được nhận từ front-end: " + idQuan);
		List<Xa> xaList = xaRepository.findXaByIdQuan(idQuan);
		System.out.println("Danh sách xã: " + xaList);
		return xaList;
	}

	// Thêm 1 entity mới vào database (POST)
	@PostMapping(path = "/doInsert")
	DuLich insertInfo(@RequestBody DuLichForm duLichForm, ModelMap model) {
		// Thêm thuộc tính "message" vào mô hình với giá trị như sau
		model.addAttribute("mes", "Hello to spring MVC");

		// Gọi phương thức `getDuLichDropDown` service để lấy dữ liệu cần thiết cho
		// @controller
		DuLichHomepageDTO DuLichDropdown = duLichService.getDuLichDropDown();
		// Thêm thuộc tính "message" vào model với thông điệp
		model.addAttribute("mes", "Hello to spring MVC");

		// Thêm list các đối tượng để hiển thị ở dropdown
		// Danh sách các tỉnh lấy từ DuLichHomepageDTO
		model.addAttribute("tinhList", DuLichDropdown.getTinhList());
		// Danh sách các quận lấy từ DuLichHomepageDTO
		model.addAttribute("quanList", DuLichDropdown.getQuanList());
		// Danh sách các xã lấy từ DTO
		model.addAttribute("xaList", DuLichDropdown.getXaList());

		// Thêm các đối tượng rỗng để binding với Thymeleaf
		// Đối tượng `Tinh` rỗng để binding khi thêm mới hoặc chỉnh sửa
		model.addAttribute("tinh", new Tinh());
		// Đối tượng `Quan` rỗng để binding khi thêm mới hoặc chỉnh sửa
		model.addAttribute("quan", new Quan());
		// Đối tượng `Xa` rỗng để binding khi thêm mới hoặc chỉnh sửa
		model.addAttribute("xa", new Xa());

		// Tên địa điểm
		String diaDiemDuLich = duLichForm.getDiaDiem();
		// Tỉnh
		Long idTinh = duLichForm.getIdTinh();
		// Quận
		Long idQuan = duLichForm.getIdQuan();
		// Xã
		Long idXa = duLichForm.getIdXa();
		// Địa chỉ cụ thể
		String diaChiCuThe = duLichForm.getDiaChiCuThe();
		// Mô tả
		String moTa = duLichForm.getMoTa();
		// Đưa các thông tin vào database bằng method ở service
		// duLichService.insertInfo(diaDiemDuLich, idTinh, idQuan, idXa, diaChiCuThe,
		// moTa);

		// Trả về html "home"
		return duLichService.insertInfo(diaDiemDuLich, idTinh, idQuan, idXa, diaChiCuThe, moTa);
	}

	// Lấy thông tin của 1 entity (GET)
	@GetMapping(path = "/home")
	public List<DuLichResultDTO> printString(Model model, @ModelAttribute("duLichObject") DuLich duLich) {
		// Lấy id từ service Dulich
		Long idDuLich = duLich.getId();
		// Lấy địa điểm từ service Dulich
		String diaDiem = duLich.getDiaDiem();
		// if-else rút gọn của id Tinh (Nếu (điều kiện ?) thì lấy giá trị trước (:) ,
		// còn không thì lấy sau (:)
		Long idTinh = (duLich.getIdTinh() != null) ? duLich.getIdTinh() : null;
		// if-else rút gọn của idQuan (Nếu (điều kiện ?) thì lấy giá trị trước (:) , còn
		// không thì lấy sau (:)
		Long idQuan = (duLich.getIdQuan() != null) ? duLich.getIdQuan() : null;
		// if-else rút gọn của idXa (Nếu (điều kiện ?) thì lấy giá trị trước (:) , còn
		// không thì lấy sau (:)
		Long idXa = (duLich.getIdXa() != null) ? duLich.getIdXa() : null;
		// Lấy địa chỉ cụ thể từ service Dulich
		String diaChiCuThe = duLich.getDiaChiCuThe();
		// Lấy mô tả từ service Dulich
		String moTa = duLich.getMoTa();

		// Hiển thị thông tin theo yêu cầu từ List và lấy đối tượng từ interface
		List<DuLichResultDTO> duLichResults = duLichService.findId(idDuLich, diaDiem, idTinh, idQuan, idXa, diaChiCuThe,
				moTa);
		// Trả về tên tệp html
		return duLichResults;
	}

	// Cập nhập thông tin của 1 entity (PUT)
	@PutMapping(path = "/doUpdate")
	public DuLich updateInfo(@RequestBody DuLichForm duLichForm, ModelMap model) {
		// Gọi phương thức `getDuLichDropDown` service để lấy dữ liệu cần thiết cho
		// @controller
		DuLichHomepageDTO DuLichDropdown = duLichService.getDuLichDropDown();
		// Thêm thuộc tính "message" vào model với thông điệp
		model.addAttribute("mes", "Hello to spring MVC");

		// Thêm list các đối tượng để hiển thị ở dropdown
		// Danh sách các tỉnh lấy từ DuLichHomepageDTO
		model.addAttribute("tinhList", DuLichDropdown.getTinhList());
		// Danh sách các quận lấy từ DuLichHomepageDTO
		model.addAttribute("quanList", DuLichDropdown.getQuanList());
		// Danh sách các xã lấy từ DTO
		model.addAttribute("xaList", DuLichDropdown.getXaList());

		// Thêm các đối tượng rỗng để binding với Thymeleaf
		// Đối tượng `Tinh` rỗng để binding khi thêm mới hoặc chỉnh sửa
		model.addAttribute("tinh", new Tinh());
		// Đối tượng `Quan` rỗng để binding khi thêm mới hoặc chỉnh sửa
		model.addAttribute("quan", new Quan());
		// Đối tượng `Xa` rỗng để binding khi thêm mới hoặc chỉnh sửa
		model.addAttribute("xa", new Xa());

		// id
		Long id = duLichForm.getId();
		// Tên địa điểm
		String diaDiemDuLich = duLichForm.getDiaDiem();
		// Tỉnh
		Long idTinh = duLichForm.getIdTinh();
		// Quận
		Long idQuan = duLichForm.getIdQuan();
		// Xã
		Long idXa = duLichForm.getIdXa();
		// Địa chỉ cụ thể
		String diaChiCuThe = duLichForm.getDiaChiCuThe();
		// Mô tả
		String moTa = duLichForm.getMoTa();
		// Trả về đối tượng
		return duLichService.updateInfoById(id, diaDiemDuLich, idTinh, idQuan, idXa, diaChiCuThe, moTa);
	}

	// Xóa 1 entity khỏi database (DELETE)
	@DeleteMapping(path = "/doDelete/{id}")
	public ResponseEntity<String> deleteInfo(Model model, @PathVariable("id") Long id) {
		// Tạo 1 boolean để xóa thông tin cần xóa bằng method ở service và trả về kết
		// quả
		duLichService.deleteInfoById(id);
		// Trả về lại html "home"
		return new ResponseEntity<>("id: " + id + " đã bị xóa", HttpStatus.OK);
	}

}
