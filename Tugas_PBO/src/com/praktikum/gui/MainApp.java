package com.praktikum.gui;

import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Sistem Laporan Barang Praktikum");

        // Show the login pane initially
        showLoginPane();

        primaryStage.show();
    }

    public void showLoginPane() {
        LoginPane loginPane = new LoginPane(this);
        Scene scene = new Scene(loginPane, 500, 400); // Adjust size as needed
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login - Sistem Laporan Barang");
    }

    public void showAdminDashboard(Admin admin) {
        AdminDashboard adminDashboard = new AdminDashboard(this, admin);
        Scene scene = new Scene(adminDashboard, 800, 600); // Adjust size as needed
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Dashboard - Sistem Laporan Barang");
    }

    public void showMahasiswaDashboard(Mahasiswa mahasiswa) {
        MahasiswaDashboard mahasiswaDashboard = new MahasiswaDashboard(this, mahasiswa);
        Scene scene = new Scene(mahasiswaDashboard, 800, 600); // Adjust size as needed
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mahasiswa Dashboard - Sistem Laporan Barang");
    }

    // Main method is typically in a separate launcher class, but can be here too
    // public static void main(String[] args) {
    //     launch(args);
    // }
}

