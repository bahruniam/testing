package com.praktikum.gui;

import com.praktikum.data.DataStore;
import com.praktikum.data.Item;
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
import java.util.Optional;

public class MahasiswaDashboard extends BorderPane {

    private MainApp mainApp;
    private Mahasiswa currentMahasiswa;
    private TableView<Item> reportedItemsTable;
    private ObservableList<Item> reportedItemsData;

    public MahasiswaDashboard(MainApp mainApp, Mahasiswa mahasiswa) {
        this.mainApp = mainApp;
        this.currentMahasiswa = mahasiswa;
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #f8f8e8;"); // Slightly different background for distinction

        // --- Top Section: Title and Logout ---
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setSpacing(10);
        topBar.setPadding(new Insets(0, 0, 20, 0));

        Text title = new Text("Mahasiswa Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Welcome message
        Label welcomeLabel = new Label("Selamat datang, " + currentMahasiswa.getNama() + " (NIM: " + currentMahasiswa.getNim() + ")!");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        // Logout Button
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> mainApp.showLoginPane());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topBar.getChildren().addAll(title, spacer, welcomeLabel, logoutButton);
        setTop(topBar);

        // --- Center Section: Tabbed Pane ---
        TabPane tabPane = new TabPane();

        Tab reportTab = new Tab("Laporkan Barang Temuan/Hilang");
        reportTab.setClosable(false);
        reportTab.setContent(createReportItemPane());

        Tab viewTab = new Tab("Lihat Daftar Laporan (Status Reported)");
        viewTab.setClosable(false);
        viewTab.setContent(createViewReportedItemsPane());

        tabPane.getTabs().addAll(reportTab, viewTab);
        setCenter(tabPane);
    }

    // --- Report Item Pane ---
    private VBox createReportItemPane() {
        VBox reportPane = new VBox(15);
        reportPane.setPadding(new Insets(20));
        reportPane.setAlignment(Pos.CENTER_LEFT);
        reportPane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 1;");

        Text reportTitle = new Text("Form Laporan Barang");
        reportTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField itemNameField = new TextField();
        itemNameField.setPromptText("Contoh: Dompet Coklat");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Contoh: Berisi KTP a/n Budi, SIM, sedikit uang tunai");
        descriptionArea.setWrapText(true);
        descriptionArea.setPrefRowCount(3);
        TextField locationField = new TextField();
        locationField.setPromptText("Contoh: Perpustakaan Lantai 3");

        grid.add(new Label("Nama Barang:"), 0, 0);
        grid.add(itemNameField, 1, 0);
        grid.add(new Label("Deskripsi:"), 0, 1);
        grid.add(descriptionArea, 1, 1);
        grid.add(new Label("Lokasi Terakhir/Ditemukan:"), 0, 2);
        grid.add(locationField, 1, 2);

        Button submitButton = new Button("Kirim Laporan");
        submitButton.setOnAction(e -> {
            String itemName = itemNameField.getText();
            String description = descriptionArea.getText();
            String location = locationField.getText();

            if (itemName.trim().isEmpty() || description.trim().isEmpty() || location.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Input Tidak Lengkap", "Harap isi semua field laporan.");
                return;
            }

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Konfirmasi Laporan");
            confirmation.setHeaderText("Konfirmasi Detail Laporan");
            confirmation.setContentText("Nama Barang: " + itemName + "\nDeskripsi: " + description + "\nLokasi: " + location + "\n\nLaporkan barang ini?");

            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean success = currentMahasiswa.submitReport(itemName, description, location);
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Sukses", "Laporan berhasil dikirim.");
                    // Clear fields after successful submission
                    itemNameField.clear();
                    descriptionArea.clear();
                    locationField.clear();
                    // Optionally refresh the view list if needed immediately
                    refreshReportedItemsTable();
                } else {
                    // This might happen if validation fails in submitReport, though current validation is basic
                    showAlert(Alert.AlertType.ERROR, "Error", "Gagal mengirim laporan.");
                }
            }
        });

        HBox buttonBox = new HBox(submitButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        grid.add(buttonBox, 1, 3);

        reportPane.getChildren().addAll(reportTitle, grid);
        return reportPane;
    }

    // --- View Reported Items Pane ---
    private VBox createViewReportedItemsPane() {
        VBox viewPane = new VBox(15);
        viewPane.setPadding(new Insets(15));
        viewPane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 1;");

        Text viewTitle = new Text("Daftar Barang Dilaporkan (Status: Reported)");
        viewTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        // Table View for Reported Items
        reportedItemsTable = new TableView<>();
        reportedItemsData = FXCollections.observableArrayList(currentMahasiswa.getReportedStatusItemsList());
        reportedItemsTable.setItems(reportedItemsData);

        TableColumn<Item, String> nameCol = new TableColumn<>("Nama Barang");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        nameCol.setPrefWidth(200);

        TableColumn<Item, String> descCol = new TableColumn<>("Deskripsi");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descCol.setPrefWidth(300);

        TableColumn<Item, String> locCol = new TableColumn<>("Lokasi");
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        locCol.setPrefWidth(200);

        // NIM Pelapor might not be necessary for Mahasiswa view, but can be included
        // TableColumn<Item, String> nimCol = new TableColumn<>("NIM Pelapor");
        // nimCol.setCellValueFactory(new PropertyValueFactory<>("reporterNim"));
        // nimCol.setPrefWidth(120);

        reportedItemsTable.getColumns().addAll(nameCol, descCol, locCol);
        reportedItemsTable.setPlaceholder(new Label("Tidak ada barang yang dilaporkan saat ini."));

        Button refreshButton = new Button("Refresh Daftar");
        refreshButton.setOnAction(e -> refreshReportedItemsTable());

        HBox buttonBox = new HBox(refreshButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        viewPane.getChildren().addAll(viewTitle, reportedItemsTable, buttonBox);
        return viewPane;
    }

    // --- Helper Methods ---

    private void refreshReportedItemsTable() {
        reportedItemsData.setAll(currentMahasiswa.getReportedStatusItemsList());
        reportedItemsTable.refresh();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

