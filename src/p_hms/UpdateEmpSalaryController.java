package p_hms;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateEmpSalaryController implements Initializable {

    @FXML
    private Button button1;  // Back button
    @FXML
    private ComboBox<String> comboBoxSelectEmployeeType;
    @FXML
    private TextField tfOldSalary;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonClear;
    @FXML
    private TextField tfPercentChange;
    @FXML
    private TextField tfNewSalary;

    // Dummy salary data by employee type for demo
    private Map<String, Double> salaryMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate combo box with example employee types
        comboBoxSelectEmployeeType.getItems().addAll("Full Time", "Part Time", "Contract", "Intern");

        // Example old salaries by type
        salaryMap.put("Full Time", 50000.0);
        salaryMap.put("Part Time", 30000.0);
        salaryMap.put("Contract", 40000.0);
        salaryMap.put("Intern", 20000.0);

        // Load old salary when employee type is selected
        comboBoxSelectEmployeeType.setOnAction(e -> loadOldSalary());

        // Optionally select the first item by default
        comboBoxSelectEmployeeType.getSelectionModel().selectFirst();
        loadOldSalary();
    }

    private void loadOldSalary() {
        String type = comboBoxSelectEmployeeType.getValue();
        if (type != null && salaryMap.containsKey(type)) {
            tfOldSalary.setText(String.format("%.2f", salaryMap.get(type)));
            tfNewSalary.clear();
            tfPercentChange.clear();
        }
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save(ActionEvent event) {
        String type = comboBoxSelectEmployeeType.getValue();
        String oldSalaryStr = tfOldSalary.getText().trim();
        String percentStr = tfPercentChange.getText().trim();

        if (type == null || oldSalaryStr.isEmpty() || percentStr.isEmpty()) {
            System.out.println("Please select employee type and enter percentage change.");
            return;
        }

        try {
            double oldSalary = Double.parseDouble(oldSalaryStr);
            double percentChange = Double.parseDouble(percentStr);

            // Calculate new salary
            double newSalary = oldSalary + (oldSalary * percentChange / 100);
            tfNewSalary.setText(String.format("%.2f", newSalary));

            // TODO: Add DB update or persistence logic here
            System.out.println("Salary updated for " + type + ":");
            System.out.println("Old Salary: " + oldSalary);
            System.out.println("Percentage Change: " + percentChange + "%");
            System.out.println("New Salary: " + newSalary);
            System.out.println("Save successful!");

        } catch (NumberFormatException ex) {
            System.out.println("Invalid number format. Please enter valid numbers.");
        }
    }

    @FXML
    private void clear(ActionEvent event) {
        comboBoxSelectEmployeeType.getSelectionModel().clearSelection();
        tfOldSalary.clear();
        tfPercentChange.clear();
        tfNewSalary.clear();
    }
}
