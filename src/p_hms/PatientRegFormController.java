package p_hms;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PatientRegFormController implements Initializable {

    @FXML private Button button1, button4, buttonSavePD, buttonClearPD, buttonSaveIPI, buttonClearIPI;
    @FXML private RadioButton rb1, rb2, rb3;
    @FXML private ToggleGroup radio;
    @FXML private TextField tfID, tfFirstName, tfLastName, tfDateOfBirthday, tfBloodGroup,
            tfHouseNo, tfStreet, tfCity, tfState, tfPhoneNo1, tfPhoneNo2, tfEmail,
            tfReferredWaed, tfReferredRoom, tfRoomAssigned, tfDateAdmitted,
            tfNurseAssigned, tfRelativeName, tfRelativeRelation, tfRelativePhoneNo;
    @FXML private ComboBox<String> comboBoxProblem, comboBoxRoomType;
    @FXML private TextArea textAreaDiseaseAetails;

    private final String DB_URL = "jdbc:mysql://localhost:3306/hospital_db";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate Problems - adjust these according to your hospital's common problems
        comboBoxProblem.getItems().addAll(
            "Cardiac Arrest", "Diabetes", "Hypertension", "Fracture", "Infection", "Respiratory Issues"
        );

        // Populate Room Types - adjust as needed
        comboBoxRoomType.getItems().addAll(
            "General Ward", "Private Room", "Semi-Private Room", "ICU", "Operation Theater"
        );

        // Optionally select default items
        comboBoxProblem.getSelectionModel().selectFirst();
        comboBoxRoomType.getSelectionModel().selectFirst();

        // Set default gender selection
        rb1.setSelected(true);
    }

    @FXML
    private void back(ActionEvent event) {
        ((Stage) button1.getScene().getWindow()).close();
    }

    @FXML
    private void back2(ActionEvent event) {
        ((Stage) button4.getScene().getWindow()).close();
    }

    @FXML
    private void save(ActionEvent event) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = """
                INSERT INTO Patients (
                    patient_id, first_name, last_name, dob, gender, blood_group,
                    house_no, street, city, state, phone1, phone2, email,
                    referred_ward, referred_room, problem, room_type,
                    disease_details, room_assigned, date_admitted,
                    nurse_assigned, relative_name, relative_relation, relative_phone
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

            PreparedStatement stmt = conn.prepareStatement(sql);

            String gender = rb1.isSelected() ? "Male" : rb2.isSelected() ? "Female" : "Other";

            stmt.setInt(1, Integer.parseInt(tfID.getText().trim()));
            stmt.setString(2, tfFirstName.getText().trim());
            stmt.setString(3, tfLastName.getText().trim());
            stmt.setString(4, tfDateOfBirthday.getText().trim());
            stmt.setString(5, gender);
            stmt.setString(6, tfBloodGroup.getText().trim());
            stmt.setString(7, tfHouseNo.getText().trim());
            stmt.setString(8, tfStreet.getText().trim());
            stmt.setString(9, tfCity.getText().trim());
            stmt.setString(10, tfState.getText().trim());
            stmt.setString(11, tfPhoneNo1.getText().trim());
            stmt.setString(12, tfPhoneNo2.getText().trim());
            stmt.setString(13, tfEmail.getText().trim());
            stmt.setString(14, tfReferredWaed.getText().trim());
            stmt.setString(15, tfReferredRoom.getText().trim());
            stmt.setString(16, comboBoxProblem.getValue());
            stmt.setString(17, comboBoxRoomType.getValue());
            stmt.setString(18, textAreaDiseaseAetails.getText().trim());
            stmt.setString(19, tfRoomAssigned.getText().trim());
            stmt.setString(20, tfDateAdmitted.getText().trim());
            stmt.setString(21, tfNurseAssigned.getText().trim());
            stmt.setString(22, tfRelativeName.getText().trim());
            stmt.setString(23, tfRelativeRelation.getText().trim());
            stmt.setString(24, tfRelativePhoneNo.getText().trim());

            int inserted = stmt.executeUpdate();
            if (inserted > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Patient registered successfully.");
                clear(event);
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
        }
    }

    @FXML
    private void clear(ActionEvent event) {
        tfID.clear(); tfFirstName.clear(); tfLastName.clear(); tfDateOfBirthday.clear(); tfBloodGroup.clear();
        tfHouseNo.clear(); tfStreet.clear(); tfCity.clear(); tfState.clear(); tfPhoneNo1.clear(); tfPhoneNo2.clear();
        tfEmail.clear(); tfReferredWaed.clear(); tfReferredRoom.clear(); tfRoomAssigned.clear();
        tfDateAdmitted.clear(); tfNurseAssigned.clear(); tfRelativeName.clear(); tfRelativeRelation.clear();
        tfRelativePhoneNo.clear(); textAreaDiseaseAetails.clear();
        comboBoxProblem.getSelectionModel().clearSelection();
        comboBoxRoomType.getSelectionModel().clearSelection();
        radio.selectToggle(null);
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle("Patient Registration");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
