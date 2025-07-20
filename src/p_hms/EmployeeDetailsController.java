/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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

/**
 * FXML Controller class
 *
 * @author Admin
 */
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
    private ComboBox<?> comboBoxEmployeeType;
    @FXML
    private ComboBox<?> comboBoxDepartment;
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

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) {
          Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void savebutton(ActionEvent event) {
    }

    @FXML
    private void clearbutton(ActionEvent event) {
    }
    
}
