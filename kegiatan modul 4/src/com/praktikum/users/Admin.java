package com.praktikum.users;

import com.praktikum.actions.AdminAction;

public class Admin extends User implements AdminAction { //class turuan user,smua method admin action

    public Admin(String username, String password) {
        super(username, password);
    } //constructor

    @Override
    public void login() {
        System.out.println("Login Admin berhasil.\n");
    } //metod login

    @Override
    public void displayAppMenu() { //metod nampilkan menu utk admin
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int choice;
        do {
            System.out.println("\nMenu Admin:");
            System.out.println("1. Kelola Laporan Barang");
            System.out.println("2. Kelola Data Mahasiswa");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> manageItems();
                case 2 -> manageUsers();
                case 0 -> System.out.println("Logout...");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 0);
    }

    @Override
    public void manageItems() {
        System.out.println(">> Fitur Kelola Barang Belum Tersedia <<");
    }

    @Override
    public void manageUsers() {
        System.out.println(">> Fitur Kelola Mahasiswa Belum Tersedia <<");
    }
}
