package Bogdan.src.vehicleRecords;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Part {
    private IntegerProperty id;
    private StringProperty desc;
    private StringProperty name;
    private IntegerProperty booking;
    private StringProperty vehicle;

    public Part(ResultSet rs)
    {
        try{
            this.id = new SimpleIntegerProperty(rs.getInt("partID"));
            this.vehicle = new SimpleStringProperty(rs.getString("vehicleRegistrationNumber"));
            this.booking = new SimpleIntegerProperty(rs.getInt("bookingID"));
            this.desc = new SimpleStringProperty(rs.getString("installationDate"));
            this.name = new SimpleStringProperty(rs.getString("warrantyDate"));
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public int getId() {return id.get();
    }

    public IntegerProperty idProperty() {return id;
    }

    public void setId(int id) {this.id.set(id);
    }

    public String getVehicle() {return vehicle.get();
    }

    public StringProperty vehicleProperty() {return vehicle;
    }

    public void setVehicle(String vehicle) {this.vehicle.set(vehicle);
    }

    public int getBooking() {return booking.get();
    }

    public IntegerProperty bookingProperty() {return booking;
    }

    public void setBooking(int booking) {this.booking.set(booking);
    }

    public String getDesc() {return desc.get();
    }

    public StringProperty descProperty() {return desc;
    }

    public void setDesc(String desc) {this.desc.set(desc);
    }

    public String getName() {return name.get();
    }

    public StringProperty nameProperty() {return name;
    }

    public String getVehicleRegistrationNumber() { return vehicle.get(); }

    public void setName(String name) {this.name.set(name);
    }
}
