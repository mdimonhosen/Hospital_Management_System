package p_hms;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import p_hms.class1.Medicine;
import p_hms.class1.Test;

public class BillGenerationController implements Initializable {

    @FXML private Button button1, buttonGenerateBill, buttonClearFields;
    @FXML private TextField tfID, tfNAME, tfGENDER, tfADDRESS, tfDISEASECTREATED;
    @FXML private TableView<Medicine> tableview2;
    @FXML private TableColumn<Medicine, Integer> colS_NO;
    @FXML private TableColumn<Medicine, String> colMEDICINENAME;
    @FXML private TableColumn<Medicine, Integer> colQUANTITY;
    @FXML private TableColumn<Medicine, Double> colCOST;
    @FXML private TableColumn<Medicine, Double> colTOTALCOST;

    @FXML private TextField tfTOTALMEDICINESCOST;
    @FXML private TableView<Test> tableview1;
    @FXML private TableColumn<Test, String> colTESTNAME;
    @FXML private TextField tfTOTALTESTSCOST, tfROOMBILL, tfOTHERCHARGES, tfTOTALPAYABLEAMMOUNT;

    private final String DB_URL = "jdbc:mysql://localhost:3306/p_hms";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colS_NO.setCellValueFactory(cellData -> cellData.getValue().sNoProperty().asObject());
        colMEDICINENAME.setCellValueFactory(cellData -> {
            cellData.getValue().medicineNameProperty();
            return null;
        });
        colQUANTITY.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        colCOST.setCellValueFactory(cellData -> cellData.getValue().costProperty().asObject());
        colTOTALCOST.setCellValueFactory(cellData -> cellData.getValue().totalCostProperty().asObject());

        colTESTNAME.setCellValueFactory(cellData -> {
            cellData.getValue().testNameProperty();
            return null;
        });
    }

    @FXML
    private void generatebillbutton(ActionEvent event) {
        try {
            double medicineCost = Double.parseDouble(tfTOTALMEDICINESCOST.getText());
            double testCost = Double.parseDouble(tfTOTALTESTSCOST.getText());
            double roomBill = Double.parseDouble(tfROOMBILL.getText());
            double otherCharges = Double.parseDouble(tfOTHERCHARGES.getText());

            double total = medicineCost + testCost + roomBill + otherCharges;
            tfTOTALPAYABLEAMMOUNT.setText(String.format("%.2f", total));

            saveBillToDatabase();

        } catch (NumberFormatException e) {
            tfTOTALPAYABLEAMMOUNT.setText("Error: Invalid input");
        }
    }

    private void saveBillToDatabase() {
        String id = tfID.getText();
        String name = tfNAME.getText();
        String gender = tfGENDER.getText();
        String address = tfADDRESS.getText();
        String disease = tfDISEASECTREATED.getText();

        double medicineCost = Double.parseDouble(tfTOTALMEDICINESCOST.getText());
        double testCost = Double.parseDouble(tfTOTALTESTSCOST.getText());
        double roomBill = Double.parseDouble(tfROOMBILL.getText());
        double otherCharges = Double.parseDouble(tfOTHERCHARGES.getText());
        double totalAmount = Double.parseDouble(tfTOTALPAYABLEAMMOUNT.getText());

        ObservableList<Medicine> medicines = tableview2.getItems();
        ObservableList<Test> tests = tableview1.getItems();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO Bill_Generation (id, name, gender, address, disease, test_name, medicine_name, quantity, cost, total_cost, total_medicine_cost, total_test_cost, room_bill, other_charges, total_amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (Test test : tests) {
                for (Medicine med : medicines) {
                    stmt.setString(1, id);
                    stmt.setString(2, name);
                    stmt.setString(3, gender);
                    stmt.setString(4, address);
                    stmt.setString(5, disease);
                    stmt.setString(6, test.getTestName());
                    stmt.setString(7, med.getMedicineName());
                    stmt.setInt(8, med.getQuantity());
                    stmt.setDouble(9, med.getCost());
                    stmt.setDouble(10, med.getTotalCost());
                    stmt.setDouble(11, medicineCost);
                    stmt.setDouble(12, testCost);
                    stmt.setDouble(13, roomBill);
                    stmt.setDouble(14, otherCharges);
                    stmt.setDouble(15, totalAmount);

                    stmt.addBatch();
                }
            }

            stmt.executeBatch();
            showAlert(Alert.AlertType.INFORMATION, "Bill saved successfully!");

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error saving bill: " + e.getMessage());
        }
    }

    @FXML
    private void clearbutton(ActionEvent event) {
        tfID.clear(); tfNAME.clear(); tfGENDER.clear(); tfADDRESS.clear();
        tfDISEASECTREATED.clear(); tfTOTALMEDICINESCOST.clear();
        tfTOTALTESTSCOST.clear(); tfROOMBILL.clear(); tfOTHERCHARGES.clear();
        tfTOTALPAYABLEAMMOUNT.clear();
        tableview1.getItems().clear();
        tableview2.getItems().clear();
    }

    @FXML
    private void back(ActionEvent event) {
        ((Stage) button1.getScene().getWindow()).close();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Bill Generation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
