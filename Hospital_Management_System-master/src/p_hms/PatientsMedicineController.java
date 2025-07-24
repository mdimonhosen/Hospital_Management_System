package p_hms;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatientsMedicineController implements Initializable {

    @FXML private Button button1, buttonSave, buttonClear;
    @FXML private TableView<MedicineData> tableView;
    @FXML private TableColumn<MedicineData, Integer> colS_No;
    @FXML private TableColumn<MedicineData, String> colMedicineName;
    @FXML private TableColumn<MedicineData, Integer> colQuantity;
    @FXML private TextField tfID, tfDate, tfQuantity;
    @FXML private ComboBox<String> comboBoxSelectMedicine;

    private final String DB_URL = "jdbc:mysql://localhost:3306/hospital_db";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    private ObservableList<MedicineData> medicineList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxSelectMedicine.getItems().addAll("Paracetamol", "Antibiotic", "Insulin", "Cough Syrup");

        colS_No.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMedicineName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @FXML
    private void back(ActionEvent event) {
        ((Stage) button1.getScene().getWindow()).close();
    }

    @FXML
    private void save(ActionEvent event) {
        String patientId = tfID.getText().trim();
        String medicine = comboBoxSelectMedicine.getValue();
        String quantityStr = tfQuantity.getText().trim();
        String dateStr = tfDate.getText().trim(); // Format: yyyy-mm-dd expected

        if (patientId.isEmpty() || medicine == null || quantityStr.isEmpty() || dateStr.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Please fill in all fields.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO PatientMedicines (patient_id, medicine_name, quantity, date) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(patientId));
            stmt.setString(2, medicine);
            stmt.setInt(3, Integer.parseInt(quantityStr));
            stmt.setDate(4, Date.valueOf(dateStr));  // yyyy-MM-dd format

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Medicine assigned successfully.");
                loadMedicinesForPatient(Integer.parseInt(patientId));
            }

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage());
        }
    }

    @FXML
    private void clear(ActionEvent event) {
        tfID.clear();
        tfQuantity.clear();
        tfDate.clear();
        comboBoxSelectMedicine.setValue(null);
        tableView.getItems().clear();
    }

    private void loadMedicinesForPatient(int patientId) {
        medicineList.clear();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT id, medicine_name, quantity FROM PatientMedicines WHERE patient_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("medicine_name");
                int qty = rs.getInt("quantity");
                medicineList.add(new MedicineData(id, name, qty));
            }

            tableView.setItems(medicineList);

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error loading medicines: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle("Patient Medicines");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // Model class for TableView rows
    public static class MedicineData {
        private final Integer id;
        private final String medicineName;
        private final Integer quantity;

        public MedicineData(Integer id, String medicineName, Integer quantity) {
            this.id = id;
            this.medicineName = medicineName;
            this.quantity = quantity;
        }

        public Integer getId() { return id; }
        public String getMedicineName() { return medicineName; }
        public Integer getQuantity() { return quantity; }
    }
}
