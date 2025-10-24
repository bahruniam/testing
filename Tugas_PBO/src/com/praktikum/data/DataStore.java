package com.praktikum.data;

import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataStore {
    private static final ArrayList<User> userList = new ArrayList<>();
    private static final ArrayList<Item> reportedItems = new ArrayList<>();

    //Data Awal
    static {
        // Default Admin
        userList.add(new Admin("Administrator", "ADMIN123", "admin277", "password277"));

        // Default Mahasiswa
        userList.add(new Mahasiswa("Bahru Niam", "202410370110277"));
        userList.add(new Mahasiswa("Yudi Upil", "202410370110312"));
        userList.add(new Mahasiswa("BoBon Santoso", "202410370110313"));

        // Default Items
        reportedItems.add(new Item("Laptop Asus", "Warna hitam, ada stiker yupi", "GKB 7 lantai 2", "202410370110312"));
        reportedItems.add(new Item("Kunci Motor Honda", "Ada gantungan Pocong", "Kantin", "202410370110313"));
    }

    // User Management Methods
    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static User loginAdmin(String username, String password) {
        for (User user : userList) {
            if (user instanceof Admin) {
                Admin admin = (Admin) user;
                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                    return admin;
                }
            }
        }
        return null;
    }

    public static User loginMahasiswa(String nama, String nim) {
        for (User user : userList) {
            if (user instanceof Mahasiswa) {
                if (((Mahasiswa) user).getNama().equals(nama) && ((Mahasiswa) user).getNim().equals(nim)) {
                    return user;
                }
            }
        }
        return null;
    }

    public static void addMahasiswa(Mahasiswa mahasiswa) {
        if (userList.stream().noneMatch(user -> user.getNim().equals(mahasiswa.getNim()))) {
            userList.add(mahasiswa);
        }
    }

    public static boolean deleteMahasiswa(String nim) {
        return userList.removeIf(user -> user instanceof Mahasiswa && user.getNim().equals(nim));
    }

    public static List<Mahasiswa> getAllMahasiswa() {
        return userList.stream().filter(user -> user instanceof Mahasiswa).map(user -> (Mahasiswa) user).collect(Collectors.toList());
    }

    // Item Management Methods
    public static ArrayList<Item> getReportedItems() {
        return reportedItems;
    }

    public static void addItem(Item item) {
        reportedItems.add(item);
    }

    public static List<Item> getAllItems() {
        return new ArrayList<>(reportedItems); // Return a copy
    }

    public static List<Item> getReportedStatusItems() {
        return reportedItems.stream().filter(item -> "Reported".equalsIgnoreCase(item.getStatus())).collect(Collectors.toList());
    }

    public static Item findItemByIndex(int index) {
        if (index >= 0 && index < reportedItems.size()) {
            return reportedItems.get(index);
        }
        return null;
    }

    public static Item findReportedItemByNameAndNim(String itemName, String reporterNim) {
        for (Item item : reportedItems) {
            if (item.getItemName().equalsIgnoreCase(itemName) && item.getReporterNim().equals(reporterNim) && "Reported".equalsIgnoreCase(item.getStatus())) {
                return item;
            }
        }
        return null;
    }
}

