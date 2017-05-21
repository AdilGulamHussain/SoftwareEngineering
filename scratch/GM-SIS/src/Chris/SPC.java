package Chris;

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
            this.spcID = new SimpleIntegerProperty(rs.getInt("spcID"));
            this.spcName = new SimpleStringProperty(rs.getString("spcName"));
            this.spcPostcode = new SimpleStringProperty(rs.getString("spcPostcode"));
            this.spcAddress = new SimpleStringProperty(rs.getString("spcAddress"));
            this.spcPhone = new SimpleStringProperty(rs.getString("spcPhone"));
            this.spcEmail = new SimpleStringProperty(rs.getString("spcEmail"));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Integer getSpcID() {
        return spcID.get();
    }

    public IntegerProperty SPCIDProperty() {
        return spcID;
    }

    public void setSPCID(Integer spcID) {
        this.spcID.set(spcID);
    }

    public String getSpcName() {
        return spcName.get();
    }

    public StringProperty SPCNameProperty() {
        return spcName;
    }

    public void setSPCName(String spcName) {
        this.spcName.set(spcName);
    }

    public String getSpcPostcode() {
        return spcPostcode.get();
    }

    public StringProperty postcodeProperty() {
        return spcPostcode;
    }

    public void setSPCPostcode(String spcPostcode) {
        this.spcPostcode.set(spcPostcode);
    }

    public String getSpcAddress() {
        return spcAddress.get();
    }

    public StringProperty addressProperty() {
        return spcAddress;
    }

    public void setSPCAddress(String spcAddress) {
        this.spcAddress.set(spcAddress);
    }

    public String getSpcPhone() {
        return spcPhone.get();
    }

    public StringProperty phoneProperty() {
        return spcPhone;
    }

    public void setSPCPhone(String spcPhone) {
        this.spcPhone.set(spcPhone);
    }

    public String getSpcEmail() {
        return spcEmail.get();
    }

    public StringProperty emailProperty() {
        return spcEmail;
    }

    public void setSPCEmail(String spcEmail) {
        this.spcEmail.set(spcEmail);

    }



}
