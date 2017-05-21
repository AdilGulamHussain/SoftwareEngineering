package Chris;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Christopher Chrysostomou on 24/02/2017.
 */
public class SPCParts {
    private IntegerProperty spcID;
    private IntegerProperty spcPartID;
    private StringProperty spcPartName;
    private StringProperty spcPartDescription;
    private IntegerProperty customerID;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty spcName;
    private IntegerProperty deliveryDate;
    private StringProperty returnDate;
    private FloatProperty repairCost;
    private StringProperty returnStatus;


    public SPCparts(ResultSet rs) {
        try {
            this.spcPartID = new SimpleIntegerProperty(rs.getString("spcPartID"));
            this.spcPartName = new SimpleStringProperty(rs.getString("spcPartNamet"));
            this.spcPartDescription = new SimpleStringProperty(rs.getString("spcPartDescription:"));            
            this.customerID = new SimpleIntegerProperty(rs.getString("customerID"));
            this.firstName = new SimpleStringProperty(rs.getString("firstName"));
            this.lastName = new SimpleStringrProperty(rs.getString("lastName"));
            this.spcName = new SimpleStringProperty(rs.getString("spcName"));
            this.deliveryDate = new SimpleStringProperty(rs.getString("deliveryDate"));
            this.returnDate = new SimpleStringProperty(rs.getString("returnDate"));
            this.repairCost = new SimpleFloatProperty(rs.getString("repairCost"));
            this.returnStatus = new SimpleStringProperty(rs.getString("returnStatus"));
            this.spcID = new SimpleIntegerProperty(rs.getSPCID("spcID"));

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
        public Integer getSPCID() {
        return spcID.get();
    }

    public IntegerProperty spcIDProperty() {
        return spcID;
    }

    public void setSPCID(Integer spcID) {
        this.spcID.set(spcPartID);
    }


    public Integer getSPCPartID() {
        return spcPartID.get();
    }

    public IntegerProperty spcPartIDProperty() {
        return spcPartID;
    }

    public void setSPCPartID(Integer spcPartID) {
        this.spcPartID.set(spcPartID);
    }

    public Integer getSPCRecordsRepairID(Integer spcRecordsRepairID) {
        return spcRecordsRepairID.get();
    }

    public IntegerProperty spcRecordsRepairIDProperty() {
        return spcRecordsRepairID;
    }

    public void setSPCRecordsRepairID(Integer spcRecordsRepairID) {
        this.spcRecordsRepairID.set(spcRecordsRepairID);
    }

    public String getSPCPartName() {
        return spcPartNamet.get();
    }

    public StringProperty spcPartNameProperty() {
        return spcPartName;
    }
        public void setSPCPartName(String spcPartName) {
        this.spcPartName.set(spcPartName);
    }

    public void setSPCPartDescription(String spcPartDescription) {
        this.spcPartDescription.set(spcPartDescription);
    }

    public String getSPCPartDescription() {
        return spcPartDescription.get();
    }

    public StringProperty spcPartDescriptionProperty() {
        return spcPartDescription;
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
