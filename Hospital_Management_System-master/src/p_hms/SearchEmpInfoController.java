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

    @FXML private Button button1, buttonclear, buttonSearch;
    @FXML private ToggleGroup radio;
    @FXML private RadioButton rbName, rbEmployeeID, rbPhone, rbEmail;
    @FXML private TableView<Employee> tableview;
    @FXML private TableColumn<Employee, Integer> colEmpID;
    @FXML private TableColumn<Employee, String> colName, colGender, colAddress, colEmpType, colDeptName, colDateOfJoining, colPhone, colEmail;
    @FXML private TableColumn<Employee, Double> colSalary;
    @FXML private TextField tfNameOrEmployeeID, tfPhoneOrEmail;

    private final String DB_URL = "jdbc:mysql://localhost:3306/p_hms";
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
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadEmployeeData();
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clear(ActionEvent event) {
        tfNameOrEmployeeID.clear();
        tfPhoneOrEmail.clear();
        radio.selectToggle(null);
        tableview.getItems().clear();
        loadEmployeeData();
    }

    @FXML
    private void search(ActionEvent event) {
        String nameOrId = tfNameOrEmployeeID.getText().trim();
        String phoneOrEmail = tfPhoneOrEmail.getText().trim();

        employeeList.clear();
        String sql = "";
        PreparedStatement stmt = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            if (rbPhone.isSelected() && !phoneOrEmail.isEmpty()) {
                sql = "SELECT * FROM p_hms.SearchEmpInfoController WHERE phone LIKE ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, "%" + phoneOrEmail + "%");

            } else if (rbEmail.isSelected() && !phoneOrEmail.isEmpty()) {
                sql = "SELECT * FROM p_hms.SearchEmpInfoController WHERE email LIKE ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, "%" + phoneOrEmail + "%");

            } else if (rbEmployeeID.isSelected() && !nameOrId.isEmpty()) {
                sql = "SELECT * FROM p_hms.SearchEmpInfoController WHERE emp_id = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(nameOrId));

            } else if (rbName.isSelected() && !nameOrId.isEmpty()) {
                sql = "SELECT * FROM p_hms.SearchEmpInfoController WHERE name LIKE ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, "%" + nameOrId + "%");

            } else {
                showAlert(Alert.AlertType.WARNING, "Please fill a field and select the appropriate search type.");
                return;
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("emp_type"),
                        rs.getString("date_of_joining"),
                        rs.getString("dept_name"),
                        rs.getDouble("salary"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
                employeeList.add(emp);
            }

            if (employeeList.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "No employee found.");
            }

            tableview.setItems(employeeList);

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Employee ID format.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "DB Error: " + e.getMessage());
        }
    }

    private void loadEmployeeData() {
        employeeList.clear();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM p_hms.SearchEmpInfoController")) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("emp_type"),
                        rs.getString("date_of_joining"),
                        rs.getString("dept_name"),
                        rs.getDouble("salary"),
                        rs.getString("phone"),
                        rs.getString("email")
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
        private final String name, gender, address, empType, dateOfJoining, deptName, phone, email;
        private final double salary;

        public Employee(int empId, String name, String gender, String address, String empType, String dateOfJoining,
                        String deptName, double salary, String phone, String email) {
            this.empId = empId;
            this.name = name;
            this.gender = gender;
            this.address = address;
            this.empType = empType;
            this.dateOfJoining = dateOfJoining;
            this.deptName = deptName;
            this.salary = salary;
            this.phone = phone;
            this.email = email;
        }

        public int getEmpId() { return empId; }
        public String getName() { return name; }
        public String getGender() { return gender; }
        public String getAddress() { return address; }
        public String getEmpType() { return empType; }
        public String getDateOfJoining() { return dateOfJoining; }
        public String getDeptName() { return deptName; }
        public double getSalary() { return salary; }
        public String getPhone() { return phone; }
        public String getEmail() { return email; }
    }
}
