package PartsLogic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MohammedKhan on 16/03/2017.
 */
public class OrderParts {
    private IntegerProperty orderID;
    private StringProperty partID;
    private StringProperty expectedDate;
    private StringProperty quantity;

    public OrderParts(ResultSet rs) {
        try {
            this.orderID = new SimpleIntegerProperty(rs.getInt("orderID"));
            this.partID = new SimpleStringProperty(rs.getString("partID"));
            this.expectedDate = new SimpleStringProperty(rs.getString("expectedDate"));
            this.quantity = new SimpleStringProperty(rs.getString("quantity"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getOrderID() {
        return orderID.get();
    }

    public IntegerProperty orderIDProperty() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID.set(orderID);
    }

    public String getPartID() {
        return partID.get();
    }

    public StringProperty partIDProperty() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID.set(partID);
    }

    public String getExpectedDate() {
        return expectedDate.get();

    }
    public StringProperty expectedDateProperty(){
        return expectedDate;
    }
    public void setExpectedDate(String expectedDate){
        this.expectedDate.set(expectedDate);
    }
    public String getQuantity(){
        return quantity.get();
    }
    public StringProperty quantityProperty(){
        return quantity;
    }
    public void setQuantity(String quantity){
        this.quantity.set(quantity);
    }
}
