/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package p_hms;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AdminLoginController implements Initializable {

    @FXML private TextField tfuserid;
    @FXML private PasswordField pfpass;
    @FXML private Label lblMessage;
    private Button back;
    private Button login;

    private Connection conn;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonlogin;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/p_hms", // Database URL
                "root",                              // MySQL username
                ""                                   // MySQL password (empty in your case)
            );
        } catch (ClassNotFoundException | SQLException e) {
            lblMessage.setText("❌ Database connection failed!");
        }
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        String userId = tfuserid.getText().trim();
        String password = pfpass.getText().trim();

        if (userId.isEmpty() || password.isEmpty()) {
            lblMessage.setText("⚠️ Please enter both User ID and Password.");
            return;
        }

        String query = "SELECT * FROM admin_login WHERE user_id = ? AND user_password = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userId);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Successful login
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminInfo.fxml"));
                Parent root = loader.load();

        // Create a new stage (window) and set the scene
                Stage stage = new Stage();
                stage.setTitle("Admin Dashboard");
                stage.setScene(new Scene(root));
                stage.show();

                // Close current login window
                ((Stage) login.getScene().getWindow()).close();
            } else {
                lblMessage.setText("❌ Invalid User ID or Password.");
            }

        } catch (SQLException e) {
            lblMessage.setText("❌ Login error occurred.");
        }
    }

    @FXML
    private void back(ActionEvent event) {
        ((Stage) back.getScene().getWindow()).close();
    }

}
