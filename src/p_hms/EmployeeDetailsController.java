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

public class EmployeeDetailsController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private RadioButton rb1;
    @FXML
    private ToggleGroup radio;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfDateOfBirthday;
    @FXML
    private ComboBox<String> comboBoxEmployeeType;
    @FXML
    private ComboBox<String> comboBoxDepartment;
    @FXML
    private TextField tfDateOfJoining;
    @FXML
    private TextField tfMiddleName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfHouseNo;
    @FXML
    private TextField tfStreet;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfState;
    @FXML
    private TextField tfMobileNo1;
    @FXML
    private TextField tfMobileNo2;
    @FXML
    private TextField tfEmailAddress;
    @FXML
    private Button buttonSAVE;
    @FXML
    private Button buttonClear;

    private final String DB_URL = "jdbc:mysql://localhost:3306/hospital_db";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate Employee Type
        comboBoxEmployeeType.getItems().addAll("Doctor", "Nurse", "Staff");

        // Populate Departments - replace or add your real departments
        comboBoxDepartment.getItems().addAll("Cardiology", "Neurology", "Pediatrics", "Radiology", "Emergency");

        // Optional: select first items by default
        comboBoxEmployeeType.getSelectionModel().selectFirst();
        comboBoxDepartment.getSelectionModel().selectFirst();
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void savebutton(ActionEvent event) {
        try {
            // Basic validation example
            if (tfID.getText().trim().isEmpty() ||
                tfFirstName.getText().trim().isEmpty() ||
                tfLastName.getText().trim().isEmpty() ||
                tfDateOfBirthday.getText().trim().isEmpty() ||
                comboBoxEmployeeType.getValue() == null ||
                comboBoxDepartment.getValue() == null ||
                radio.getSelectedToggle() == null) {

                showAlert(Alert.AlertType.ERROR, "Please fill all required fields and select gender.");
                return;
            }

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "INSERT INTO Employees (emp_id, first_name, middle_name, last_name, dob, gender, employee_type, department, doj, house_no, street, city, state, mobile1, mobile2, email) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            String gender = rb1.isSelected() ? "Male" : rb2.isSelected() ? "Female" : "Other";

            stmt.setInt(1, Integer.parseInt(tfID.getText().trim()));
            stmt.setString(2, tfFirstName.getText().trim());
            stmt.setString(3, tfMiddleName.getText().trim());
            stmt.setString(4, tfLastName.getText().trim());
            stmt.setString(5, tfDateOfBirthday.getText().trim());
            stmt.setString(6, gender);
            stmt.setString(7, comboBoxEmployeeType.getValue());
            stmt.setString(8, comboBoxDepartment.getValue());
            stmt.setString(9, tfDateOfJoining.getText().trim());
            stmt.setString(10, tfHouseNo.getText().trim());
            stmt.setString(11, tfStreet.getText().trim());
            stmt.setString(12, tfCity.getText().trim());
            stmt.setString(13, tfState.getText().trim());
            stmt.setString(14, tfMobileNo1.getText().trim());
            stmt.setString(15, tfMobileNo2.getText().trim());
            stmt.setString(16, tfEmailAddress.getText().trim());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Employee saved successfully.");
                clearbutton(event);
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
        }
    }

    @FXML
    private void clearbutton(ActionEvent event) {
        tfID.clear();
        tfFirstName.clear();
        tfMiddleName.clear();
        tfLastName.clear();
        tfDateOfBirthday.clear();
        tfDateOfJoining.clear();
        tfHouseNo.clear();
        tfStreet.clear();
        tfCity.clear();
        tfState.clear();
        tfMobileNo1.clear();
        tfMobileNo2.clear();
        tfEmailAddress.clear();
        comboBoxDepartment.getSelectionModel().clearSelection();
        comboBoxEmployeeType.getSelectionModel().clearSelection();
        radio.selectToggle(null);
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Employee Details");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
