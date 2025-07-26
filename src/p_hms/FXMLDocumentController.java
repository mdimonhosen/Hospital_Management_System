package p_hms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller class for the main FXML window
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button minimizeButton;
    @FXML
    private Button maximizeButton;
    @FXML
    private Button closeButton;

    // This method is called when the button is clicked
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        // Load the PatientRegForm.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HospitalManage.fxml"));
        Parent root = loader.load();

        // Create a new stage (window) and set the scene
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Any initialization code if needed
    }

    @FXML
    private void mt(ActionEvent event) throws IOException {
        // Load the PatientRegForm.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientTests.fxml"));
        Parent root = loader.load();

        // Create a new stage (window) and set the scene
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void md(ActionEvent event) throws IOException {
        // Load the PatientRegForm.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientsMedicine.fxml"));
        Parent root = loader.load();

        // Create a new stage (window) and set the scene
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void ap(ActionEvent event) throws IOException {
        // Load the PatientRegForm.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminLogin.fxml"));
        Parent root = loader.load();

        // Create a new stage (window) and set the scene
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleMinimize(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void handleMaximize(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }

    @FXML
    private void handleClose(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}