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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SearchPatientDetailsController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private RadioButton rb1;
    @FXML
    private ToggleGroup radio;
    @FXML
    private RadioButton rb2;
    @FXML
    private TextField tfNameOrID;
    @FXML
    private Button buttonSearch;
    @FXML
    private Button buttonClear;
    @FXML
    private TableView<?> tableview;
    @FXML
    private TableColumn<?, ?> colPatientID;
    @FXML
    private TableColumn<?, ?> colPatientName;
    @FXML
    private TableColumn<?, ?> colGender;
    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colDoctorAssigned;
    @FXML
    private TableColumn<?, ?> colRoomAdmitted;
    @FXML
    private TableColumn<?, ?> colDoctorAdmitted;
    @FXML
    private TableColumn<?, ?> colDateDischarged;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
          Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clear(ActionEvent event) {
    }
    
}
