package Donovan.common.logic;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mechanic {
    private StringProperty mFname;
    private StringProperty duration;
    private StringProperty bookingT;

    public Mechanic(ResultSet rsb) {
        try {
            this.mFname = new SimpleStringProperty(rsb.getString("mName"));
            this.duration = new SimpleStringProperty(rsb.getString("duration"));
            this.bookingT = new SimpleStringProperty(rsb.getString("bookingT"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getmFName() {return mFname.get();}

    public StringProperty fnameProperty() {
        return mFname;
    }

    public void setmFname(String fname) {this.mFname.set(fname);    }

    public String getDuration() {return duration.get();    }

    public StringProperty durationProperty() {
        return duration;
    }

    public void setDuration(String duration) {this.duration.set(duration);    }

    public String getBookgingT() {return bookingT.get();    }

    public StringProperty bookingTProperty() {
        return bookingT;
    }

    public void setBookingT(String bookingT) {this.bookingT.set(bookingT);    }

}
