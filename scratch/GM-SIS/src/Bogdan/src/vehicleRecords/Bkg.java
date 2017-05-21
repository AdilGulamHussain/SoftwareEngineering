package Bogdan.src.vehicleRecords;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bkg {
    private IntegerProperty bookingID;
    private StringProperty bookingType;
    private StringProperty date;
    private IntegerProperty spc;
    private StringProperty vehicleReg;
    public Bkg(ResultSet rs)
    {
        try {
            this.bookingID = new SimpleIntegerProperty(rs.getInt("BookingID"));
            this.bookingType = new SimpleStringProperty(rs.getString("BookingType"));
            this.date = new SimpleStringProperty(rs.getString("Date"));
            this.spc = new SimpleIntegerProperty(rs.getInt("SPC"));
            this.vehicleReg = new SimpleStringProperty(rs.getString("VehicleRegistration"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public int getSpc() {
        return spc.get();
    }

    public IntegerProperty spcProperty() {
        return spc;
    }

    public void setSpc(int spc) {
        this.spc.set(spc);
    }

    public int getBookingID() {
        return bookingID.get();
    }

    public IntegerProperty bookingIDProperty() {
        return bookingID;
    }

    public String getBookingType() {
        return bookingType.get();
    }

    public StringProperty bookingTypeProperty() {
        return bookingType;
    }

    public void setBookingID(int bookingID) {
        this.bookingID.set(bookingID);
    }

    public void setBookingType(String bookingType) {
        this.bookingType.set(bookingType);
    }

    public String getVehicleReg() {
        return vehicleReg.get();
    }

    public StringProperty vehicleRegProperty() {
        return vehicleReg;
    }

    public void setVehicleReg(String vehicleReg) {
        this.vehicleReg.set(vehicleReg);
    }
}
