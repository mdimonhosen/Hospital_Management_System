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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class PatientTestsController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonClear;
    @FXML
    private TextField tfID;
    @FXML
    private ComboBox<?> comboBoxSelectTest;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> colS_NO;
    @FXML
    private TableColumn<?, ?> colTestName;

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
    private void save(ActionEvent event) {
    }

    @FXML
    private void clear(ActionEvent event) {
    }
    
}
