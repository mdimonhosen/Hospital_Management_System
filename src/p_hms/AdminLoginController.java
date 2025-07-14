/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package p_hms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AdminLoginController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private TextField tfuserid;
    @FXML
    private PasswordField pfpass;
    @FXML
    private Label lblMessage; // Add this to your FXML file

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Nothing needed here now
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
        String userId = tfuserid.getText();
        String password = pfpass.getText();

        if(userId.equals("1234") && password.equals("1234")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminInfo.fxml"));
            Parent root = loader.load();

        // Create a new stage (window) and set the scene
            Stage stage = new Stage();
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root));
            stage.show();

            // Close current window
            Stage currentStage = (Stage) button1.getScene().getWindow();
            currentStage.close();
        } else {
            lblMessage.setText("Invalid User ID or Password");
        }
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }
    
}
