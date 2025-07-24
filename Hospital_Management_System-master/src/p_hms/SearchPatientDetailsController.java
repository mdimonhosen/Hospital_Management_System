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

public class SearchPatientDetailsController implements Initializable {

    @FXML private Button button1, buttonSearch, buttonClear;
    @FXML private RadioButton rb1, rb2; // rb1: Name, rb2: ID
    @FXML private ToggleGroup radio;
    @FXML private TextField tfNameOrID;
    @FXML private TableView<Patient> tableview;
    @FXML private TableColumn<Patient, String> colPatientID, colPatientName, colGender, colAddress;
    @FXML private TableColumn<Patient, String> colDoctorAssigned, colRoomAdmitted, colDoctorAdmitted, colDateDischarged;

    private final String DB_URL = "jdbc:mysql://localhost:3306/p_hms";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    private ObservableList<Patient> allPatients = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colPatientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDoctorAssigned.setCellValueFactory(new PropertyValueFactory<>("doctorAssigned"));
        colRoomAdmitted.setCellValueFactory(new PropertyValueFactory<>("roomAdmitted"));
        colDoctorAdmitted.setCellValueFactory(new PropertyValueFactory<>("doctorAdmitted"));
        colDateDischarged.setCellValueFactory(new PropertyValueFactory<>("dateDischarged"));

        rb1.setSelected(true);
        loadAllPatientsFromDB();
    }

    private void loadAllPatientsFromDB() {
        allPatients.clear();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM SearchPatientDetails")) {

            while (rs.next()) {
                Patient p = new Patient(
                        rs.getString("patient_id"),
                        rs.getString("patient_name"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("doctor_assigned"),
                        rs.getString("room_admitted"),
                        rs.getString("doctor_admitted"),
                        rs.getString("date_discharged")
                );
                allPatients.add(p);
            }
            tableview.setItems(allPatients);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Load Error: " + e.getMessage());
        }
    }

    @FXML
    private void search(ActionEvent event) {
        String searchText = tfNameOrID.getText().trim();

        if (searchText.isEmpty()) {
            loadAllPatientsFromDB();
            return;
        }

        ObservableList<Patient> filteredList = FXCollections.observableArrayList();
        String sql;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            PreparedStatement stmt;

            if (rb1.isSelected()) {
                sql = "SELECT * FROM SearchPatientDetails WHERE patient_name LIKE ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, "%" + searchText + "%");

            } else if (rb2.isSelected()) {
                sql = "SELECT * FROM SearchPatientDetails WHERE patient_id LIKE ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, "%" + searchText + "%");

            } else {
                showAlert(Alert.AlertType.WARNING, "Select Name or ID search option.");
                return;
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Patient p = new Patient(
                        rs.getString("patient_id"),
                        rs.getString("patient_name"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("doctor_assigned"),
                        rs.getString("room_admitted"),
                        rs.getString("doctor_admitted"),
                        rs.getString("date_discharged")
                );
                filteredList.add(p);
            }

            if (filteredList.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "No matching patient found.");
            }

            tableview.setItems(filteredList);

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Search Error: " + e.getMessage());
        }
    }

    @FXML
    private void clear(ActionEvent event) {
        tfNameOrID.clear();
        rb1.setSelected(true);
        loadAllPatientsFromDB();
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Search Patient Details");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class Patient {
        private final String patientID;
        private final String patientName;
        private final String gender;
        private final String address;
        private final String doctorAssigned;
        private final String roomAdmitted;
        private final String doctorAdmitted;
        private final String dateDischarged;

        public Patient(String patientID, String patientName, String gender, String address,
                       String doctorAssigned, String roomAdmitted, String doctorAdmitted, String dateDischarged) {
            this.patientID = patientID;
            this.patientName = patientName;
            this.gender = gender;
            this.address = address;
            this.doctorAssigned = doctorAssigned;
            this.roomAdmitted = roomAdmitted;
            this.doctorAdmitted = doctorAdmitted;
            this.dateDischarged = dateDischarged;
        }

        public String getPatientID() { return patientID; }
        public String getPatientName() { return patientName; }
        public String getGender() { return gender; }
        public String getAddress() { return address; }
        public String getDoctorAssigned() { return doctorAssigned; }
        public String getRoomAdmitted() { return roomAdmitted; }
        public String getDoctorAdmitted() { return doctorAdmitted; }
        public String getDateDischarged() { return dateDischarged; }
    }
}
