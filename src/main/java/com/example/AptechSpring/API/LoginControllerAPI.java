package com.example.AptechSpring.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AptechSpring.entity.LoginEntity;
import com.example.AptechSpring.form.LoginForm;
import com.example.AptechSpring.service.UserService;

@RestController
@RequestMapping("/loginAPI")
public class LoginControllerAPI {
	@Autowired	
	private UserService userService; //Service chứa các phương thức
	
	//Tạo method để nếu đúng username và mật khẩu thì vào trang home
	@PostMapping(path ="/login")
	public ResponseEntity<?> doLogin(@RequestBody LoginForm loginForm) {

		if (userService.IsValidateUser(loginForm.getUsername(), loginForm.getPassword())) {
			//Trả về thành công
			return ResponseEntity.ok("Login succesfull") ;
		}else {
			//Trả về thất bại
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong name or password");
		}
	}
	
	//Method để hiển thị toàn bộ account (GET)
	@GetMapping(path = "/displayLogin")
	public List<LoginEntity> printAccount(@ModelAttribute LoginEntity loginEntity) {
		//Lấy id từ object loginResultDTO
		Long loginId = loginEntity.getId();
		//Lấy tên từ object loginResultDTO
		String name = loginEntity.getName();
		//Lấy password từ object loginResultDTO
		String pass = loginEntity.getPass();
		//Lấy role từ object loginResultDTO
		String role = loginEntity.getRole();
		//Gọi method từ UserSevice để lấy danh sách tài khoản 
		List<LoginEntity> loginResult = userService.displayAccount(loginId, name, pass, role);
		//Trả về danh sách account 
		return loginResult;
	}
	
	//Tạo method để cập nhập mật khẩu dựa theo tên của username
	@PutMapping(path = "/changePass")
	public ResponseEntity<?> doChangePass(@RequestBody LoginForm loginForm, ModelMap model) {
		if (userService.IsValidateUser(loginForm.getUsername(),loginForm.getPassword())) {
			
			//Gọi method cập nhập mật khẩu mới
			userService.changePass(loginForm.getUsername(), loginForm.getPassword(), loginForm.getNewPassword() );
			 // Trả về phản hồi OK kèm msg
            return ResponseEntity.ok("Password changed successfully!");
		} else {
			// Trả về thất bại
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}
	
}
