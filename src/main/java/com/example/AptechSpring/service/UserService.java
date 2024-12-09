package com.example.AptechSpring.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.example.AptechSpring.entity.LoginEntity;
import com.example.AptechSpring.repository.LoginRepository;

/*
 * Service chỉ để thực hiện tính toán, không chứa các query
 * Query (database) để bên repository
 * 
 */


// Đánh dấu class này là 1 service của spring
@Service
public class UserService{
	// Tự động inject instance của LoginRepository vào class này
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
    // Phương thức để kiểm tra thông tin user ở database
	public boolean IsValidateUser(String name, String pass) {
		// Tìm kiếm user theo name
		LoginEntity user = loginRepository.findByName(name);
		//Trả về user và check mật khẩu
		return user!= null && user.getPass().equals(pass);
		
	}
	
	//Method boolean để check điều kiện đăng ký
	public boolean IsValid(String name, String pass, ModelMap model) {
		//Check xem tên tài khoản đã có chưa, nếu có thì trả về false
		if (loginRepository.findByName(name) != null) {
			model.addAttribute("mes","Tên người dùng đã tồn tại.");
			return false; // <= Invalid
		}
		//Tạo ifelse check các điều kiện
		if (name.length() > 17) {
			//Kiểm tra nếu độ dài phải nằm trong khoảng 8 đến 16
			model.addAttribute("mes", "Username less than 16 characters");
			return false; // <= Invalid
		}else if (pass.length() < 9 || pass.length() > 21) {
			//Kiểm tra độ dài phải nằm trong khoảng 8 đến 20
			model.addAttribute("mes", "Password must be between 8 and 20 characters");
			return false; // <= Invalid
		}else if (!Pattern.compile("[A-Z]").matcher(pass).find()) {
			model.addAttribute("mes", "Password must contain at least one uppercase letter");
			return false; // <= Invalid
		}else if (!Pattern.compile("[^a-zA-Z0-9]").matcher(pass).find()) {
            model.addAttribute("mes", "Password must contain at least one special character");
            return false; // <= Invalid
		}
		else {
			//Nếu thỏa mãn các điều kiện, gọi phương thức saveUser() để lưu thông tin vào database
			saveUser(name, pass);
			model.addAttribute("okMess", "Register succesfull");
			return true; // <= Valid
		}				
	}
	
	//Method để lưu username và mật khẩu mới (tự tạo query insert, không dùng save)
	private void saveUser(String name, String pass) {
		//Gọi đối tượng từ LoginEntity
		LoginEntity obj = new LoginEntity();
		//Set các thông tin vào đối tượng để lưu vào database
		//Tên tài khoản
		obj.setName(name);
		//Mật khẩu (dùng encoder từ SecurityConfig để mã hóa trong database)
		obj.setPass(passwordEncoder.encode(pass));
		//Roles (mặc định là USER
		obj.setRole("USER");
		//Lưu vào databse với phương thức insert trong Repositoryy 
		loginRepository.insert(obj);
	}

	
	//Method boolean để cập nhập mật khẩu
	public boolean changePass(String username,String oldPassword , String newPassword) {
		//Kiểm tra tính hợp lệ của user 
		LoginEntity loginEntity = loginRepository.findByName(username);
		//Tạo if-else để kiểm tra thông tin và check xem mật khẩu đã nhập có đúng không
		if (loginEntity != null && passwordEncoder.matches(oldPassword, loginEntity.getPass())) {
			//Cập nhập mật khẩu mới với passwordEncoder
			loginEntity.setPass(passwordEncoder.encode(newPassword));
			//Update vào databse
			loginRepository.save(loginEntity);
			//Trả về true
			return true;
		}else {
			//Trả về false nếu không hợp lệ
			return false;
		}
	}
	
	//Method để hiển thị toàn bộ thông tin account
	//Tạo method để tìm kiếm các địa điểm du lịch dựa trên các tham số (parameter) truyền vào
	public List<LoginEntity> displayAccount(Long id,String name, String pass, String role) {
		//Hiển thị toàn bộ thông tin từ entity
		List<LoginEntity> loginResult = loginRepository.findAll();
		
		return loginResult;
	}

}

