package com.example.AptechSpring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;

import com.example.AptechSpring.controller.DTO.DuLichHomepageDTO;
import com.example.AptechSpring.controller.DTO.DuLichInsertDTO;
import com.example.AptechSpring.entity.DuLich;
import com.example.AptechSpring.entity.Quan;
import com.example.AptechSpring.entity.Tinh;
import com.example.AptechSpring.entity.Xa;
import com.example.AptechSpring.service.DuLichService;

//Đánh dấu lớp này là một Controller của Spring
@Controller
// Định nghĩa đường dẫn cơ sở cho tất cả các yêu cầu trong lớp này
@RequestMapping("/home")
public class HomeController {
	// Inject (tiêm) service để gọi các logic vào controller
	@Autowired
	private DuLichService duLichService;

	// Tạo method xử lý yêu cầu GET cho đường dẫn "/search"
	@RequestMapping(path = "/search", method = RequestMethod.GET)
	public String printString(Model model, @ModelAttribute("duLichObject") DuLich duLich,
			Authentication authentication) {
		// Lấy tên người dùng đã đăng nhập từ Authentication
		if (authentication != null) {
			// Lấy tên người dùng và truyền vào model
			model.addAttribute("username", authentication.getName());
		} else {
			// Nếu không có thông tin đăng nhập, có thể trả về thông báo hoặc redirect
			model.addAttribute("username", "Nhà lữ hành");
		}

		// Tạo đối tượng ở lớp DuLichHomepageDTO
		// Gọi phương thức `getDuLichHomepage` ở service để lấy dữ liệu cần thiết cho
		// @controller
		DuLichHomepageDTO duLichHomepage = duLichService.getDuLichHomepage(duLich);
		// Thêm thuộc tính "message" vào model với thông điệp
		model.addAttribute("mes", "Hello to spring MVC");

		// Gọi phương thức xác định vai trò của user từ @Service
		String role = duLichService.getCurrentUserRole();
		// Thêm thông tin về role vào form ở html
		model.addAttribute("role", role);

		// Thêm list các đối tượng để hiển thị ở dropdown
		// Danh sách các tỉnh lấy từ DTO
		model.addAttribute("tinhList", duLichHomepage.getTinhList());
		// Danh sách các quận lấy từ DTO
		model.addAttribute("quanList", duLichHomepage.getQuanList());
		// Danh sách các xã lấy từ DTO
		model.addAttribute("xaList", duLichHomepage.getXaList());

		// Thêm các đối tượng rỗng để binding với Thymeleaf
		// Đối tượng `Tinh` rỗng để binding khi thêm mới hoặc chỉnh sửa
		model.addAttribute("tinh", new Tinh());
		// Đối tượng `Quan` rỗng để binding khi thêm mới hoặc chỉnh sửa
		model.addAttribute("quan", new Quan());
		// Đối tượng `Xa` rỗng để binding khi thêm mới hoặc chỉnh sửa
		model.addAttribute("xa", new Xa());
		// Đối tượng rỗng để binding với th:object ở form
		model.addAttribute("duLichObject", new DuLich());

		// Thêm danh sách kết quả tìm kiếm địa điểm du lịch ở `DuLichHomepageDTO` vào
		// model
		model.addAttribute("duLichResults", duLichHomepage.getDuLichResults());
		// Trả về tên file template Thymeleaf (home.html)
		return "home";
	}

	// Tạo method search để xử lý yêu cầu POST cho đường dẫn "/search"
	@RequestMapping(path = "/doSearch", method = RequestMethod.POST)
	public String search(Model model, @ModelAttribute("duLichObject") DuLich duLich, Authentication authentication) {
		// Lấy tên người dùng đã đăng nhập từ Authentication
		if (authentication != null) {
			// Lấy tên người dùng và truyền vào model
			model.addAttribute("username", authentication.getName());
		} else {
			// Nếu không có thông tin đăng nhập, có thể trả về thông báo hoặc redirect
			model.addAttribute("username", "Nhà lữ hành");
		}

		// Tạo đối tượng ở lớp DuLichHomepageDTO
		// Gọi phương thức `duLichHomePageWithSearch` service để lấy dữ liệu cần thiết
		// cho @controller
		DuLichHomepageDTO duLichHomepageDTO = duLichService.duLichHomePageWithSearch(duLich);

		// Gọi phương thức xác định vai trò của user từ @Service
		String role = duLichService.getCurrentUserRole();
		// Thêm thông tin về role vào form ở html
		model.addAttribute("role", role);

		// Thêm thuộc tính "message" vào model với thông điệp
		model.addAttribute("mes", "Hello to spring MVC");

		// Thêm list các đối tượng để hiển thị ở dropdown
		// Danh sách các tỉnh lấy từ DuLichHomepageDTO
		model.addAttribute("tinhList", duLichHomepageDTO.getTinhList());
		// Danh sách các quận lấy từ DuLichHomepageDTO
		model.addAttribute("quanList", duLichHomepageDTO.getQuanList());
		// Danh sách các xã lấy từ DTO
		model.addAttribute("xaList", duLichHomepageDTO.getXaList());

		// Thêm các đối tượng rỗng để binding với Thymeleaf
		// Đối tượng `Tinh` rỗng để binding khi thêm mới hoặc chỉnh sửa
		model.addAttribute("tinh", new Tinh());
		// Đối tượng `Quan` rỗng để binding khi thêm mới hoặc chỉnh sửa
		model.addAttribute("quan", new Quan());
		// Đối tượng `Xa` rỗng để binding khi thêm mới hoặc chỉnh sửa
		model.addAttribute("xa", new Xa());
		// Đối tượng rỗng để binding với th:object ở form
		model.addAttribute("duLichObject", new DuLich());

		// Thêm danh sách kết quả tìm kiếm địa điểm du lịch ở `DuLichHomepageDTO` vào
		// model
		model.addAttribute("duLichResults", duLichHomepageDTO.getDuLichResults());

		// model.addAttribute("dulich111", dulich);

		// <td><input type="text" class="form-control" name="id" th:value="${dulich.id
		// != null} ? ${dulich.id} : ''"></td>

		// Trả về tên tệp html
		return "home";
	}

