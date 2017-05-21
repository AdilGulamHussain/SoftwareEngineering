package Parts.src.PartsLogic;

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
    private IntegerProperty partID;
    private StringProperty expectedDate;
    private IntegerProperty quantity;

    public OrderParts(ResultSet rs) {
        try {
            this.orderID = new SimpleIntegerProperty(rs.getInt("orderID"));
            this.partID = new SimpleIntegerProperty(rs.getInt("partID"));
            this.expectedDate = new SimpleStringProperty(rs.getString("expectedDate"));
            this.quantity = new SimpleIntegerProperty(rs.getInt("quantity"));
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

    public Integer getPartID() {
        return partID.get();
    }

    public IntegerProperty partIDProperty() {
        return partID;
    }

    public void setPartID(Integer partID) {
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
    public Integer getQuantity(){
        return quantity.get();
    }
    public IntegerProperty quantityProperty(){
        return quantity;
    }
    public void setQuantity(Integer quantity){
        this.quantity.set(quantity);
    }
}
