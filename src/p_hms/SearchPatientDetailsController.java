package p_hms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SearchPatientDetailsController implements Initializable {

    @FXML
    private Button button1;  // Back button

    @FXML
    private RadioButton rb1; // Search by Name

    @FXML
    private ToggleGroup radio;

    @FXML
    private RadioButton rb2; // Search by ID

    @FXML
    private TextField tfNameOrID;

    @FXML
    private Button buttonSearch;

    @FXML
    private Button buttonClear;

    @FXML
    private TableView<Patient> tableview;

    @FXML
    private TableColumn<Patient, String> colPatientID;

    @FXML
    private TableColumn<Patient, String> colPatientName;

    @FXML
    private TableColumn<Patient, String> colGender;

    @FXML
    private TableColumn<Patient, String> colAddress;

    @FXML
    private TableColumn<Patient, String> colDoctorAssigned;

    @FXML
    private TableColumn<Patient, String> colRoomAdmitted;

    @FXML
    private TableColumn<Patient, String> colDoctorAdmitted;

    @FXML
    private TableColumn<Patient, String> colDateDischarged;

    private ObservableList<Patient> allPatients = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize table columns with Patient properties
        colPatientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDoctorAssigned.setCellValueFactory(new PropertyValueFactory<>("doctorAssigned"));
        colRoomAdmitted.setCellValueFactory(new PropertyValueFactory<>("roomAdmitted"));
        colDoctorAdmitted.setCellValueFactory(new PropertyValueFactory<>("doctorAdmitted"));
        colDateDischarged.setCellValueFactory(new PropertyValueFactory<>("dateDischarged"));

        // Add sample patients
        allPatients.addAll(
                new Patient("P001", "Alice", "Female", "123 St", "Dr. Smith", "101", "Dr. Adams", "2025-07-10"),
                new Patient("P002", "Bob", "Male", "456 Ave", "Dr. Jones", "102", "Dr. Baker", "2025-07-15"),
                new Patient("P003", "Charlie", "Male", "789 Blvd", "Dr. Smith", "103", "Dr. Clark", "2025-07-20")
        );

        // Display all patients initially
        tableview.setItems(allPatients);
        rb1.setSelected(true);  // Default select search by Name
    }

    @FXML
    private void search(ActionEvent event) {
        String searchText = tfNameOrID.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            tableview.setItems(allPatients); // Show all if search is empty
            return;
        }
        ObservableList<Patient> filteredList = FXCollections.observableArrayList();

        if (rb1.isSelected()) {
            // Search by Name
            for (Patient p : allPatients) {
                if (p.getPatientName().toLowerCase().contains(searchText)) {
                    filteredList.add(p);
                }
            }
        } else if (rb2.isSelected()) {
            // Search by ID
            for (Patient p : allPatients) {
                if (p.getPatientID().toLowerCase().contains(searchText)) {
                    filteredList.add(p);
                }
            }
        }
        tableview.setItems(filteredList);
    }

    @FXML
    private void clear(ActionEvent event) {
        tfNameOrID.clear();
        tableview.setItems(allPatients);
        rb1.setSelected(true);  // Reset radio button selection to Name
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }

    // Sample Patient model class
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

        public String getPatientID() {
            return patientID;
        }

        public String getPatientName() {
            return patientName;
        }

        public String getGender() {
            return gender;
        }

        public String getAddress() {
            return address;
        }

        public String getDoctorAssigned() {
            return doctorAssigned;
        }

        public String getRoomAdmitted() {
            return roomAdmitted;
        }

        public String getDoctorAdmitted() {
            return doctorAdmitted;
        }

        public String getDateDischarged() {
            return dateDischarged;
        }
    }
}
