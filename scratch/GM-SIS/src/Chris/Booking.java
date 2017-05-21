package Chris;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by adilh on 19/02/2017.
 */
public class Booking {
    private IntegerProperty bookingID;
    private StringProperty mechanic;
    private IntegerProperty duration;
    private StringProperty bookingType;
    private StringProperty date;
    private IntegerProperty spc;
    private IntegerProperty complete;
    private IntegerProperty part;
    private StringProperty vehicleReg;
    public Booking(ResultSet rs)
    {
        try {
            this.bookingID = new SimpleIntegerProperty(rs.getInt("BookingID"));
            this.mechanic = new SimpleStringProperty(rs.getString("Mechanic"));
            this.duration = new SimpleIntegerProperty(rs.getInt("Duration"));
            this.bookingType = new SimpleStringProperty(rs.getString("BookingType"));
            this.date = new SimpleStringProperty(rs.getString("Date"));
            this.spc = new SimpleIntegerProperty(rs.getInt("SPC"));
            this.complete = new SimpleIntegerProperty(rs.getInt("Complete"));
            this.part = new SimpleIntegerProperty(rs.getInt("Part"));
            this.vehicleReg = new SimpleStringProperty(rs.getString("VehicleRegistration"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getMechanic() {
        return mechanic.get();
    }

    public StringProperty mechanicProperty() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic.set(mechanic);
    }

    public int getDuration() {
        return duration.get();
    }

    public IntegerProperty durationProperty() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
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

    public int getComplete() {
        return complete.get();
    }

    public IntegerProperty completeProperty() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete.set(complete);
    }

    public int getPart() {
        return part.get();
    }

    public IntegerProperty partProperty() {
        return part;
    }

    public void setPart(int part) {
        this.part.set(part);
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
