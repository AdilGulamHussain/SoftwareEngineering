package common.logic;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Christopher Chrysostomou on 24/02/2017. add return status to edit vehicle fxml
 */
public class SPCVehicle {
    private StringProperty model;
    private StringProperty make;
    private StringProperty vehicleRegistrationNumber;
    private IntegerProperty customerID;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty spcName;
    private IntegerProperty deliveryDate;
    private StringProperty returnDate;
    private FloatProperty repairCost;
    private StringProperty returnStatus;


    public SPCVehicle(ResultSet rs) {
        try {
            this.vehicleRegistrationNumber = new SimpleStringProperty(rs.getString("vehicleRegistrationNumber"));
            this.model = new SimpleStringProperty(rs.getString("Model"));
            this.make = new SimpleIntegerProperty(rs.getString("make"));
            this.customerID = new SimpleIntegerProperty(rs.getString("customerID"));
            this.firstName = new SimpleStringProperty(rs.getString("firstName"));
            this.lastName = new SimpleStringrProperty(rs.getString("lastName"));
            this.spcName = new SimpleStringProperty(rs.getString("spcName"));
            this.deliveryDate = new SimpleStringProperty(rs.getString("deliveryDate"));
            this.returnDate = new SimpleStringProperty(rs.getString("returnDate"));
            this.repairCost = new SimpleFloatProperty(rs.getString("repairCost"));
            this.returnStatus = new SimpleStringProperty(rs.getString("returnStatus"));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public String getMake() {
        return make.get();
    }

    public StringProperty spcMakeProperty() {
        return make;
    }

    public void setMake(String make) {
        this.make.set(make);
    }

    public String getModel(String model) {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getvehicleRegistrationNumber() {
        return vehicleRegistrationNumber.get();
    }

    public StringProperty vehicleRegistrationNumberProperty() {
        return vehicleRegistrationNumber;
    }
        public void setvehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber.set(vehicleRegistrationNumber);
    }


        public Integer getCustomerID() {
        return customerID.get();
    }

    public IntegerProperty customerIDProperty() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID.set(customerID);
    }

    public String getFirstName(String firstName) {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }
        public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

        public String getSPCName(String firstName) {
        return firstName.get();
    }

    public StringProperty spcNameProperty() {
        return spcName;
    }

    public void setSPCName(String spcName) {
        this.spcName.set(spcName);
    }

    public String getDeliveryDate() {
        return deliveryDate.get();
    }

    public StringProperty deliveryDateProperty() {
        return deliveryDate;
    }
        public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate.set(deliveryDate);
    }

        public String getReturnDate(String returnDate) {
        return returnDate.get();
    }

    public StringProperty returnDateProperty() {
        return returnDate;
    }

    public void setReturndate(String returnDate) {
        this.returnDate.set(returnDate);
    }

    public Float getRepairCost() {
        return repairCost.get();
    }

    public FloatProperty repairCostProperty() {
        return repairCost;
    }
        public void setRepaircost(Float repairCost) {
        this.repairCost.set(repairCost);
    }
        public String getReturnStatus() {
        return returnStatus.get();
    }

    public StringProperty returnStatusProperty() {
        return returnStatus;
    }
        public void setReturnStatus(String returnStatus) {
        this.returnStatus.set(returnStatus);
    }

     
}