	// Tạo method xử lý yêu cầu GET cho đường dẫn "/insert"
	@RequestMapping(path = "/insert", method = RequestMethod.GET)
	public String loadingInsertInfo(Model model) {
		// Tạo đối tượng ở lớp DuLichHomepageDTO
		// Gọi phương thức `getDuLichDropDown` service để lấy dữ liệu cần thiết cho
		// @controller
		DuLichHomepageDTO DuLichDropdown = duLichService.getDuLichDropDown();
		// Thêm thuộc tính "message" vào model với thông điệp
		model.addAttribute("mes", "Hello to spring MVC");

		// Gọi phương thức xác định vai trò của user từ @Service
		String role = duLichService.getCurrentUserRole();
		// Thêm thông tin về role vào form ở html
		model.addAttribute("role", role);

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
		// Trả về html "inserInfo"
		return "insertInfo";
	}

	// Tạo method để nhập thông tin vào database
	@RequestMapping(path = "/doInsert", method = RequestMethod.POST)
	public String insertInfo(@ModelAttribute DuLichInsertDTO duLichInsertDTO, ModelMap model) {
		// Tạo đối tượng ở lớp DuLichHomepageDTO
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

		// Tạo biến, và lấu thông lấy phương thức từ interface DuLichInsertDTO
		// Tên địa điểm
		String diaDiemDuLich = duLichInsertDTO.getDiaDiem();
		// Tỉnh
		Long idTinh = duLichInsertDTO.getIdTinh();
		// Quận
		Long idQuan = duLichInsertDTO.getIdQuan();
		// Xã
		Long idXa = duLichInsertDTO.getIdXa();
		// Địa chỉ cụ thể
		String diaChiCuThe = duLichInsertDTO.getDiaChiCuThe();
		// Mô tả
		String moTa = duLichInsertDTO.getMoTa();
		// Đưa các thông tin vào database bằng method ở service
		duLichService.insertInfo(diaDiemDuLich, idTinh, idQuan, idXa, diaChiCuThe, moTa);

		// Trả về html "home"
		return "redirect:/home/search";
	}

	// Tạo method xử lý yêu cầu GET cho đường dẫn "/update"
	@RequestMapping(path = "/update", method = RequestMethod.GET)
	public String loadingUpdateInfo(Model model, @RequestParam("id") Long id) {
		// Tạo đối tượng ở lớp DuLichHomepageDTO
		// Gọi phương thức `getDuLichDropDown` service để lấy dữ liệu cần thiết cho
		// @controller
		DuLichHomepageDTO DuLichDropdown = duLichService.getDuLichDropDown();

		// Gọi phương thức xác định vai trò của user từ @Service
		String role = duLichService.getCurrentUserRole();
		// Thêm thông tin về role vào form ở html
		model.addAttribute("role", role);

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
		// Lấy đối tượng DuLich để cập nhật (gọi từ service)
		Optional<DuLich> duLichUpdate = duLichService.findByid(id);
		model.addAttribute("duLichUpdate", duLichUpdate);

		// Trả về html "updateInfo"
		return "updateInfo";
	}

	// Tạo method xử lý yêu cầu POST cho đường dẫn "/doUpdate"
	@RequestMapping(path = "/doUpdate", method = RequestMethod.POST)
	public String updateInfo(@ModelAttribute("duLichUpdate") DuLichInsertDTO duLichInsertDTO, ModelMap model) {
		// Tạo đối tượng ở lớp DuLichHomepageDTO
		// Gọi phương thức `getDuLichDropDown` service để lấy dữ liệu cần thiết cho
		// @controller
		DuLichHomepageDTO DuLichDropdown = duLichService.getDuLichDropDown();

		// Gọi phương thức xác định vai trò của user từ @Service
		String role = duLichService.getCurrentUserRole();
		// Thêm thông tin về role vào form ở html
		model.addAttribute("role", role);

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
		Long id = duLichInsertDTO.getId();
		// Tên địa điểm
		String diaDiemDuLich = duLichInsertDTO.getDiaDiem();
		// Tỉnh
		Long idTinh = duLichInsertDTO.getIdTinh();
		// Quận
		Long idQuan = duLichInsertDTO.getIdQuan();
		// Xã
		Long idXa = duLichInsertDTO.getIdXa();
		// Địa chỉ cụ thể
		String diaChiCuThe = duLichInsertDTO.getDiaChiCuThe();
		// Mô tả
		String moTa = duLichInsertDTO.getMoTa();
		// Đưa các thông tin vào database bằng method ở service
		duLichService.updateInfoById(id, diaDiemDuLich, idTinh, idQuan, idXa, diaChiCuThe, moTa);
		// Trả về html "home"
		return "redirect:/home/search";
	}

	// Tạo method xử lý yêu cầu GET cho đường dẫn "/doDelete"
	@RequestMapping(path = "/doDelete", method = RequestMethod.GET)
	public String deleteInfo(Model model, @RequestParam(value = "id") Long id) {
		// Gọi phương thức xác định vai trò của user từ @Service
		String role = duLichService.getCurrentUserRole();
		// Thêm thông tin về role vào form ở html
		model.addAttribute("role", role);

		// Xóa thông tin cần xóa bằng method ở service
		duLichService.deleteInfoById(id);
		// Trả về lại html "home"
		return "redirect:/home/search";
	}

}
