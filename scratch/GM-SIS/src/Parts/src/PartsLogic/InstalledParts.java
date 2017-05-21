package Parts.src.PartsLogic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MohammedKhan on 16/03/2017.
 */
public class InstalledParts {
    private IntegerProperty bookingID;
    private StringProperty vehicleRegistrationNumber;
    private IntegerProperty partID;
    private StringProperty installationDate;
    private StringProperty warrantyDate;


    public InstalledParts(ResultSet rs) {
        try {
            this.partID = new SimpleIntegerProperty(rs.getInt("partID"));
            this.vehicleRegistrationNumber = new SimpleStringProperty(rs.getString("vehicleRegistrationNumber"));
            this.bookingID = new SimpleIntegerProperty(rs.getInt("bookingID"));
            this.installationDate = new SimpleStringProperty(rs.getString("installationDate"));
            this.warrantyDate = new SimpleStringProperty(rs.getString("warrantyDate"));
        } catch (SQLException e){
            e.printStackTrace();

        }
    }

    public Integer getPartID(){
        return partID.get();
    }
    public IntegerProperty partIDProperty(){
        return partID;
    }
    public void setpartID(Integer partID){
        this.partID.set(partID);
    }
    public String getVehicleRegistrationNumber(){
        return vehicleRegistrationNumber.get();
    }
    public StringProperty vehicleRegistrationNumberProperty(){
        return vehicleRegistrationNumber;
    }
    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber){
        this.vehicleRegistrationNumber.set(vehicleRegistrationNumber);
    }
    public Integer getBookingID(){
        return bookingID.get();
    }
    public IntegerProperty bookingIDProperty(){
        return bookingID;
    }
    public void setBookingID(int bookingID){
        this.bookingID.set(bookingID);
    }
    public String getInstallationDate(){
        return installationDate.get();
    }
    public StringProperty installationDateProperty(){
        return installationDate;
    }
    public void setInstallationDate(String installationDate){
        this.installationDate.set(installationDate);
    }
    public String getWarrantyDate(){
        return warrantyDate.get();
    }
    public StringProperty warrantyDateProperty(){
        return warrantyDate;
    }
    public void setWarrantyDate(String warrantyDate){
        this.warrantyDate.set(warrantyDate);
    }

}