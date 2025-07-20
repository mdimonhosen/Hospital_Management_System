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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class PatientRegFormController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private Button button4;
    @FXML
    private RadioButton rb1;
    @FXML
    private ToggleGroup radio;
    @FXML
    private RadioButton rb3;
    @FXML
    private RadioButton rb2;
    @FXML
    private TextField tfID;
    @FXML
    private Button buttonSavePD;
    @FXML
    private Button buttonClearPD;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfDateOfBirthday;
    @FXML
    private TextField tfBloodGroup;
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
    @FXML
    private TextField tfReferredWaed;
    @FXML
    private TextField tfReferredRoom;
    @FXML
    private ComboBox<?> comboBoxProblem;
    @FXML
    private ComboBox<?> comboBoxRoomType;
    @FXML
    private Button buttonSaveIPI;
    @FXML
    private Button buttonClearIPI;
    @FXML
    private TextArea textAreaDiseaseAetails;
    @FXML
    private TextField tfRoomAssigned;
    @FXML
    private TextField tfDateAdmitted;
    @FXML
    private TextField tfNurseAssigned;
    @FXML
    private TextField tfRelativeName;
    @FXML
    private TextField tfRelativeRelation;
    @FXML
    private TextField tfRelativePhoneNo;

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
    private void back2(ActionEvent event) {
        Stage stage;
        stage = (Stage) button4.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save(ActionEvent event) {
    }

    @FXML
    private void clear(ActionEvent event) {
    }
    
}
