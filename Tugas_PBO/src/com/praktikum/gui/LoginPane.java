package com.praktikum.gui;

import com.praktikum.data.DataStore;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginPane extends VBox {

    private MainApp mainApp;

    public LoginPane(MainApp mainApp) {
        this.mainApp = mainApp;
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPadding(new Insets(40, 40, 40, 40));
        setStyle("-fx-background-color: #f0f0f0;");

        Text sceneTitle = new Text("Login Sistem Lost & Found!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        // Login Form
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label userTypeLabel = new Label("Login sebagai:");
        grid.add(userTypeLabel, 0, 0);

        ToggleGroup userTypeGroup = new ToggleGroup();
        RadioButton adminRadio = new RadioButton("Admin");
        adminRadio.setToggleGroup(userTypeGroup);
        adminRadio.setSelected(true); // Default selection
        RadioButton mahasiswaRadio = new RadioButton("Mahasiswa");
        mahasiswaRadio.setToggleGroup(userTypeGroup);

        VBox radioBox = new VBox(5, adminRadio, mahasiswaRadio);
        grid.add(radioBox, 1, 0);

        Label input1Label = new Label("Username:"); // Default label
        grid.add(input1Label, 0, 1);
        TextField input1Field = new TextField();
        grid.add(input1Field, 1, 1);

        Label input2Label = new Label("Password:"); // Default label
        grid.add(input2Label, 0, 2);
        PasswordField input2Field = new PasswordField(); // Use PasswordField for password
        grid.add(input2Field, 1, 2);

        Button loginButton = new Button("Login");
        VBox hbBtn = new VBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginButton);
        grid.add(hbBtn, 1, 4);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        // --- Event Handlers ---

        // Change labels based on radio button selection
        userTypeGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == adminRadio) {
                input1Label.setText("Username:");
                input2Label.setText("Password:");
                input1Field.setPromptText("Masukkan username admin");
                input2Field.setPromptText("Masukkan password admin");
            } else if (newValue == mahasiswaRadio) {
                input1Label.setText("Nama:");
                input2Label.setText("NIM:");
                input1Field.setPromptText("Masukkan nama mahasiswa");
                input2Field.setPromptText("Masukkan NIM mahasiswa");
            }
            input1Field.clear();
            input2Field.clear();
            actionTarget.setText(""); // Clear message on type change
        });

        // Login button action
        loginButton.setOnAction(e -> {
            actionTarget.setText(""); // Clear previous messages
            String input1 = input1Field.getText();
            String input2 = input2Field.getText();
            User loggedInUser = null;

            if (input1.isEmpty() || input2.isEmpty()) {
                actionTarget.setFill(javafx.scene.paint.Color.FIREBRICK);
                actionTarget.setText("Input tidak boleh kosong!");
                return;
            }

            if (adminRadio.isSelected()) {
                loggedInUser = DataStore.loginAdmin(input1, input2);
                if (loggedInUser != null) {
                    mainApp.showAdminDashboard((Admin) loggedInUser);
                } else {
                    actionTarget.setFill(javafx.scene.paint.Color.FIREBRICK);
                    actionTarget.setText("Login Admin Gagal! Periksa username/password.");
                }
            } else if (mahasiswaRadio.isSelected()) {
                loggedInUser = DataStore.loginMahasiswa(input1, input2);
                if (loggedInUser != null) {
                    mainApp.showMahasiswaDashboard((Mahasiswa) loggedInUser);
                } else {
                    actionTarget.setFill(javafx.scene.paint.Color.FIREBRICK);
                    actionTarget.setText("Login Mahasiswa Gagal! Periksa nama/NIM.");
                }
            }
        });

        getChildren().addAll(sceneTitle, grid, actionTarget);
    }
}

