package com.example.AptechSpring.security;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//Đánh dấu class này 1 lớp cấu hình (configuration)của spring
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.AptechSpring.service.iml.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
	
//	@Bean này thay thế cho WebSecurityConfigureAdapter
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
					.requestMatchers("/home/search").permitAll() //Không đăng nhập: Chỉ được phép xem home
					.requestMatchers("/doLogin", "/register", "/doRegister", "/changePass", "/doChangePass").permitAll() 
					.requestMatchers("/home/doSearch").hasAnyRole("ADMIN", "USER") //Cả user và admin thực hiện search
					.requestMatchers("/home/insert", "/home/update", "/home/doUpdate", "/home/doDelete").hasRole("ADMIN") //admin: có toàn quyền
					.anyRequest().authenticated() //Yêu cầu khác phải đăng nhập
			)
			.formLogin(login -> login
					.loginPage("/login") //Trang login
					.defaultSuccessUrl("/home/search", true) //Chuyển hướng sau khi đăng nhập thành công
					.permitAll() //Ai cũng có thể truy cập
			)
			.logout(logout -> logout
					.logoutUrl("/logout") //Đường dẫn logout
//					.logoutSuccessUrl("/home/home1")
					.permitAll()
			);
		
		//Hoàn tất cấu hình bảo mật và trả về SecurityFilterChain
		return http.build();
	}
		
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
