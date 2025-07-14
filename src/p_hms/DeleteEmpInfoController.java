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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class DeleteEmpInfoController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private Button button3;
    @FXML
    private Button button2;

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
    private void clearbutton(ActionEvent event) {
    }

    @FXML
    private void deletebutton(ActionEvent event) {
    }
    
}
