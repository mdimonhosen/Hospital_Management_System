package p_hms;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteEmpInfoController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private Button buttonClearFields;
    @FXML
    private Button buttonDeleteRecord;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfNAME;

    private final String DB_URL = "jdbc:mysql://localhost:3306/hospital_db";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization if needed
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clearbutton(ActionEvent event) {
        tfID.clear();
        tfNAME.clear();
    }

    @FXML
    private void deletebutton(ActionEvent event) {
        String id = tfID.getText();

        if (id.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please enter the Employee ID.");
            return;
        }

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "DELETE FROM Employees WHERE emp_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Employee deleted successfully.");
                clearbutton(event);
            } else {
                showAlert(Alert.AlertType.WARNING, "No employee found with the given ID.");
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Delete Employee");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
