package com.example.AptechSpring.entity;


import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Đánh dấu lớp này là một entity JPA, đại diện cho bảng LOGIN trong database
@Entity
//Gọi tên của table
@Table(name = "LOGIN") //Sẽ lấy theo tên của Table này (nếu đặt tên class đúng với tên table thì k cần cũng được)
public class LoginEntity {
	//Tạo các thuộc tính = tên trường trong database (k cần đúng tên, nhưng nên đặt giống cho khỏe):
    // Đánh dấu trường này là khóa chính (primary key) của bảng LOGIN
	@Id
    // Tự động sinh giá trị cho khóa chính (ID) khi một bản ghi mới được tạo
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//Tên
	@Column(name = "User_name")
	private String name;
	//Password
	@Column(name = "User_Password")
	private String pass;
	//Role
	@Column(name = "Role")
	private String role = "USER"; //Gán thẳng role là user
	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		//Trả về danh sách quyền của người dùng
//		// Mỗi quyền được biểu diễn bởi một đối tượng SimpleGrantedAuthority.
//		//Thêm "ROLE_" trước vai trò để phù hợp với spring security
//		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
//	}
	
	//Tạo getter và setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
