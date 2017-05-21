package Chris;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Christopher Chrysostomou on 24/02/2017. add return status to edit bvehicle fxml
 */
public class SPCVehicle {
    private StringProperty bmodel;
    private StringProperty bmake;
    private StringProperty bvehicleRegistrationNumber;
    private IntegerProperty bcustomerID;
    private StringProperty bfirstName;
    private StringProperty blastName;
    private StringProperty bspcName;
    private StringProperty bdeliveryDate;
    private StringProperty breturnDate;
    private FloatProperty brepairCost;
    private StringProperty breturnStatus;
    private IntegerProperty bspcID;


    public SPCVehicle(ResultSet rs) {
        try {
            this.bvehicleRegistrationNumber = new SimpleStringProperty(rs.getString("bvehicleRegistrationNumber"));
            this.bmodel = new SimpleStringProperty(rs.getString("bmodel"));
            this.bmake = new SimpleStringProperty(rs.getString("bmake"));
            this.bcustomerID = new SimpleIntegerProperty(rs.getInt("bcustomerID"));
            this.bfirstName = new SimpleStringProperty(rs.getString("bfirstName"));
            this.blastName = new SimpleStringProperty(rs.getString("blastName"));
            this.bspcName = new SimpleStringProperty(rs.getString("bspcName"));
            this.bdeliveryDate = new SimpleStringProperty(rs.getString("bdeliveryDate"));
            this.breturnDate = new SimpleStringProperty(rs.getString("breturnDate"));
            this.brepairCost = new SimpleFloatProperty(rs.getFloat("brepairCost"));
            this.breturnStatus = new SimpleStringProperty(rs.getString("breturnStatus"));
            this.bspcID = new SimpleIntegerProperty(rs.getInt("bspcID"));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public String getBmake() {
        return bmake.get();
    }

    public StringProperty bspcbmakeProperty() {
        return bmake;
    }

    public void setbmake(String bmake) {
        this.bmake.set(bmake);
    }

    public String getbmodel() {
        return bmodel.get();
    }

    public StringProperty bmodelProperty() {
        return bmodel;
    }

    public void setbmodel(String bmodel) {
        this.bmodel.set(bmodel);
    }

    public String getbvehicleRegistrationNumber() {
        return bvehicleRegistrationNumber.get();
    }

    public StringProperty bvehicleRegistrationNumberProperty() {
        return bvehicleRegistrationNumber;
    }

    public void setbvehicleRegistrationNumber(String bvehicleRegistrationNumber) {
        this.bvehicleRegistrationNumber.set(bvehicleRegistrationNumber);
    }


    public Integer getbcustomerID() {
        return bcustomerID.get();
    }

    public IntegerProperty bcustomerIDProperty() {
        return bcustomerID;
    }

    public void setbcustomerID(Integer bcustomerID) {
        this.bcustomerID.set(bcustomerID);
    }

    public String getbfirstName() {
        return bfirstName.get();
    }

    public StringProperty bfirstNameProperty() {
        return bfirstName;
    }

    public void setbfirstName(String bfirstName) {
        this.bfirstName.set(bfirstName);
    }

    public String getblastName() {
        return blastName.get();
    }

    public StringProperty blastNameProperty() {
        return blastName;
    }
    public void setblastName(String blastName) {
        this.blastName.set(blastName);
    }

    public String getbspcName() {
        return bspcName.get();
    }

    public StringProperty bspcNameProperty() {
        return bspcName;
    }

    public void setbspcName(String bspcName) {
        this.bspcName.set(bspcName);
    }

    public String getbdeliveryDate() {
        return bdeliveryDate.get();
    }

    public StringProperty bdeliveryDateProperty() {
        return bdeliveryDate;
    }
    public void setbdeliveryDate(String bdeliveryDate) {
        this.bdeliveryDate.set(bdeliveryDate);
    }

    public String getbreturnDate() {
        return breturnDate.get();
    }

    public StringProperty breturnDateProperty() {
        return breturnDate;
    }

    public void setbreturndate(String breturnDate) {
        this.breturnDate.set(breturnDate);
    }

    public Float getbrepairCost() {
        return brepairCost.get();
    }

    public FloatProperty brepairCostProperty() {
        return brepairCost;
    }
    public void setbrepaircost(Float brepairCost) {
        this.brepairCost.set(brepairCost);
    }
    public String getbreturnStatus() {
        return breturnStatus.get();
    }

    public StringProperty breturnStatusProperty() {
        return breturnStatus;
    }
    public void setbreturnStatus(String breturnStatus) {
        this.breturnStatus.set(breturnStatus);
    }

    public Integer getbspcID() {
        return bspcID.get();
    }

    public IntegerProperty bspcIDProperty() {
        return bspcID;
    }

    public void setbspcID(Integer bspcID) {
        this.bspcID.set(bspcID);
    }


}
