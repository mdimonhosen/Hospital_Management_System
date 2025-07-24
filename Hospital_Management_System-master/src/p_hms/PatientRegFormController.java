package p_hms;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PatientRegFormController implements Initializable {

    @FXML private Button button1, button4, buttonSavePD, buttonClearPD, buttonSaveIPI, buttonClearIPI;
    @FXML private RadioButton rbMale, rbFemale, rbOthers;
    @FXML private ToggleGroup radio;
    @FXML private TextField tfFirstName, tfLastName, tfBloodGroup;
    @FXML private TextField tfHouseNo, tfStreet, tfCity, tfState;
    @FXML private TextField tfPhoneNo1, tfPhoneNo2, tfEmail;
    @FXML private TextField tfReferredWaed, tfReferredRoom, tfRoomAssigned;
    @FXML private TextField tfNurseAssigned, tfRelativeName, tfRelativeRelation, tfRelativePhoneNo;
    @FXML private ComboBox<String> comboBoxProblem, comboBoxRoomType;
    @FXML private TextArea textAreaDiseaseAetails;
    @FXML private DatePicker datePickerDateofBirth, datePickerDateAdmitted;

    // ✅ Updated DB name
    private final String DB_URL = "jdbc:mysql://localhost:3306/p_hms";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxProblem.getItems().addAll(
            "Cardiac Arrest", "Diabetes", "Hypertension", "Fracture", "Infection", "Respiratory Issues"
        );

        comboBoxRoomType.getItems().addAll(
            "General Ward", "Private Room", "Semi-Private Room", "ICU", "Operation Theater"
        );

        comboBoxProblem.getSelectionModel().selectFirst();
        comboBoxRoomType.getSelectionModel().selectFirst();
        radio.selectToggle(rbMale);  // Default gender: Male
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
     
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
        String sql = """
            INSERT INTO Patients (
                first_name, last_name, dob, gender, blood_group,
                house_no, street, city, state, phone1, phone2, email,
                referred_ward, referred_room, problem, room_type,
                disease_details, room_assigned, date_admitted,
                nurse_assigned, relative_name, relative_relation, relative_phone
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        PreparedStatement stmt = conn.prepareStatement(sql);

        String gender = rbMale.isSelected() ? "Male"
                        : rbFemale.isSelected() ? "Female"
                        : "Other";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // ✅ Null-safe date formatting
        String dob = (datePickerDateofBirth.getValue() != null)
                ? datePickerDateofBirth.getValue().format(formatter)
                : null;

        String dateAdmitted = (datePickerDateAdmitted.getValue() != null)
                ? datePickerDateAdmitted.getValue().format(formatter)
                : null;

        stmt.setString(1, tfFirstName.getText().trim());
        stmt.setString(2, tfLastName.getText().trim());
        stmt.setString(3, dob);
        stmt.setString(4, gender);
        stmt.setString(5, tfBloodGroup.getText().trim());
        stmt.setString(6, tfHouseNo.getText().trim());
        stmt.setString(7, tfStreet.getText().trim());
        stmt.setString(8, tfCity.getText().trim());
        stmt.setString(9, tfState.getText().trim());
        stmt.setString(10, tfPhoneNo1.getText().trim());
        stmt.setString(11, tfPhoneNo2.getText().trim());
        stmt.setString(12, tfEmail.getText().trim());
        stmt.setString(13, tfReferredWaed.getText().trim());
        stmt.setString(14, tfReferredRoom.getText().trim());
        stmt.setString(15, comboBoxProblem.getValue());
        stmt.setString(16, comboBoxRoomType.getValue());
        stmt.setString(17, textAreaDiseaseAetails.getText().trim());
        stmt.setString(18, tfRoomAssigned.getText().trim());
        stmt.setString(19, dateAdmitted);
        stmt.setString(20, tfNurseAssigned.getText().trim());
        stmt.setString(21, tfRelativeName.getText().trim());
        stmt.setString(22, tfRelativeRelation.getText().trim());
        stmt.setString(23, tfRelativePhoneNo.getText().trim());

        int inserted = stmt.executeUpdate();
        if (inserted > 0) {
            showAlert(Alert.AlertType.INFORMATION, "✅ Patient registered successfully.");
            clear(event);
        }

    } catch (Exception e) {
        showAlert(Alert.AlertType.ERROR, "❌ Error: " + e.getMessage());
    }


    }

    @FXML
    private void clear(ActionEvent event) {
        tfFirstName.clear(); tfLastName.clear(); tfBloodGroup.clear();
        tfHouseNo.clear(); tfStreet.clear(); tfCity.clear(); tfState.clear();
        tfPhoneNo1.clear(); tfPhoneNo2.clear(); tfEmail.clear();
        tfReferredWaed.clear(); tfReferredRoom.clear(); tfRoomAssigned.clear();
        tfNurseAssigned.clear(); tfRelativeName.clear(); tfRelativeRelation.clear(); tfRelativePhoneNo.clear();
        textAreaDiseaseAetails.clear();
        comboBoxProblem.getSelectionModel().selectFirst();
        comboBoxRoomType.getSelectionModel().selectFirst();
        datePickerDateofBirth.setValue(null);
        datePickerDateAdmitted.setValue(null);
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
