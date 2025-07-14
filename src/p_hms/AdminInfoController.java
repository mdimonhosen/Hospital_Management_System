/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package p_hms;

import java.awt.event.MouseEvent;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AdminInfoController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ner(ActionEvent event) throws IOException {
         // Load the PatientRegForm.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Employee Details.fxml"));
        Parent root = loader.load();

        // Create a new stage (window) and set the scene
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void sed(ActionEvent event) throws IOException {
         // Load the PatientRegForm.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchEmpInfo.fxml"));
        Parent root = loader.load();

        // Create a new stage (window) and set the scene
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void der(ActionEvent event) throws IOException {
         // Load the PatientRegForm.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteEmpInfo.fxml"));
        Parent root = loader.load();

        // Create a new stage (window) and set the scene
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void uer(ActionEvent event) throws IOException {
         // Load the PatientRegForm.fxml file
        var loader = new FXMLLoader(getClass().getResource("UpdateEmpRecord.fxml"));
        Parent root = loader.load();

        // Create a new stage (window) and set the scene
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void ues(ActionEvent event) throws IOException {
         // Load the PatientRegForm.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateEmpSalary.fxml"));
        Parent root = loader.load();

        // Create a new stage (window) and set the scene
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }
    
}
