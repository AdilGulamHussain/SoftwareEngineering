package Bogdan.src.vehicleRecords;

import common.logic.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public class AddVehicleController implements Initializable {

    private ArrayList<AddVehicle> VehicleTemplate = new ArrayList<>();

    @FXML private TableView<AddVehicle> templateTable;
    @FXML private TableColumn<AddVehicle, String> TemplateID;
    @FXML private TableColumn<AddVehicle, String> Make;
    @FXML private TableColumn<AddVehicle, String> Model;
    @FXML private TableColumn<AddVehicle, String> Engine;
    @FXML private TableColumn<AddVehicle, String> Fuel;

    private SQLiteConnection db = SQLiteConnection.getInstance();
    ResultSet rs = null;

    private static AddVehicle selected;

    @FXML private TextField warrantyCompany;
    @FXML private TextField warrantyExpiry;
    @FXML private TextField warrantyAddress;

    @FXML private TextField NumberIn;
    @FXML private TextField CustomerIn;
    @FXML private TextField ModelIn;
    @FXML private TextField MakeIn;
    @FXML private TextField EngineIn;
    @FXML private TextField FuelIn;
    @FXML private TextField ColourIn;
    @FXML private TextField MOTIn;
    @FXML private TextField MileIn;
    @FXML

    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        refresh();
        //String sql = "SELECT TemplateID, Make, Model, EngineSize, FuelType FROM VehicleTemplate";
        ResultSet rs = db.query("SELECT TemplateID, Make, Model, EngineSize, FuelType FROM VehicleTemplate");
        try {
            while (rs.next())
            {
                VehicleTemplate.add(new AddVehicle(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        templateTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected = templateTable.getSelectionModel().getSelectedItem();
                }
        );

        //sql = "SELECT TemplateID, Make, Model, EngineSize, FuelType FROM VehicleTemplate";
        rs = db.query("SELECT TemplateID, Make, Model, EngineSize, FuelType FROM VehicleTemplate");
        try {
            while (rs.next())
            {
                VehicleTemplate.add(new AddVehicle(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void refresh()
    {
        setValueFactories();
        ArrayList<AddVehicle> tableValues = new ArrayList<>();
        // String sql = "SELECT TemplateID, Make, Model, EngineSize, FuelType FROM VehicleTemplate";

        ResultSet rs = db.query("SELECT TemplateID, Make, Model, EngineSize, FuelType FROM VehicleTemplate");
        try {
            while (rs.next())
            {
                tableValues.add(new AddVehicle(rs));
            }
            templateTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static AddVehicle getSelected() {
        return selected;
    }


    private void setValueFactories()
    {
        TemplateID.setCellValueFactory(new PropertyValueFactory<>("TemplateID"));
        Make.setCellValueFactory(new PropertyValueFactory<>("Make"));
        Model.setCellValueFactory(new PropertyValueFactory<>("Model"));
        Engine.setCellValueFactory(new PropertyValueFactory<>("Engine"));
        Fuel.setCellValueFactory(new PropertyValueFactory<>("Fuel"));
    }

    public void add(ActionEvent event)
    {
        try {
            SQLiteConnection db = SQLiteConnection.getInstance();

            String stmt = "INSERT INTO Vehicles (VehicleRegistrationNumber, CustomerID, Model, Make, EngineSize, FuelType, Colour, MOTRenewalDate, LastServiceDate, CurrentMileage) VALUES ('" + NumberIn.getText() + "', '" + CustomerIn.getText() + "', '" + ModelIn.getText() + "', '" + MakeIn.getText() + "', '" + EngineIn.getText() + "', '" + FuelIn.getText() + "', '" + ColourIn.getText() + "', '" + MOTIn.getText() + "', '" + "00/00/0000" + "', '" + MileIn.getText() + "')";
            db.update(stmt);

            if(!(warrantyCompany.getText().isEmpty())) {
                String stmt2 = "INSERT INTO Warranty (VehicleRegistration, Warranty, WarrantyCompany, WarrantyAddress, WarrantyExpiry) VALUES ('" + NumberIn.getText() + "', '" + "1" + "', '" + warrantyCompany.getText() + "', '" + warrantyAddress.getText() + "', '" + warrantyExpiry.getText() + "')";
                db.update(stmt2);
            }

            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("One of the fields is empty");
            alert.showAndWait();
        }
    }


    public void importTemplate(ActionEvent event)
    {
        try {
            this.ModelIn.setText(selected.getModel());
            this.MakeIn.setText(selected.getMake());
            this.EngineIn.setText(selected.getEngine());
            this.FuelIn.setText(selected.getFuel());
        }

        catch (NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Please select a template before attempting to import.");
            alert.showAndWait();
        }

    }

    public void refreshTemplate(ActionEvent event)
    {
        refresh();
    }


}
