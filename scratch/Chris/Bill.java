package common.logic;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by adilh on 19/02/2017.
 */
public class Bill {
    private IntegerProperty id;
    private IntegerProperty booking;
    private FloatProperty price;
    private FloatProperty paid;
    private FloatProperty balance;

            this.type = new SimpleIntegerProperty(rs.getString("type"));
            this.returnStatus = new SimpleIntegerProperty(rs.getString("returnStatus"));
            this.expectedDeliveryDate = new SimpleStringProperty(rs.getString("expectedDeliveryDate"));
            this.expectedReturnDate = new SimpleIntegerProperty(rs.getString("expectedReturnDate"));
            this.repairCost = new SimpleStringProperty(rs.getString("repairCost"));

    public Bill(ResultSet rs) {
        try {
            this.id = new SimpleIntegerProperty(rs.getInt("BillsID"));
            this.booking = new SimpleIntegerProperty(rs.getInt("BookingID"));
            this.price = new SimpleFloatProperty(rs.getFloat("TotalCost"));
            this.paid = new SimpleFloatProperty(rs.getFloat("Paid"));
            this.balance = new SimpleFloatProperty(rs.getFloat("TotalCost") - rs.getInt("Paid"));
        } catch (SQLException e)
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

    public int getBooking() {
        return booking.get();
    }

    public IntegerProperty bookingProperty() {
        return booking;
    }

    public void setBooking(int booking) {
        this.booking.set(booking);
    }

    public float getPrice() {
        return price.get();
    }

    public FloatProperty priceProperty() {
        return price;
    }

    public void setPrice(float price) {
        this.price.set(price);
    }

    public float getPaid() {
        return paid.get();
    }

    public FloatProperty paidProperty() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid.set(paid);
    }

    public float getBalance() {
        return balance.get();
    }

    public FloatProperty balanceProperty() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance.set(balance);
    }
}
