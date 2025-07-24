package p_hms;

import javafx.beans.property.*;

public class class1 {

    public static class Medicine {
        private final SimpleIntegerProperty sNo;
        private final SimpleStringProperty medicineName;
        private final SimpleIntegerProperty quantity;
        private final SimpleDoubleProperty cost;
        private final SimpleDoubleProperty totalCost;

        public Medicine(int sNo, String medicineName, int quantity, double cost, double totalCost) {
            this.sNo = new SimpleIntegerProperty(sNo);
            this.medicineName = new SimpleStringProperty(medicineName);
            this.quantity = new SimpleIntegerProperty(quantity);
            this.cost = new SimpleDoubleProperty(cost);
            this.totalCost = new SimpleDoubleProperty(totalCost);
        }

        public int getSNo() { return sNo.get(); }
        public SimpleIntegerProperty sNoProperty() { return sNo; }

        public String getMedicineName() { return medicineName.get(); }
        public SimpleStringProperty medicineNameProperty() { return medicineName; }

        public int getQuantity() { return quantity.get(); }
        public SimpleIntegerProperty quantityProperty() { return quantity; }

        public double getCost() { return cost.get(); }
        public SimpleDoubleProperty costProperty() { return cost; }

        public double getTotalCost() { return totalCost.get(); }
        public SimpleDoubleProperty totalCostProperty() { return totalCost; }
    }

    public static class Test {
        private final SimpleStringProperty testName;

        public Test(String testName) {
            this.testName = new SimpleStringProperty(testName);
        }

        public String getTestName() { return testName.get(); }
        public SimpleStringProperty testNameProperty() { return testName; }
    }
}
