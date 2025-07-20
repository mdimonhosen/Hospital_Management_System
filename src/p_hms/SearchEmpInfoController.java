package p_hms;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SearchEmpInfoController implements Initializable {

    @FXML private Button button1, buttonsave, buttonclear;
    @FXML private RadioButton rb1, rb2;
    @FXML private ToggleGroup radio;
    @FXML private TableView<Employee> tableview;
    @FXML private TableColumn<Employee, Integer> colEmpID;
    @FXML private TableColumn<Employee, String> colName, colGender, colAddress, colEmpType, colDeptName, colDateOfJoining;
    @FXML private TableColumn<Employee, Double> colSalary;
    @FXML private TextField tfNameOrEmployeeID, tfPnoneNumber, tfEmail;

    private final String DB_URL = "jdbc:mysql://localhost:3306/hospital_db";
    private final String DB_USER = "root";
    private final String DB_PASS = "";

    ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmpType.setCellValueFactory(new PropertyValueFactory<>("empType"));
        colDateOfJoining.setCellValueFactory(new PropertyValueFactory<>("dateOfJoining"));
        colDeptName.setCellValueFactory(new PropertyValueFactory<>("deptName"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        loadEmployeeData();
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save(ActionEvent event) {
        String input = tfNameOrEmployeeID.getText();
        String phone = tfPnoneNumber.getText();
        String email = tfEmail.getText();

        if (input.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please fill all fields.");
            return;
        }

        String gender = rb1.isSelected() ? "Male" : rb2.isSelected() ? "Female" : "";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "UPDATE Employees SET phone=?, email=?, gender=? WHERE emp_id=? OR name=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, phone);
            stmt.setString(2, email);
            stmt.setString(3, gender);
            stmt.setString(4, input); // assuming input might be ID
            stmt.setString(5, input); // or name

            int updated = stmt.executeUpdate();
            if (updated > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Employee info updated.");
                loadEmployeeData();
            } else {
                showAlert(Alert.AlertType.WARNING, "No match found to update.");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "DB Error: " + e.getMessage());
        }
    }

    @FXML
    private void clear(ActionEvent event) {
        tfNameOrEmployeeID.clear();
        tfPnoneNumber.clear();
        tfEmail.clear();
        radio.selectToggle(null);
        tableview.getItems().clear();
    }

    private void loadEmployeeData() {
        employeeList.clear();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Employees")) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("emp_type"),
                        rs.getString("date_of_joining"),
                        rs.getString("dept_name"),
                        rs.getDouble("salary")
                );
                employeeList.add(emp);
            }

            tableview.setItems(employeeList);

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error loading employees: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle("Employee Info");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // Model Class
    public static class Employee {
        private final int empId;
        private final String name, gender, address, empType, dateOfJoining, deptName;
        private final double salary;

        public Employee(int empId, String name, String gender, String address, String empType, String dateOfJoining, String deptName, double salary) {
            this.empId = empId;
            this.name = name;
            this.gender = gender;
            this.address = address;
            this.empType = empType;
            this.dateOfJoining = dateOfJoining;
            this.deptName = deptName;
            this.salary = salary;
        }

        public int getEmpId() { return empId; }
        public String getName() { return name; }
        public String getGender() { return gender; }
        public String getAddress() { return address; }
        public String getEmpType() { return empType; }
        public String getDateOfJoining() { return dateOfJoining; }
        public String getDeptName() { return deptName; }
        public double getSalary() { return salary; }
    }
}
