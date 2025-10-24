package com.praktikum.users;

import com.praktikum.actions.AdminActions;
import com.praktikum.main.LoginSystem;
import com.praktikum.data.Item;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.InputMismatchException;

public class Admin extends User implements AdminActions {
    private String username;
    private String password;
    Scanner scanner = new Scanner(System.in);

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean login(String username, String password) {
        for (User user : LoginSystem.getUserList()) {
            if (user instanceof Admin admin) {
                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
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
            System.out.println("\n===== MENU ADMIN =====");
            System.out.println("1. Kelola Barang Temuan");
            System.out.println("2. Kelola Mahasiswa Laporan");
            System.out.println("3. Logout");
            System.out.print("Pilih menu: ");
            try {
                pilih = scanner.nextInt();
                scanner.nextLine(); // bersihkan newline
                switch (pilih) {
                    case 1:
                        manageItems();
                        break;
                    case 2:
                        manageUsers();
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
    public void manageItems() {
        ArrayList<Item> reportedItems = LoginSystem.getReportedItems();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== MENU KELOLA BARANG =====");
            System.out.println("1. Lihat Semua Laporan");
            System.out.println("2. Tandai Barang Telah Diambil (Claimed)");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Bersihkan buffer

                switch (choice) {
                    case 0: {
                        exit = true;
                        break;
                    }

                    case 1:
                        System.out.println("\n===== DAFTAR SEMUA LAPORAN =====");
                        if (reportedItems.isEmpty()) {
                            System.out.println("Tidak ada barang yang dilaporkan.");
                        } else {
                            Iterator<Item> iterator = reportedItems.iterator();
                            int index = 0;
                            while (iterator.hasNext()) {
                                Item item = iterator.next();
                                System.out.printf("%d. %s - Status: %s\n", index++, item.getItemName(), item.getStatus());
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\n===== TANDAI BARANG TELAH DIAMBIL =====");
                        boolean hasReported = false;
                        ArrayList<Integer> validIndices = new ArrayList<>();

                        for (int i = 0; i < reportedItems.size(); i++) {
                            Item item = reportedItems.get(i);
                            if ("Reported".equals(item.getStatus())) {
                                System.out.printf("%d. %s\n", i, item.getItemName());
                                validIndices.add(i);
                                hasReported = true;
                            }
                        }

                        if (!hasReported) {
                            System.out.println("Tidak ada barang dengan status \"Reported\".");
                            break;
                        }

                        System.out.print("Masukkan nomor indeks barang yang ingin ditandai: ");
                        try {
                            int selectedIndex = scanner.nextInt();
                            scanner.nextLine();

                            if (validIndices.contains(selectedIndex)) {
                                try {
                                    Item selectedItem = reportedItems.get(selectedIndex);
                                    selectedItem.setStatus("Claimed");
                                    reportedItems.remove(selectedIndex);
                                    System.out.println("Status item berhasil diubah menjadi \"Claimed\".");
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("Indeks tidak valid.");
                                }
                            } else {
                                System.out.println("Indeks tidak sesuai daftar barang \"Reported\".");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Input harus berupa angka!");
                            scanner.nextLine();
                        }
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine(); // Bersihkan buffer
            }
        }
    }

    @Override
    public void manageUsers() {
        ArrayList<User> userList = LoginSystem.getUserList();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== MENU KELOLA MAHASISWA =====");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("3. Lihat Daftar Mahasiswa");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 0:
                        exit = true;
                        break;

                    case 1:
                        System.out.println("\n===== TAMBAH MAHASISWA =====");
                        System.out.print("Masukkan Nama Mahasiswa: ");
                        String nama = scanner.nextLine();

                        System.out.print("Masukkan NIM Mahasiswa: ");
                        String nim = scanner.nextLine();

                        Mahasiswa mahasiswaBaru = new Mahasiswa(nama, nim);
                        userList.add(mahasiswaBaru);
                        System.out.println("Mahasiswa berhasil ditambahkan!");
                        break;

                    case 2:
                        System.out.println("\n===== HAPUS MAHASISWA =====");
                        System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
                        String nimHapus = scanner.nextLine();

                        boolean found = false;
                        Iterator<User> iterator = userList.iterator();
                        while (iterator.hasNext()) {
                            User user = iterator.next();
                            if (user instanceof Mahasiswa mhs && mhs.getNim().equals(nimHapus)) {
                                iterator.remove();
                                found = true;
                                break;
                            }
                        }

                        if (found) {
                            System.out.println("Mahasiswa dengan NIM " + nimHapus + " berhasil dihapus.");
                        } else {
                            System.out.println("Mahasiswa dengan NIM " + nimHapus + " tidak ditemukan.");
                        }
                        break;

                    case 3:
                        System.out.println("\n===== DAFTAR MAHASISWA =====");
                        int index = 1;
                        boolean adaMahasiswa = false;
                        for (User user : userList) {
                            if (user instanceof Mahasiswa mhs) {
                                System.out.println(index++ + ". Nama: " + mhs.getNama() + " | NIM: " + mhs.getNim());
                                adaMahasiswa = true;
                            }
                        }
                        if (!adaMahasiswa) {
                            System.out.println("Belum ada mahasiswa yang terdaftar.");
                        }
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
            }
        }
    }
}