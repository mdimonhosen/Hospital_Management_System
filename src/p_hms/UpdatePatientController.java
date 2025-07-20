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

public class UpdatePatientController implements Initializable {

    @FXML
    private Button button1;  // Back button
    @FXML
    private RadioButton rb1; // Male
    @FXML
    private RadioButton rb2; // Female
    @FXML
    private RadioButton rb3; // Other
    @FXML
    private ToggleGroup radio;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfPhoneNo1;
    @FXML
    private TextField tfPhoneNo2;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfDateOfBirth;
    @FXML
    private ComboBox<String> comboBoxBloodGroup;
    @FXML
    private TextField tfHouseNo;
    @FXML
    private TextField tfStreet;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfState;
    @FXML
    private Button buttonUpdate;
    @FXML
    private TextField tfReltiveName;
    @FXML
    private TextField tfRelativeRelation;
    @FXML
    private TextField tfRelativePhoneNo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxBloodGroup.getItems().addAll(
                "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        );
        comboBoxBloodGroup.getSelectionModel().selectFirst();

        rb1.setSelected(true); // Default gender Male
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void update(ActionEvent event) {
        // Basic validation
        if (tfID.getText().trim().isEmpty() ||
            tfFirstName.getText().trim().isEmpty() ||
            tfLastName.getText().trim().isEmpty() ||
            tfDateOfBirth.getText().trim().isEmpty()) {
            System.out.println("Error: Please fill in ID, First Name, Last Name and Date of Birth.");
            return;
        }

        String gender = null;
        if (rb1.isSelected()) gender = "Male";
        else if (rb2.isSelected()) gender = "Female";
        else if (rb3.isSelected()) gender = "Other";

        String id = tfID.getText().trim();
        String firstName = tfFirstName.getText().trim();
        String lastName = tfLastName.getText().trim();
        String phone1 = tfPhoneNo1.getText().trim();
        String phone2 = tfPhoneNo2.getText().trim();
        String email = tfEmail.getText().trim();
        String dob = tfDateOfBirth.getText().trim();
        String bloodGroup = comboBoxBloodGroup.getValue();
        String address = tfHouseNo.getText().trim() + ", " + tfStreet.getText().trim() + ", " +
                         tfCity.getText().trim() + ", " + tfState.getText().trim();
        String relativeName = tfReltiveName.getText().trim();
        String relativeRelation = tfRelativeRelation.getText().trim();
        String relativePhone = tfRelativePhoneNo.getText().trim();

        // TODO: Replace this with your DB update logic
        System.out.println("Updating Patient Record:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Gender: " + gender);
        System.out.println("DOB: " + dob);
        System.out.println("Blood Group: " + bloodGroup);
        System.out.println("Address: " + address);
        System.out.println("Phone 1: " + phone1);
        System.out.println("Phone 2: " + phone2);
        System.out.println("Email: " + email);
        System.out.println("Relative: " + relativeName + " (" + relativeRelation + "), Phone: " + relativePhone);

        System.out.println("Update successful!");
    }
}
