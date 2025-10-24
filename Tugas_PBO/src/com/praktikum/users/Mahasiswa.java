package com.praktikum.users;

import com.praktikum.main.LoginSystem;
import com.praktikum.actions.MahasiswaActions;
import com.praktikum.data.Item;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;

public class Mahasiswa extends User implements MahasiswaActions {
    private String nama;
    private String nim;
    Scanner scanner = new Scanner(System.in);

    public Mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    @Override
    public boolean login(String nama, String nim) {
        for (User user : LoginSystem.getUserList()) {
            if (user instanceof Mahasiswa mhs) {
                if (mhs.getNama().equals(nama) && mhs.getNim().equals(nim)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void diplayMenu() {
        int pilih = 0;
        do {
            System.out.println("\n===== MENU MAHASISWA =====");
            System.out.println("1. Laporan Barang Temuan");
            System.out.println("2. Lihat Daftar Laporan");
            System.out.println("3. Logout");

            System.out.print("Pilih menu: ");
            try {
                pilih = scanner.nextInt();
                scanner.nextLine(); // bersihkan buffer newline

                switch (pilih) {
                    case 1:
                        reportItem();
                        break;
                    case 2:
                        viewReportedItems();
                        break;
                    case 3:
                        System.out.println("Logout berhasil.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine(); // bersihkan buffer
            }
        } while (pilih != 3);
    }

    @Override
    public void reportItem() {
        System.out.println("\n===== LAPORAN BARANG =====");

        System.out.print("Nama barang: ");
        String namaBarang = scanner.nextLine();

        System.out.print("Deskripsi barang: ");
        String deskripsiBarang = scanner.nextLine();

        System.out.print("Lokasi terakhir: ");
        String lokasiTerakhir = scanner.nextLine();

        Item newItem = new Item(namaBarang, deskripsiBarang, lokasiTerakhir, "Reported");

        LoginSystem.getReportedItems().add(newItem);

        System.out.println("Barang sudah dikonfirmasi dan dilaporkan sebagai hilang.");
    }

    @Override
    public void viewReportedItems() {
        System.out.println("\n===== DAFTAR BARANG HILANG =====");

        if (LoginSystem.getReportedItems().isEmpty()) {
            System.out.println("Belum ada laporan barang.");
            return;
        }

        Iterator<Item> iterator = LoginSystem.getReportedItems().iterator();
        boolean foundReportedItems = false;

        while (iterator.hasNext()) {
            Item item = iterator.next();
            if ("Reported".equals(item.getStatus())) {
                System.out.println("Nama barang     : " + item.getItemName());
                System.out.println("Deskripsi        : " + item.getDescription());
                System.out.println("Lokasi terakhir  : " + item.getLocation());
                System.out.println("Status           : " + item.getStatus());
                System.out.println("--------------------------");
                foundReportedItems = true;
            }
        }

        if (!foundReportedItems) {
            System.out.println("Tidak ada barang dengan status \"Reported\".");
        }
    }

}