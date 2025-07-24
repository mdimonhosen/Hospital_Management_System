package p_hms;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UpdatePatientController implements Initializable {

    @FXML private Button button1;  // Back button

    @FXML private RadioButton rbMale;
    @FXML private RadioButton rbFemale;
    @FXML private RadioButton rbOthers;
    @FXML private ToggleGroup radio;

    @FXML private TextField tfID;
    @FXML private TextField tfFirstName;
    @FXML private TextField tfLastName;
    @FXML private TextField tfPhoneNo1;
    @FXML private TextField tfPhoneNo2;
    @FXML private TextField tfEmail;
    @FXML private ComboBox<String> comboBoxBloodGroup;
    @FXML private TextField tfHouseNo;
    @FXML private TextField tfStreet;
    @FXML private TextField tfCity;
    @FXML private TextField tfState;
    @FXML private Button buttonUpdate;
    @FXML private TextField tfReltiveName;
    @FXML private TextField tfRelativeRelation;
    @FXML private TextField tfRelativePhoneNo;

    @FXML private TextField tfReferredWard;
    @FXML private TextField tfReferredRoom;
    @FXML private TextField tfProblem;
    @FXML private ComboBox<String> comboBoxRoomType;
    @FXML private TextField tfDiseaseDetails;
    @FXML private TextField tfRoomAssigned;
    @FXML private TextField tfDateAdmitted;
    @FXML private TextField tfNurseAssigned;

    @FXML private DatePicker datePicker;

    private final String DB_URL = "jdbc:mysql://localhost:3306/p_hms";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxBloodGroup.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        comboBoxBloodGroup.getSelectionModel().selectFirst();

        comboBoxRoomType.getItems().addAll("General", "Semi-Private", "Private", "ICU", "Other");
        comboBoxRoomType.getSelectionModel().selectFirst();

        rbMale.setSelected(true); // Default gender
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void update(ActionEvent event) {
        if (tfID.getText().trim().isEmpty() ||
            tfFirstName.getText().trim().isEmpty() ||
            tfLastName.getText().trim().isEmpty() ||
            datePicker.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Please fill in ID, First Name, Last Name, and Date of Birth.");
            return;
        }

        String gender = null;
        if (rbMale.isSelected()) gender = "Male";
        else if (rbFemale.isSelected()) gender = "Female";
        else if (rbOthers.isSelected()) gender = "Other";

        String dob = datePicker.getValue().toString(); // LocalDate to String

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            String sql = "UPDATE patients SET "
                       + "first_name=?, last_name=?, dob=?, gender=?, blood_group=?, "
                       + "house_no=?, street=?, city=?, state=?, phone1=?, phone2=?, email=?, "
                       + "referred_ward=?, referred_room=?, problem=?, room_type=?, disease_details=?, "
                       + "room_assigned=?, date_admitted=?, nurse_assigned=?, relative_name=?, "
                       + "relative_relation=?, relative_phone=?, created_at=NOW() "
                       + "WHERE id=?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, tfFirstName.getText().trim());
            stmt.setString(2, tfLastName.getText().trim());
            stmt.setString(3, dob);
            stmt.setString(4, gender);
            stmt.setString(5, comboBoxBloodGroup.getValue());

            stmt.setString(6, tfHouseNo.getText().trim());
            stmt.setString(7, tfStreet.getText().trim());
            stmt.setString(8, tfCity.getText().trim());
            stmt.setString(9, tfState.getText().trim());
            stmt.setString(10, tfPhoneNo1.getText().trim());
            stmt.setString(11, tfPhoneNo2.getText().trim());
            stmt.setString(12, tfEmail.getText().trim());

            stmt.setString(13, getSafe(tfReferredWard));
            stmt.setString(14, getSafe(tfReferredRoom));
            stmt.setString(15, getSafe(tfProblem));
            stmt.setString(16, comboBoxRoomType.getValue());
            stmt.setString(17, getSafe(tfDiseaseDetails));

            stmt.setString(18, getSafe(tfRoomAssigned));
            stmt.setString(19, getSafe(tfDateAdmitted));
            stmt.setString(20, getSafe(tfNurseAssigned));
            stmt.setString(21, tfReltiveName.getText().trim());
            stmt.setString(22, tfRelativeRelation.getText().trim());
            stmt.setString(23, tfRelativePhoneNo.getText().trim());

            stmt.setString(24, tfID.getText().trim());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Patient record updated successfully.");
            } else {
                showAlert(Alert.AlertType.WARNING, "No record found with the given ID.");
            }

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database error: " + e.getMessage());
        }
    }

    private String getSafe(TextField field) {
        return (field != null && field.getText() != null) ? field.getText().trim() : "";
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle("Update Patient");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
