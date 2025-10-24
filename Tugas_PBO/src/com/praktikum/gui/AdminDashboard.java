package com.praktikum.gui;

import com.praktikum.data.DataStore;
import com.praktikum.data.Item;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.List;
import java.util.Optional;
import javafx.scene.Node;

public class AdminDashboard extends BorderPane {

    private MainApp mainApp;
    private Admin currentAdmin;
    private TableView<Item> itemTable;
    private TableView<Mahasiswa> userTable;
    private ObservableList<Item> itemData;
    private ObservableList<Mahasiswa> userData;

    public AdminDashboard(MainApp mainApp, Admin admin) {
        this.mainApp = mainApp;
        this.currentAdmin = admin;
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #e8f4f8;");

        // --- Top Section: Title and Logout ---
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setSpacing(10);
        topBar.setPadding(new Insets(0, 0, 20, 0));

        Text title = new Text("Admin Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Welcome message
        Label welcomeLabel = new Label("Selamat datang, " + currentAdmin.getNama() + "!");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        // Logout Button
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> mainApp.showLoginPane());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topBar.getChildren().addAll(title, spacer, welcomeLabel, logoutButton);
        setTop(topBar);

        // --- Center Section: Tabbed Pane for Items and Users ---
        TabPane tabPane = new TabPane();

        Tab itemsTab = new Tab("Kelola Laporan Barang");
        itemsTab.setClosable(false);
        itemsTab.setContent(createItemsManagementPane());

        Tab usersTab = new Tab("Kelola Data Mahasiswa");
        usersTab.setClosable(false);
        usersTab.setContent(createUsersManagementPane());

        tabPane.getTabs().addAll(itemsTab, usersTab);
        setCenter(tabPane);
    }

    // --- Items Management Pane ---
    private VBox createItemsManagementPane() {
        VBox itemsPane = new VBox(15);
        itemsPane.setPadding(new Insets(15));
        itemsPane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 1;");

        Text itemsTitle = new Text("Daftar Laporan Barang");
        itemsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        // Table View for Items
        itemTable = new TableView<>();
        itemData = FXCollections.observableArrayList(DataStore.getAllItems());
        itemTable.setItems(itemData);

        TableColumn<Item, String> nameCol = new TableColumn<>("Nama Barang");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        nameCol.setPrefWidth(150);

        TableColumn<Item, String> descCol = new TableColumn<>("Deskripsi");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descCol.setPrefWidth(250);

        TableColumn<Item, String> locCol = new TableColumn<>("Lokasi");
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        locCol.setPrefWidth(150);

        TableColumn<Item, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(100);

        TableColumn<Item, String> nimCol = new TableColumn<>("NIM Pelapor");
        nimCol.setCellValueFactory(new PropertyValueFactory<>("reporterNim"));
        nimCol.setPrefWidth(120);

        itemTable.getColumns().addAll(nameCol, descCol, locCol, statusCol, nimCol);
        itemTable.setPlaceholder(new Label("Tidak ada laporan barang."));

        // Buttons for Item Actions
        Button markClaimedButton = new Button("Tandai Telah Diambil (Claimed)");
        markClaimedButton.setOnAction(e -> markSelectedItemAsClaimed());

        Button refreshItemsButton = new Button("Refresh Daftar Barang");
        refreshItemsButton.setOnAction(e -> refreshItemTable());

        HBox itemButtons = new HBox(10, markClaimedButton, refreshItemsButton);
        itemButtons.setAlignment(Pos.CENTER_LEFT);

        itemsPane.getChildren().addAll(itemsTitle, itemTable, itemButtons);
        return itemsPane;
    }

