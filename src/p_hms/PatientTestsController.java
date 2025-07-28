package p_hms;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatientTestsController implements Initializable {

    @FXML private Button button1, buttonSave, buttonClear;
    @FXML private TextField tfID;
    @FXML private ComboBox<String> comboBoxSelectTest;
    @FXML private TableView<TestData> tableView;
    @FXML private TableColumn<TestData, Integer> colS_NO;
    @FXML private TableColumn<TestData, String> colTestName;

    private final String DB_URL = "jdbc:mysql://localhost:3306/p_hms";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    private ObservableList<TestData> testList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxSelectTest.getItems().addAll("Blood Test", "X-Ray", "MRI", "ECG", "Urine Test");
        colS_NO.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTestName.setCellValueFactory(new PropertyValueFactory<>("testName"));
    }

    @FXML
    private void back(ActionEvent event) {
        ((Stage) button1.getScene().getWindow()).close();
    }

    @FXML
    private void save(ActionEvent event) {
        String patientId = tfID.getText();
        String selectedTest = comboBoxSelectTest.getValue();

        if (patientId.isEmpty() || selectedTest == null) {
            showAlert(Alert.AlertType.ERROR, "Please enter Patient ID and select a Test.");
            return;
        }

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "INSERT INTO PatientTests (patient_id, test_name) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, Integer.parseInt(patientId));
            stmt.setString(2, selectedTest);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Test assigned successfully.");
                loadTestsForPatient(Integer.parseInt(patientId));
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
        }
    }

    @FXML
    private void clear(ActionEvent event) {
        tfID.clear();
        comboBoxSelectTest.setValue(null);
        tableView.getItems().clear();
    }

    private void loadTestsForPatient(int patientId) {
        testList.clear();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "SELECT id, test_name FROM PatientTests WHERE patient_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String testName = rs.getString("test_name");
                testList.add(new TestData(id, testName));
            }

            tableView.setItems(testList);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error loading tests: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle("Patient Tests");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // inner class to hold table data
    public static class TestData {
        private final Integer id;
        private final String testName;

        public TestData(Integer id, String testName) {
            this.id = id;
            this.testName = testName;
        }

        public Integer getId() { return id; }
        public String getTestName() { return testName; }
    }
}
