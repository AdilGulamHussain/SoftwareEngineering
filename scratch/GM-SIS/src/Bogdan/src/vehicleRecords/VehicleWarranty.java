package Bogdan.src.vehicleRecords;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Bogdan on 16/03/2017.
 */

public class VehicleWarranty {

    private StringProperty wNumber;
    private IntegerProperty wStatus;
    private StringProperty wCompany;
    private StringProperty wAddress;
    private StringProperty wExpiry;


    public VehicleWarranty(ResultSet rs) {
        try {
            this.wNumber = new SimpleStringProperty(rs.getString("VehicleRegistration"));
            this.wStatus = new SimpleIntegerProperty(rs.getInt("Warranty"));
            this.wCompany = new SimpleStringProperty(rs.getString("WarrantyCompany"));
            this.wAddress = new SimpleStringProperty(rs.getString("WarrantyAddress"));
            this.wExpiry = new SimpleStringProperty(rs.getString("WarrantyExpiry"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getwNumber() {
        return wNumber.get();
    }

    public StringProperty wNumberProperty() {
        return wNumber;
    }

    public void setwNumber(String wNumber) {
        this.wNumber.set(wNumber);
    }


    public int getwStatus() {
        return wStatus.get();
    }

    public IntegerProperty wStatusProperty() {
        return wStatus;
    }

    public void setwStatus(int wStatus) {
        this.wStatus.set(wStatus);
    }


    public String getwCompany() {
        return wCompany.get();
    }

    public StringProperty wCompanyProperty() {
        return wCompany;
    }

    public void setwCompany(String wCompany) {
        this.wCompany.set(wCompany);
    }


    public String getwAddress() {
        return wAddress.get();
    }

    public StringProperty wAddressProperty() {
        return wAddress;
    }

    public void setwAddress(String wAddress) {
        this.wAddress.set(wAddress);
    }


    public String getwExpiry() {
        return wExpiry.get();
    }

    public StringProperty wExpiryProperty() {
        return wExpiry;
    }

    public void setwExpiry(String wExpiry) {
        this.wExpiry.set(wExpiry);
    }



}