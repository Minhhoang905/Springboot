package com.example.AptechSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AptechSpring.form.LoginForm;
import com.example.AptechSpring.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Đánh dấu class này là 1 controller của spring
@Controller
public class LoginController {
	@Autowired
	private UserService userService; // Service chứa các phương thức

	@SuppressWarnings("deprecation") // Loại bỏ cảnh báo deprecation, liên quan đến các phương thức hoặc lớp cũ
	@GetMapping("/login") // Dùng getmapping với authentication từ spring security để đăng nhập
	public String login(Authentication authentication, ModelMap model,
			@RequestParam(value = "error", required = false) String error) {
		// Kiểm tra xem người dùng đã đăng nhập hay chưa thông qua đối tượng
		// Athentication
		if (null != authentication) {
			// Tạo biến String rồi gán tên đăng nhập của người dùng thông qua Authentication
			String username = authentication.getName();
			// Tạo if để kiểm tra xem tên đăng nhập của người dùng có bị rỗng không
			if (!StringUtils.isEmpty(username)) {
				// Nếu đã đăng nhập, điều hướng về link sau
				return "redirect:/home/search";
			}
		}
		// Kiểm tra nếu có tham số "error" trong URL (được gửi từ Spring Security)
		if (error != null) {
			model.addAttribute("errMes", "Wrong name or password");
		}
		// Nếu chưa đăng nhập thì đưa về lại login
		return "login";
	}

	// //Tạo method để hiển thị page login.html
	// @RequestMapping(path ="/login", method=RequestMethod.GET)
	// public String login(ModelMap model) {
	// return "login";
	// }
	//
	// //Tạo method để nếu đúng username và mật khẩu thì vào trang home
	// @RequestMapping(path ="/doLogin", method=RequestMethod.POST)
	// public String doLogin(@ModelAttribute com.example.AptechSpring.form.LoginForm
	// loginForm, ModelMap model) {
	//
	// if (userService.IsValidateUser(loginForm.getUsername(),
	// loginForm.getPassword())) {
	//
	// // Thêm thuộc tính "message" vào mô hình với giá trị như sau
	// model.addAttribute("mes", "LOGIN THANH CONG");
	// return "redirect:/home/home1";
	// }else {
	// model.addAttribute("errMes", "Wrong name or password");
	// return "login";
	// }
	// }
	// Tạo method để hiển thị page register.html
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String register(ModelMap model) {
		return "register";
	}

	// Tạo method để đăng ký thông tin vào database
	@RequestMapping(path = "/doRegister", method = RequestMethod.POST)
	public String doRegister(@ModelAttribute LoginForm loginform, ModelMap model) {

		// Tạo biến boolean và dùng method IsValid() ở UserService để check điều kiện
		// đăng ký
		boolean isRegistered = userService.IsValid(loginform.getUsername(), loginform.getPassword(), model);
		// Nếu thực hiện đăng ký thành công thì dùng method saveUser để lưu vào database
		if (isRegistered) {
			// Nếu đăng ký thành công, trả về trang login
			return "login";
		} else {
			// Nếu không thành công, trả về lại register
			return "register";
		}
	}

	// Tạo method để hiển thị changePass.html
	@RequestMapping(path = "/changePass", method = RequestMethod.GET)
	public String changePass(ModelMap model) {
		return "changePass";
	}

	// Tạo method để cập nhập mật khẩu
	@RequestMapping(path = "/doChangePass", method = RequestMethod.POST)
	public String doChangePass(@ModelAttribute LoginForm loginForm, ModelMap model) {

		// Tạo 1 boolean và gọi method changePass từ UserService để kiểm tra và cập nhập
		// mật khẩu
		boolean isPasswordChanged = userService.changePass(loginForm.getUsername(),
				loginForm.getPassword(),
				loginForm.getNewPassword());
		// Tạo if-else để điều hướng trang web
		if (isPasswordChanged) {
			// Nếu thành công => Thêm thuộc tính "mes" để chuyển hướng tới trang login với
			// thông báo
			model.addAttribute("okMess", "Password changed successfully!");
			// Đi đến trang login.html
			return "login";
		} else {
			// Nếu thất bại => Thêm thuộc tính "mes" ở trang đổi mật khẩu với thông báo
			model.addAttribute("mes", "Invalid username or password");
			// Quay trở lại trang ChangePass.html
			return "changePass";
		}
	}

	// Tạo method để log out và đưa về page login.html
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse reponse) {
		// Tạo đối tượng SecurityContextLogoutHandler để xử lý việc đăng xuất
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		// Gọi phương thức logout() có sẵn của SecurityContextServerLogoutHandle để thực
		// hiện đăng xuất
		logoutHandler.logout(request, reponse, null);
		return "redirect:/login";
	}

}
