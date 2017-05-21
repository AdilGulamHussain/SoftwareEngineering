package Donovan.common.logic;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Booking {
    private StringProperty fname;
    private StringProperty sname;
    private IntegerProperty id;
    private StringProperty bType;
    private StringProperty Date;
    private StringProperty SPC;
    private StringProperty Part;
    private StringProperty Complete;
    private FloatProperty costs;
    private StringProperty vreg;

    public Booking (ResultSet rsb)
    {
        try {
            this.fname = new SimpleStringProperty(rsb.getString("FirstName"));
            this.sname = new SimpleStringProperty(rsb.getString("Surname"));
            this.id = new SimpleIntegerProperty(rsb.getInt("BookingID"));
            this.vreg = new SimpleStringProperty(rsb.getString("VehicleRegistration"));
            this.bType = new SimpleStringProperty(rsb.getString("BookingType"));
            this.Date = new SimpleStringProperty(rsb.getString("Date"));
            this.SPC = new SimpleStringProperty(rsb.getString("SPC"));
            this.Part = new SimpleStringProperty(rsb.getString("Part"));
            this.Complete = new SimpleStringProperty(rsb.getString("Complete"));
            this.costs = new SimpleFloatProperty(rsb.getFloat("Costs"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getFirstName() {return fname.get();}

    public StringProperty fnameProperty() {
        return fname;
    }

    public void setFirstName(String fname) {this.fname.set(fname);}

    public String getSurname() {return sname.get();}

    public StringProperty snameProperty() {
        return sname;
    }

    public void setSurname(String sname) {this.sname.set(sname);}

    public int getBookingID() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setBookingID(int id) {
        this.id.set(id);
    }

    public String getVehicleRegistration() {return vreg.get();}

    public StringProperty vregProperty() {
        return vreg;
    }

    public void setVehicleRegistration(String vreg) {this.vreg.set(vreg);}

    public String getBookingTtype() {
        return bType.get();
    }

    public StringProperty bTypeProperty() {
        return bType;
    }

    public void setBookingTtype(String bType) {
        this.bType.set(bType);
    }

    public String getDate() {
        return Date.get();
    }

    public StringProperty DateProperty() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date.set(Date);
    }

    public String getSPC() {
        return SPC.get();
    }

    public StringProperty SPCProperty() {
        return SPC;
    }

    public void setSPC(String SPC) {
        this.SPC.set(SPC);
    }

    public String getPart() {
        return Part.get();
    }

    public StringProperty PartProperty() {
        return Part;
    }

    public void setPart(String Part) {
        this.Part.set(Part);
    }

    public String getComplete() {
        return Complete.get();
    }

    public StringProperty CompleteProperty() {
        return Complete;
    }

    public void setComplete(String Complete) {this.Complete.set(Complete);}

    public Float getCosts() {
        return costs.get();
    }

    public FloatProperty costsProperty() {
        return costs;
    }

    public void setCosts(Float costs) {
        this.costs.set(costs);
    }




}
