package p_hms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class UpdateEmpRecordController implements Initializable {

    @FXML
    private Button button1; // Back button
    @FXML
    private RadioButton rb1; // Gender Male
    @FXML
    private RadioButton rb2; // Gender Female
    @FXML
    private RadioButton rb3; // Gender Other
    @FXML
    private ToggleGroup radio;
    @FXML
    private Button buttonUpdate;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfMiddleName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfDateOfBirth;
    @FXML
    private ComboBox<String> comboBoxEmployeeType;
    @FXML
    private ComboBox<String> comboBoxDepartment;
    @FXML
    private TextField tfDateOfJoining;
    @FXML
    private TextField tfHouseNo;
    @FXML
    private TextField tfStreet;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfState;
    @FXML
    private TextField tfPhoneNo1;
    @FXML
    private TextField tfPhoneNo2;
    @FXML
    private TextField tfEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate combo boxes with sample data (replace with your data source)
        comboBoxEmployeeType.getItems().addAll("Full Time", "Part Time", "Contract", "Intern");
        comboBoxDepartment.getItems().addAll("HR", "IT", "Finance", "Operations", "Marketing");

        // Optionally set defaults
        comboBoxEmployeeType.getSelectionModel().selectFirst();
        comboBoxDepartment.getSelectionModel().selectFirst();

        // Set default gender selected
        rb1.setSelected(true);
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void update(ActionEvent event) {
        // Basic validation: check required fields
        if (tfID.getText().trim().isEmpty() ||
            tfFirstName.getText().trim().isEmpty() ||
            tfLastName.getText().trim().isEmpty() ||
            tfDateOfBirth.getText().trim().isEmpty() ||
            tfDateOfJoining.getText().trim().isEmpty()) {

            System.out.println("Error: Please fill in all required fields (ID, First Name, Last Name, DOB, Date of Joining).");
            return;
        }

        // Read gender radio buttons
        String gender = null;
        if (rb1.isSelected()) {
            gender = "Male";
        } else if (rb2.isSelected()) {
            gender = "Female";
        } else if (rb3.isSelected()) {
            gender = "Other";
        }

        // Collect all data
        String id = tfID.getText().trim();
        String firstName = tfFirstName.getText().trim();
        String middleName = tfMiddleName.getText().trim();
        String lastName = tfLastName.getText().trim();
        String dob = tfDateOfBirth.getText().trim();
        String employeeType = comboBoxEmployeeType.getValue();
        String department = comboBoxDepartment.getValue();
        String doj = tfDateOfJoining.getText().trim();
        String address = tfHouseNo.getText().trim() + ", " + tfStreet.getText().trim() + ", " +
                         tfCity.getText().trim() + ", " + tfState.getText().trim();
        String phone1 = tfPhoneNo1.getText().trim();
        String phone2 = tfPhoneNo2.getText().trim();
        String email = tfEmail.getText().trim();

        // TODO: Add your database update logic here, for now printing to console
        System.out.println("Updating Employee Record:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + firstName + " " + middleName + " " + lastName);
        System.out.println("Gender: " + gender);
        System.out.println("DOB: " + dob);
        System.out.println("Employee Type: " + employeeType);
        System.out.println("Department: " + department);
        System.out.println("Date of Joining: " + doj);
        System.out.println("Address: " + address);
        System.out.println("Phone 1: " + phone1);
        System.out.println("Phone 2: " + phone2);
        System.out.println("Email: " + email);

        System.out.println("Update successful!");
        // Optionally clear fields or close window after update
    }
}
