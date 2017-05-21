package common.logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Booking {
    private StringProperty fName;
    private StringProperty sName;
    private IntegerProperty id;
    private StringProperty vMan;
    private StringProperty vReg;
    private StringProperty mechanic;
    private IntegerProperty duration;
    private StringProperty bType;
    private StringProperty Date;
    private StringProperty SPC;
    private StringProperty Part;
    private StringProperty Complete;

    public Booking (ResultSet rsb)
    {
        try {
            this.fName = new SimpleStringProperty(rsb.getString("FirstName"));
            this.sName = new SimpleStringProperty(rsb.getString("Surname"));
            this.id = new SimpleIntegerProperty(rsb.getInt("BookingID"));
            this.vMan = new SimpleStringProperty(rsb.getString("VehicleManufacturer"));
            this.vReg = new SimpleStringProperty(rsb.getString("VehicleRegistration"));
            this.mechanic = new SimpleStringProperty(rsb.getString("Mechanic"));
            this.duration = new SimpleIntegerProperty(rsb.getInt("Duration"));
            this.bType = new SimpleStringProperty(rsb.getString("BookingType"));
            this.Date = new SimpleStringProperty(rsb.getString("Date"));
            this.SPC = new SimpleStringProperty(rsb.getString("SPC"));
            this.Part = new SimpleStringProperty(rsb.getString("Part"));
            this.Complete = new SimpleStringProperty(rsb.getString("Complete"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getFirstName() {return fName.get();}

    public StringProperty fNameProperty() {
        return fName;
    }

    public void setFirstName(String fName) {this.fName.set(fName);}

    public String getSurname() {
        return sName.get();
    }

    public StringProperty sNameProperty() {
        return sName;
    }

    public void setSurname(String sName) {
        this.sName.set(sName);
    }

    public int getBookingID() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setBookingID(int id) {
        this.id.set(id);
    }

    public String getVehicleManufacturer() {return vMan.get();}

    public StringProperty vManProperty() {return vMan;}

    public void setVehicleManufacturer(String vMan) {this.vMan.set(vMan);}

    public String getVehicleRegistration() {
        return vReg.get();
    }

    public StringProperty vRegProperty() {
        return vReg;
    }

    public void setVehicleRegistration(String vReg) {
        this.vReg.set(vReg);
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

    public void setDuration (Integer duration) {
        this.duration.set(duration);
    }

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



}
