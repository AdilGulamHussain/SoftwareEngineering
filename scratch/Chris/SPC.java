package common.logic;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Christopher Chrysostomou on 24/02/2017.
 */
public class SPC {
    private StringProperty spcName;
    private StringProperty spcPostcode;
    private StringProperty spcAddress;
    private StringProperty spcPhone;
    private StringProperty spcEmail;
    private IntegerProperty spcID;

    public SPC(ResultSet rs) {
        try {
            this.spcID = new SimpleIntegerProperty(rs.getString("spcID"));
            this.spcName = new SimpleStringProperty(rs.getString("Name"));
            this.spcPostcode = new SimpleStringProperty(rs.getString("Postcode"));
            this.spcAddress = new SimpleStringProperty(rs.getString("TotalCost"));
            this.spcPhone = new SimpleStringProperty(rs.getString("Phone Number:"));
            this.spcEmail = new SimpleStringProperty(rs.getString("spcEmail"));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Integer getSPCID() {
        return spcID.get();
    }

    public IntegerProperty SPCIDProperty() {
        return spcID;
    }

    public void setSPCID(Integer spcID) {
        this.spcID.set(spcID);
    }

    public String getSPCName() {
        return spcName.get();
    }

    public StringProperty SPCNameProperty() {
        return spcName;
    }

    public void setSPCName(String spcName) {
        this.spcName.set(spcName);
    }

    public String getSPCPostcode(String spcPostcode) {
        return spcPostcode.get();
    }

    public StringProperty postcodeProperty() {
        return spcPostcode;
    }

    public void setSPCPostcode(String spcPostcode) {
        this.spcPostcode.set(spcPostcode);
    }

    public String getSPCAddress() {
        return spcAddress.get();
    }

    public StringProperty addressProperty() {
        return spcAddress;
    }

    public void setSPCAddress(String spcAddress) {
        this.spcAddress.set(spcAddress);
    }

    public String getSPCPhone() {
        return spcPhone.get();
    }

    public StringProperty addressProperty() {
        return spcPhone;
    }

    public void setSPCPhone(String spcPhone) {
        this.spcPhone.set(spcPhone);
    }

    public String getSPCEmail() {
        return spcEmail.get();
    }

    public StringProperty emailProperty() {
        return spcEmail;
    }

    public void setSPCEmail(String spcEmail) {
        this.spcEmail.set(spcEmail);

    }

    
}
