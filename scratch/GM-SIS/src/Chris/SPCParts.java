package Chris;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Christopher Chrysostomou on 24/02/20a7.
 */
public class SPCParts {
    private IntegerProperty aspcID;
    private IntegerProperty aspcPartID;
    private StringProperty aspcPartName;
    private StringProperty aspcPartDescription;
    private IntegerProperty acustomerID;
    private StringProperty afirstName;
    private StringProperty alastName;
    private StringProperty aspcName;
    private StringProperty adeliveryDate;
    private StringProperty areturnDate;
    private FloatProperty arepairCost;
    private StringProperty areturnStatus;


    public SPCParts(ResultSet rs) {
        try {
            this.aspcPartID = new SimpleIntegerProperty(rs.getInt("aspcPartID"));
            this.aspcPartName = new SimpleStringProperty(rs.getString("aspcPartName"));
            this.aspcPartDescription = new SimpleStringProperty(rs.getString("aspcPartDescription"));
            this.acustomerID = new SimpleIntegerProperty(rs.getInt("acustomerID"));
            this.afirstName = new SimpleStringProperty(rs.getString("afirstName"));
            this.alastName = new SimpleStringProperty(rs.getString("alastName"));
            this.aspcName = new SimpleStringProperty(rs.getString("aspcName"));
            this.adeliveryDate = new SimpleStringProperty(rs.getString("adeliveryDate"));
            this.areturnDate = new SimpleStringProperty(rs.getString("areturnDate"));
            this.arepairCost = new SimpleFloatProperty(rs.getFloat("arepairCost"));
            this.areturnStatus = new SimpleStringProperty(rs.getString("areturnStatus"));
            this.aspcID = new SimpleIntegerProperty(rs.getInt("aspcID"));

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public Integer getAspcID() {
        return aspcID.get();
    }

    public IntegerProperty aspcIDProperty() {
        return aspcID;
    }

    public void setaspcID(Integer aspcID) {
        this.aspcID.set(aspcID);
    }


    public Integer getAspcPartID() {
        return aspcPartID.get();
    }

    public IntegerProperty aspcPartIDProperty() {
        return aspcPartID;
    }

    public void setaspcPartID(Integer aspcPartID) {
        this.aspcPartID.set(aspcPartID);
    }


    public String getAspcPartName() {
        return aspcPartName.get();
    }

    public StringProperty aspcPartNameProperty() {
        return aspcPartName;
    }
    public void setaspcPartName(String aspcPartName) {
        this.aspcPartName.set(aspcPartName);
    }

    public void setaspcPartDescription(String aspcPartDescription) {
        this.aspcPartDescription.set(aspcPartDescription);
    }

    public String getAspcPartDescription() {
        return aspcPartDescription.get();
    }

    public StringProperty aspcPartDescriptionProperty() {
        return aspcPartDescription;
    }



    public Integer getAcustomerID() {
        return acustomerID.get();
    }

    public IntegerProperty acustomerIDProperty() {
        return acustomerID;
    }

    public void setacustomerID(Integer acustomerID) {
        this.acustomerID.set(acustomerID);
    }

    public String getAfirstName() {
        return afirstName.get();
    }

    public StringProperty afirstNameProperty() {
        return afirstName;
    }

    public void setafirstName(String afirstName) {
        this.afirstName.set(afirstName);
    }

    public String getAlastName() {
        return alastName.get();
    }

    public StringProperty alastNameProperty() {
        return alastName;
    }
    public void setalastName(String alastName) {
        this.alastName.set(alastName);
    }

    public String getAspcName() {
        return aspcName.get();
    }

    public StringProperty aspcNameProperty() {
        return aspcName;
    }

    public void setaspcName(String aspcName) {
        this.aspcName.set(aspcName);
    }

    public String getAdeliveryDate() {
        return adeliveryDate.get();
    }

    public StringProperty adeliveryDateProperty() {
        return adeliveryDate;
    }
    public void setadeliveryDate(String adeliveryDate) {
        this.adeliveryDate.set(adeliveryDate);
    }

    public String getAreturnDate() {
        return areturnDate.get();
    }

    public StringProperty areturnDateProperty() {
        return areturnDate;
    }

    public void setareturndate(String areturnDate) {
        this.areturnDate.set(areturnDate);
    }

    public Float getArepairCost() {
        return arepairCost.get();
    }

    public FloatProperty arepairCostProperty() {
        return arepairCost;
    }
    public void setarepaircost(Float arepairCost) {
        this.arepairCost.set(arepairCost);
    }
    public String getAreturnStatus() {
        return areturnStatus.get();
    }

    public StringProperty areturnStatusProperty() {
        return areturnStatus;
    }
    public void setareturnStatus(String areturnStatus) {
        this.areturnStatus.set(areturnStatus);
    }

}
