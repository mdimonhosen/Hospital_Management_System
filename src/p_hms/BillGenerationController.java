package p_hms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BillGenerationController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private Button buttonGenerateBill;
    @FXML
    private Button buttonClearFields;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfNAME;
    @FXML
    private TextField tfGENDER;
    @FXML
    private TextField tfADDRESS;
    @FXML
    private TextField tfDISEASECTREATED;
    @FXML
    private TableView<?> tableview2;
    @FXML
    private TableColumn<?, ?> colS_NO;
    @FXML
    private TableColumn<?, ?> colMEDICINENAME;
    @FXML
    private TableColumn<?, ?> colQUANTITY;
    @FXML
    private TableColumn<?, ?> colCOST;
    @FXML
    private TableColumn<?, ?> colTOTALCOST;
    @FXML
    private TextField tfTOTALMEDICINESCOST;
    @FXML
    private TableView<?> tableview1;
    @FXML
    private TableColumn<?, ?> colTESTNAME;
    @FXML
    private TextField tfTOTALTESTSCOST;
    @FXML
    private TextField tfROOMBILL;
    @FXML
    private TextField tfOTHERCHARGES;
    @FXML
    private TextField tfTOTALPAYABLEAMMOUNT;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization if needed
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
        } catch (NumberFormatException e) {
            tfTOTALPAYABLEAMMOUNT.setText("Error: Invalid input");
        }
    }

    @FXML
    private void clearbutton(ActionEvent event) {
        tfID.clear();
        tfNAME.clear();
        tfGENDER.clear();
        tfADDRESS.clear();
        tfDISEASECTREATED.clear();
        tfTOTALMEDICINESCOST.clear();
        tfTOTALTESTSCOST.clear();
        tfROOMBILL.clear();
        tfOTHERCHARGES.clear();
        tfTOTALPAYABLEAMMOUNT.clear();

        tableview1.getItems().clear();
        tableview2.getItems().clear();
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }
}
