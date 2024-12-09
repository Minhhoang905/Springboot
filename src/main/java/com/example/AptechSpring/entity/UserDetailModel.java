package com.example.AptechSpring.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailModel implements UserDetails {

	private String name;
	private String pass;
	private String role;
//  private List<GrantedAuthority> authorities;

	// Constructor nhận đối tượng LoginEntity và khởi tạo các trường
	public UserDetailModel(LoginEntity loginEntity) {
		this.name = loginEntity.getName();
		this.pass = loginEntity.getPass();
		this.role = loginEntity.getRole();
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}

	public String getRole() {
		return role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Thêm "ROLE_" vào role vì Spring Security yêu cầu định dạng này
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; 
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; 
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; 
	}

	@Override
	public boolean isEnabled() {
		return true;
		
	}
	
	// public UserDetailModel(LoginEntity user){
//        this.username = user.getName();
//        this.password = user.getPass();
//        this.authorities = Stream.of(user.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() { return this.authorities; }
//
//    @Override
//    public String getPassword() { return this.password; }
//
//    @Override
//    public String getUsername() { return this.username; }
//
//    @Override
//    public boolean isAccountNonExpired() { return false; }
//
//    @Override
//    public boolean isAccountNonLocked() { return false; }
//
//    @Override
//    public boolean isCredentialsNonExpired() { return false; }
//
//    @Override
//    public boolean isEnabled() { return false; }

}
