package com.praktikum.main;

import com.praktikum.users.User;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.data.Item;

import java.util.Scanner;
import java.util.ArrayList;

public class LoginSystem {
    private static ArrayList<User> userList = new ArrayList<>();
    private static ArrayList<Item> reportedItems = new ArrayList<>();

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static ArrayList<Item> getReportedItems() {
        return reportedItems;
    }

    public static void setReportedItems(ArrayList<Item> reportedItems) {
        LoginSystem.reportedItems = reportedItems;
    }

    public static void main(String[] args) {
        // Dummy data user
        userList.add(new Admin("Admin", "admin277"));
        userList.add(new Mahasiswa("Bahru Niam", "277"));

        Scanner input = new Scanner(System.in);
        int pilihan = 0;

        while (pilihan != 3) {
            System.out.println("\nPilih Login:");
            System.out.println("1. Login Admin");
            System.out.println("2. Login Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan: ");

            try {
                pilihan = input.nextInt();
                input.nextLine(); // konsumsi newline

                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan Username: ");
                        String username = input.nextLine();
                        System.out.print("Masukkan Password: ");
                        String password = input.nextLine();

                        boolean adminFound = false;
                        for (User user : userList) {
                            if (user instanceof Admin) {
                                Admin admin = (Admin) user;
                                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                                    System.out.println("Login Admin berhasil!\n");
                                    admin.diplayMenu();
                                    adminFound = true;
                                    break;
                                }
                            }
                        }

                        if (!adminFound) {
                            System.out.println("Login Admin gagal. Username atau password salah.");
                        }
                        break;

                    case 2:
                        System.out.print("Masukkan Nama: ");
                        String nama = input.nextLine();
                        System.out.print("Masukkan NIM: ");
                        String nim = input.nextLine();

                        boolean mhsFound = false;
                        for (User user : userList) {
                            if (user instanceof Mahasiswa) {
                                Mahasiswa mhs = (Mahasiswa) user;
                                if (mhs.getNama().equalsIgnoreCase(nama) && mhs.getNim().equals(nim)) {
                                    System.out.println("Login Mahasiswa berhasil!\n");
                                    mhs.diplayMenu();
                                    mhsFound = true;
                                    break;
                                }
                            }
                        }

                        if (!mhsFound) {
                            System.out.println("Login Mahasiswa gagal. Nama atau NIM salah.");
                        }
                        break;

                    case 3:
                        System.out.println("Program berakhir.");
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid. Harap masukkan angka.");
                input.nextLine(); // bersihkan buffer
            }
        }

        input.close();
    }
}