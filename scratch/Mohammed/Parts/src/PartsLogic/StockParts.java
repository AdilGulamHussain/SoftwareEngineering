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
public class StockParts {
    private IntegerProperty partID;
    private StringProperty name;
    private StringProperty description;
    private StringProperty stockLevel;
    private StringProperty cost;

    public StockParts(){
        this.partID = partIDProperty();
        this.name = nameProperty();
        this.description = descriptionProperty();
        this.stockLevel = stockLevelProperty();
        this.cost = costProperty();
    }

    public StockParts(ResultSet rs) {
        try {
            this.partID = new SimpleIntegerProperty(rs.getInt("partID"));
            this.name = new SimpleStringProperty(rs.getString("name"));
            this.description = new SimpleStringProperty(rs.getString("description"));
            this.stockLevel = new SimpleStringProperty(rs.getString("stockLevel"));
            this.cost = new SimpleStringProperty(rs.getString("cost"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getpartID() {
        return partID.get();
    }

    public IntegerProperty partIDProperty() {
        return partID;
    }

    public void setPartID(Integer partID) {
        this.partID.set(partID);
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

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getStockLevel() {
        return stockLevel.get();
    }
    public StringProperty stockLevelProperty(){
        return stockLevel;
    }
    public void setStockLevel(String stockLevel){
        this.stockLevel.set(stockLevel);
    }
    public String getCost(){
        return cost.get();
    }
    public StringProperty costProperty(){
        return cost;
    }
    public void setCost(String cost){
        this.cost.set(cost);
    }

}
