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
public class SearchEmpInfoController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private RadioButton rb1;
    @FXML
    private ToggleGroup radio;
    @FXML
    private RadioButton rb2;
    @FXML
    private TableView<?> tableview;
    @FXML
    private TableColumn<?, ?> colEmpID;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colGender;
    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colEmpType;
    @FXML
    private TableColumn<?, ?> colDateOfJoining;
    @FXML
    private TableColumn<?, ?> colDeptName;
    @FXML
    private TableColumn<?, ?> colSalary;
    @FXML
    private TextField tfNameOrEmployeeID;
    @FXML
    private TextField tfPnoneNumber;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button buttonsave;
    @FXML
    private Button buttonclear;

    /**
     * Initializes the controller class.
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
    private void save(ActionEvent event) {
    }

    @FXML
    private void clear(ActionEvent event) {
    }
    
}
