package com.praktikum.main;

import com.praktikum.users.*;

import java.util.Scanner;

public class LoginSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login System");
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();
        System.out.print("Login sebagai (1. Admin, 2. Mahasiswa): ");
        int role = scanner.nextInt();

        User user;
        if (role == 1) {
            user = new Admin(username, password);
        } else {
            user = new Mahasiswa(username, password);
        }

        user.login();
        user.displayAppMenu();
    }
}