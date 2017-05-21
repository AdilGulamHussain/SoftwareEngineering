package Chris;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Christopher Chrysostomou on 24/02/2017.
 */
public class SPCRecords {
    private IntegerProperty repairID;
    private IntegerProperty bookingBookingID;
    private StringProperty spcName;
    private IntegerProperty type;
    private IntegerProperty returnStatus;
    private StringProperty expectedDeliveryDate;
    private IntegerProperty expectedReturnDate;
    private FloatProperty repairCost;

    public SPCRecords(ResultSet rs) {
        try {
            this.repairID = new SimpleIntegerProperty(rs.getString("repairID"));
            this.bookingBookingID = new SimpleIntegerProperty(rs.getString("bookingBookingID"));
            this.spcName = new SimpleStringProperty(rs.getString("spcName"));
            this.type = new SimpleIntegerProperty(rs.getString("type"));
            this.returnStatus = new SimpleIntegerProperty(rs.getString("returnStatus"));
            this.expectedDeliveryDate = new SimpleStringProperty(rs.getString("expectedDeliveryDate"));
            this.expectedReturnDate = new SimpleIntegerProperty(rs.getString("expectedReturnDate"));
            this.repairCost = new SimpleStringProperty(rs.getString("repairCost"));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Integer getrepairID() {
        return repairID.get();
    }

    public IntegerProperty repairIDProperty() {
        return repairID;
    }

    public void setrepairID(String repairID) {
        this.repairID.set(repairID);
    }

    public String getbookingBookingID(String bookingBookingID) {
        return bookingBookingID.get();
    }

    public StringProperty spcbookingBookingIDProperty() {
        return bookingBookingID;
    }

    public void setbookingBookingID(String bookingBookingID) {
        this.bookingBookingID.set(bookingBookingID);
    }

    public String getspcName() {
        return spcName.get();
    }

    public StringProperty spcNameProperty() {
        return spcName;
    }
        public void setSPCName(String spcName) {
        this.spcName.set(spcName);
    }



    public Integer getreturnStatus() {
        return returnStatus.get();
    }

    public IntegerProperty spcreturnStatusProperty() {
        return returnStatus;
    }

    public void setreturnStatus(Integer returnStatus) {
        this.returnStatus.set(returnStatus);
    }

    public Float getrepairCost(Float repairCost) {
        return repairCost.get();
    }

    public FloatProperty repairCostProperty() {
        return repairCost;
    }

    public void setrepairCost(Float repairCost) {
        this.repairCost.set(repairCost);
    }

    public String getexpectedDeliveryDate() {
        return expectedDeliveryDate.get();
    }

    public StringProperty expectedDeliveryDateProperty() {
        return expectedDeliveryDate;
    }
        public void setexpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate.set(expectedDeliveryDate);
    }

    public Integer getexpectedReturnDate() {
        return expectedReturnDate.get();
    }

    public IntegerProperty expectedReturnDateProperty() {
        return expectedReturnDate;
    }
        public void setexpectedReturnDate(Integer expectedReturnDate) {
        this.expectedReturnDate.set(expectedReturnDate);
    }

     
}
