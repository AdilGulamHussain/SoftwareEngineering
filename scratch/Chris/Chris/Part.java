package Chris;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by adilh on 19/02/2017.
 */
public class Part {
    private IntegerProperty id;
    private StringProperty desc;
    private StringProperty name;
    private IntegerProperty booking;
    private StringProperty vehicle;

    public Part(ResultSet rs)
    {
        try{
            this.id = new SimpleIntegerProperty(rs.getInt("PartID"));
            this.desc = new SimpleStringProperty(rs.getString("Description"));
            this.name = new SimpleStringProperty(rs.getString("Name"));
            this.booking = new SimpleIntegerProperty(rs.getInt("BookingID"));
            this.vehicle = new SimpleStringProperty(rs.getString("VehicleID"));
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDesc() {
        return desc.get();
    }

    public StringProperty descProperty() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc.set(desc);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getBooking() {
        return booking.get();
    }

    public IntegerProperty bookingProperty() {
        return booking;
    }

    public void setBooking(int booking) {
        this.booking.set(booking);
    }

    public String getVehicle() {
        return vehicle.get();
    }

    public StringProperty vehicleProperty() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle.set(vehicle);
    }
}
