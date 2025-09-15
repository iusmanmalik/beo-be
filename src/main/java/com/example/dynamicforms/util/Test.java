package com.example.dynamicforms.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


        System.out.println("args = " + encoder.encode("admin123"));

                System.out.println("args = " + "$2a$10$zdQKrndBSK1hYfrIdFZn/OchX2/MmV2eTpUwanunkNzui9gisOFZy");
    }
}
