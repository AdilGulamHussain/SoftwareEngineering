package Bogdan.src.vehicleRecords;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddVehicle {

    private IntegerProperty ID;
    private StringProperty Model;
    private StringProperty Make;
    private StringProperty Engine;
    private StringProperty Fuel;


    public AddVehicle(ResultSet rs)
    {
        try {
            this.ID = new SimpleIntegerProperty(rs.getInt("TemplateID"));
            this.Make = new SimpleStringProperty(rs.getString("Make"));
            this.Model = new SimpleStringProperty(rs.getString("Model"));
            this.Engine = new SimpleStringProperty(rs.getString("EngineSize"));
            this.Fuel = new SimpleStringProperty(rs.getString("FuelType"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int getTemplateID() {
        return ID.get();
    }

    public IntegerProperty TIDProperty() {
        return ID;
    }

    public void setTID(int ID) {
        this.ID.set(ID);
    }

    public String getModel() {
        return Model.get();
    }

    public StringProperty vModelProperty() {
        return Model;
    }

    public void setvModel(String Model) {
        this.Model.set(Model);
    }


    public String getMake() {
        return Make.get();
    }

    public StringProperty vMakeProperty() {
        return Make;
    }

    public void setvMake(String Make) {
        this.Make.set(Make);
    }


    public String getEngine() {
        return Engine.get();
    }

    public StringProperty vEngineProperty() {
        return Engine;
    }

    public void setvEngine(String Engine) {
        this.Engine.set(Engine);
    }


    public String getFuel() {
        return Fuel.get();
    }

    public StringProperty vFuelProperty() {
        return Fuel;
    }

    public void setvFuel(String Fuel) {
        this.Fuel.set(Fuel);
    }

}