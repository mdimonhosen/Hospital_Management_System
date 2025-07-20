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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
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
    private void generatebillbutton(ActionEvent event) {
    }

    @FXML
    private void clearbutton(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
          Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }
    
}