    // --- Users Management Pane ---
    private VBox createUsersManagementPane() {
        VBox usersPane = new VBox(15);
        usersPane.setPadding(new Insets(15));
        usersPane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 1;");

        Text usersTitle = new Text("Daftar Mahasiswa");
        usersTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        // Table View for Users
        userTable = new TableView<>();
        userData = FXCollections.observableArrayList(DataStore.getAllMahasiswa());
        userTable.setItems(userData);

        TableColumn<Mahasiswa, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(new PropertyValueFactory<>("nama"));
        namaCol.setPrefWidth(250);

        TableColumn<Mahasiswa, String> nimCol = new TableColumn<>("NIM");
        nimCol.setCellValueFactory(new PropertyValueFactory<>("nim"));
        nimCol.setPrefWidth(150);

        userTable.getColumns().addAll(namaCol, nimCol);
        userTable.setPlaceholder(new Label("Tidak ada data mahasiswa."));

        // Buttons for User Actions
        Button addUserButton = new Button("Tambah Mahasiswa");
        addUserButton.setOnAction(e -> showAddUserDialog());

        Button deleteUserButton = new Button("Hapus Mahasiswa");
        deleteUserButton.setOnAction(e -> deleteSelectedUser());

        Button refreshUsersButton = new Button("Refresh Daftar Mahasiswa");
        refreshUsersButton.setOnAction(e -> refreshUserTable());

        HBox userButtons = new HBox(10, addUserButton, deleteUserButton, refreshUsersButton);
        userButtons.setAlignment(Pos.CENTER_LEFT);

        usersPane.getChildren().addAll(usersTitle, userTable, userButtons);
        return usersPane;
    }

    // --- Action Handlers ---

    private void refreshItemTable() {
        itemData.setAll(DataStore.getAllItems());
        itemTable.refresh();
    }

    private void refreshUserTable() {
        userData.setAll(DataStore.getAllMahasiswa());
        userTable.refresh();
    }

    private void markSelectedItemAsClaimed() {
        Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih barang terlebih dahulu.");
            return;
        }

        if (!"Reported".equalsIgnoreCase(selectedItem.getStatus())) {
            showAlert(Alert.AlertType.INFORMATION, "Informasi", "Barang ini sudah berstatus '" + selectedItem.getStatus() + "'.");
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Konfirmasi");
        confirmation.setHeaderText("Tandai Barang Telah Diambil");
        confirmation.setContentText("Apakah Anda yakin ingin menandai barang '" + selectedItem.getItemName() + "' sebagai telah diambil?");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean success = currentAdmin.markItemAsClaimed(selectedItem);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Barang berhasil ditandai sebagai 'Claimed'.");
                refreshItemTable(); // Refresh table to show updated status
            } else {
                // This case should ideally not happen based on the checks above, but good to have
                showAlert(Alert.AlertType.ERROR, "Error", "Gagal menandai barang.");
            }
        }
    }

    private void showAddUserDialog() {
        Dialog<Mahasiswa> dialog = new Dialog<>();
        dialog.setTitle("Tambah Mahasiswa Baru");
        dialog.setHeaderText("Masukkan detail mahasiswa baru:");

        ButtonType addButtonType = new ButtonType("Tambah", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField namaField = new TextField();
        namaField.setPromptText("Nama Lengkap");
        TextField nimField = new TextField();
        nimField.setPromptText("NIM");

        grid.add(new Label("Nama:"), 0, 0);
        grid.add(namaField, 1, 0);
        grid.add(new Label("NIM:"), 0, 1);
        grid.add(nimField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Enable/disable add button depending on whether a name/nim was entered.
        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        // Simple validation
        namaField.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || nimField.getText().trim().isEmpty());
        });
        nimField.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || namaField.getText().trim().isEmpty());
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                // Basic NIM format check (only digits, specific length if needed)
                if (!nimField.getText().matches("\\d+")) { // Example: Check if NIM contains only digits
                    showAlert(Alert.AlertType.ERROR, "Input Tidak Valid", "NIM harus berupa angka.");
                    return null; // Prevent dialog closing
                }
                return new Mahasiswa(namaField.getText(), nimField.getText());
            }
            return null;
        });

        Optional<Mahasiswa> result = dialog.showAndWait();

        result.ifPresent(mahasiswa -> {
            boolean success = currentAdmin.addMahasiswa(mahasiswa.getNama(), mahasiswa.getNim());
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Mahasiswa berhasil ditambahkan.");
                refreshUserTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Gagal menambahkan mahasiswa. NIM mungkin sudah terdaftar.");
            }
        });
    }

    private void deleteSelectedUser() {
        Mahasiswa selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih mahasiswa yang akan dihapus.");
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Konfirmasi Hapus");
        confirmation.setHeaderText("Hapus Mahasiswa");
        confirmation.setContentText("Apakah Anda yakin ingin menghapus mahasiswa dengan NIM " + selectedUser.getNim() + "?");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean success = currentAdmin.deleteMahasiswa(selectedUser.getNim());
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Mahasiswa berhasil dihapus.");
                refreshUserTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Gagal menghapus mahasiswa.");
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

