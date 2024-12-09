package com.example.AptechSpring;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    public static void main(String[] args) {
        String password = "hoang";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);
        System.out.println("Mật khẩu đã mã hóa: " + hashedPassword);
    }
}
