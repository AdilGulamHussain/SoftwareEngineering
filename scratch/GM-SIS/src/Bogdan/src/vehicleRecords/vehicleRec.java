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

public class vehicleRec {

    private StringProperty vNumber;
    private IntegerProperty vCustomer;
    private StringProperty vModel;
    private StringProperty vMake;
    private StringProperty vEngine;
    private StringProperty vFuel;
    private StringProperty vColour;
    private StringProperty vMOT;
    private StringProperty vLastService;
    private IntegerProperty vMile;


    public vehicleRec(ResultSet rs)
    {
        try {
            this.vNumber = new SimpleStringProperty(rs.getString("VehicleRegistrationNumber"));
            this.vCustomer = new SimpleIntegerProperty(rs.getInt("CustomerID"));
            this.vModel = new SimpleStringProperty(rs.getString("Model"));
            this.vMake = new SimpleStringProperty(rs.getString("Make"));
            this.vEngine = new SimpleStringProperty(rs.getString("EngineSize"));
            this.vFuel = new SimpleStringProperty(rs.getString("FuelType"));
            this.vColour = new SimpleStringProperty(rs.getString("Colour"));
            this.vMOT = new SimpleStringProperty(rs.getString("MOTRenewalDate"));
            this.vLastService = new SimpleStringProperty(rs.getString("LastServiceDate"));
            this.vMile = new SimpleIntegerProperty(rs.getInt("CurrentMileage"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getvNumber() {
        return vNumber.get();
    }

    public StringProperty vNumberProperty() {
        return vNumber;
    }

    public void setvNumber(String vNumber) {
        this.vNumber.set(vNumber);
    }


    public int getCustomer() {
        return vCustomer.get();
    }

    public IntegerProperty vCustomerProperty() {
        return vCustomer;
    }

    public void setCustomer(int vCustomer) {
        this.vCustomer.set(vCustomer);
    }


    public String getvModel() {
        return vModel.get();
    }

    public StringProperty vModelProperty() {
        return vModel;
    }

    public void setvModel(String vModel) {
        this.vModel.set(vModel);
    }


    public String getvMake() {
        return vMake.get();
    }

    public StringProperty vMakeProperty() {
        return vMake;
    }

    public void setvMake(String vMake) {
        this.vMake.set(vMake);
    }


    public String getvEngine() {
        return vEngine.get();
    }

    public StringProperty vEngineProperty() {
        return vEngine;
    }

    public void setvEngine(String add) {
        this.vEngine.set(add);
    }


    public String getvFuel() {
        return vFuel.get();
    }

    public StringProperty vFuelProperty() {
        return vFuel;
    }

    public void setvFuel(String vFuel) {
        this.vFuel.set(vFuel);
    }


    public String getvColour() {
        return vColour.get();
    }

    public StringProperty vColourProperty() {
        return vColour;
    }

    public void setvColour(String vColour) {
        this.vColour.set(vColour);
    }


    public String getvMOT() {
        return vMOT.get();
    }

    public StringProperty vMOTProperty() {
        return vMOT;
    }

    public void setvMOT(String vMOT) {
        this.vMOT.set(vMOT);
    }


    public String getvLastService() { return vLastService.get(); }

    public StringProperty vLastServiceProperty() { return vLastService; }

    public void setvLastService(String vLastService) { this.vLastService.set(vLastService); }


    public int getvMile() {
        return vMile.get();
    }

    public IntegerProperty vMileProperty() {
        return vMile;
    }

    public void setvMile(int vMile) {
        this.vMile.set(vMile);
    }
}


