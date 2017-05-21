package Bogdan.src.vehicleRecords;

import common.logic.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class VehicleRecController implements Initializable {

    private ArrayList<vehicleRec> Vehicles = new ArrayList<>();

    @FXML private TableView<vehicleRec> vehicleTable;
    @FXML private TableColumn<vehicleRec, String> vNumber;
    @FXML private TableColumn<vehicleRec, String> vModel;
    @FXML private TableColumn<vehicleRec, String> vMake;
    @FXML private TableColumn<vehicleRec, String> vEngine;
    @FXML private TableColumn<vehicleRec, String> vFuel;
    @FXML private TableColumn<vehicleRec, String> vColour;
    @FXML private TableColumn<vehicleRec, String> vMOT;
    @FXML private TableColumn<vehicleRec, String> vLastService;
    @FXML private TableColumn<vehicleRec, Integer> vMile;

    @FXML private TableView<Bkg> bookingTable;
    @FXML private TableColumn<Bkg, Integer> bookingID;
    @FXML private TableColumn<Bkg, String> vehicle;
    @FXML private TableColumn<Bkg, String> bookingType;
    @FXML private TableColumn<Bkg, String> date;
    @FXML private TableColumn<Bkg, Integer> spc;

    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, String> vehicleID;
    @FXML private TableColumn<Part, Integer> partBookingID;
    @FXML private TableColumn<Part, String> name;
    @FXML private TableColumn<Part, String> desc;
    @FXML private TableColumn<Part, String> cost;

    @FXML private TableView<Bill> billTable;
    @FXML private TableColumn<Bill, Integer> billID;
    @FXML private TableColumn<Bill,Integer> billBookingID;
    @FXML private TableColumn<Bill,Float> price;
    @FXML private TableColumn<Bill,Float> paid;
    @FXML private TableColumn<Bill,Float> balance;

    @FXML private TextField search_bar;

    private SQLiteConnection db = SQLiteConnection.getInstance();
    ResultSet rs = null;

    private static vehicleRec selected;

    public void addVehicle(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/addVehicleTest.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void editVehicle(ActionEvent event) throws Exception {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/editVehicle.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void editWarranty(ActionEvent event) throws Exception {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/editWarranty.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }


    }

    public void checkWarranty(ActionEvent event) throws Exception {
            ResultSet rs = db.query("SELECT VehicleRegistration, Warranty, WarrantyCompany, WarrantyAddress, WarrantyExpiry FROM Warranty WHERE VehicleRegistration='" + VehicleRecController.getSelected().getvNumber() + "'");

            if (rs.next()) {
                try {
                    vehicleRec selected = vehicleTable.getSelectionModel().getSelectedItem();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/vehicleWarranty.fxml"));
                    Parent root1 = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Vehicle has no warranty.");
                alert.showAndWait();
            }

    }

    public void deleteVehicle(ActionEvent event)
    {
        try {
            vehicleRec selected = vehicleTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Vehicle");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete " + selected.getvNumber() + " " + selected.getCustomer() + "?");
            Optional<ButtonType> response = alert.showAndWait();
            if (response.get() == ButtonType.OK) {
                String stmt = "DELETE FROM Vehicles WHERE VehicleRegistrationNumber = \'" + selected.getvNumber() + "\'";
                db.update(stmt);
                String stmt2 = "DELETE FROM Warranty WHERE VehicleRegistration = \'" + selected.getvNumber() + "\'";
                db.update(stmt2);
                refreshTable();
            }
        }

        catch (NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Please select a vehicle from the table first.");
            alert.showAndWait();
        }
    }

    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        refreshTable();
        String sql = "SELECT *  FROM Vehicles";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                Vehicles.add(new vehicleRec(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        vehicleTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected = vehicleTable.getSelectionModel().getSelectedItem();
                    fillBookingTable();
                    fillPartTable();
                    fillBillsTable();
                }
        );

        sql = "SELECT *  FROM Vehicles";
        rs = db.query(sql);
        try {
            while (rs.next())
            {
                Vehicles.add(new vehicleRec(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void refreshTable()
    {
        setValueFactories();
        ArrayList<vehicleRec> tableValues = new ArrayList<>();
        String sql = "SELECT *  FROM Vehicles";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                tableValues.add(new vehicleRec(rs));
            }
            vehicleTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static vehicleRec getSelected() {
        return selected;
    }

    private void fillBookingTable()
    {
        bookingID.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        vehicle.setCellValueFactory(new PropertyValueFactory<>("vehicleReg"));
        bookingType.setCellValueFactory(new PropertyValueFactory<>("bookingType"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        spc.setCellValueFactory(new PropertyValueFactory<>("spc"));
        ArrayList<Bkg> tableValues = new ArrayList<>();
        try
        {

            String sql = "SELECT VehicleRegistrationNumber FROM Vehicles WHERE CustomerID = " + selected.getCustomer();
            System.out.println(selected.getCustomer());
            ResultSet rs = db.query(sql);
            try {
                while (rs.next())
                {
                    ResultSet rs1 = db.query("SELECT * FROM Booking WHERE VehicleRegistration = '"+ rs.getString("VehicleRegistrationNumber") +"'");
                    while(rs1.next()) {
                        tableValues.add(new Bkg(rs1));
                        //System.out.print(tableValues);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            bookingTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (NullPointerException e)
        {

        }

    }

    private void fillBillsTable()
    {
        billBookingID.setCellValueFactory(new PropertyValueFactory<>("booking"));
        billID.setCellValueFactory(new PropertyValueFactory<>("id"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        ArrayList<Bill> tableValues = new ArrayList<>();
        try {
            String sql = "SELECT VehicleRegistrationNumber FROM Vehicles WHERE CustomerID = " + selected.getCustomer();
            ResultSet rs = db.query(sql);

            while (rs.next()) {
                ResultSet rs1 = db.query("SELECT BookingID FROM Booking WHERE VehicleRegistration = '" + rs.getString("VehicleRegistrationNumber") + "'");
                while(rs1.next())
                {
                    ResultSet rs2 = db.query("SELECT * FROM Bills WHERE BookingID = " + rs1.getString("BookingID"));
                    while (rs2.next()) {
                        tableValues.add(new Bill(rs2));
                    }
                }
            }
            billTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e)
        {

        }

    }

    private void fillPartTable()
    {
        vehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistrationNumber"));
        partBookingID.setCellValueFactory(new PropertyValueFactory<>("booking"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        ArrayList<Part> tableValues = new ArrayList<>();
        try {
            String sql = "SELECT VehicleRegistrationNumber  FROM Vehicles WHERE CustomerID = " + selected.getCustomer();
            ResultSet rs = db.query(sql);

            while (rs.next()) {
                ResultSet rs2 = db.query("SELECT * FROM InstalledParts WHERE vehicleRegistrationNumber = '" + rs.getString("VehicleRegistrationNumber") + "'");
                while (rs2.next())
                {
                    tableValues.add(new Part(rs2));
                }
            }
            partTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e)
        {
        }

    }

    @FXML
    public void search()
    {

        try {
            vehicleTable.getItems().clear();
            setValueFactories();
            ArrayList<vehicleRec> searchedItems = new ArrayList<>();
            for (vehicleRec vehicle : Vehicles) {
                if ((vehicle.getvNumber().toLowerCase().contains(search_bar.getText().toLowerCase()))) {
                    vehicleTable.getItems().clear();
                    searchedItems.add(vehicle);
                }
                if ((vehicle.getvMake().toLowerCase().contains(search_bar.getText().toLowerCase()))) {
                    vehicleTable.getItems().clear();
                    searchedItems.add(vehicle);
                }
            }
            ObservableList searched = FXCollections.observableArrayList(searchedItems);
            vehicleTable.setItems(searched);
        } catch (NullPointerException e)
        {
            System.out.println("Empty");
        }
    }


    private void setValueFactories()
    {
        vNumber.setCellValueFactory(new PropertyValueFactory<>("vNumber"));
        vModel.setCellValueFactory(new PropertyValueFactory<>("vModel"));
        vMake.setCellValueFactory(new PropertyValueFactory<>("vMake"));
        vEngine.setCellValueFactory(new PropertyValueFactory<>("vEngine"));
        vFuel.setCellValueFactory(new PropertyValueFactory<>("vFuel"));
        vColour.setCellValueFactory(new PropertyValueFactory<>("vColour"));
        vMOT.setCellValueFactory(new PropertyValueFactory<>("vMOT"));
        vLastService.setCellValueFactory(new PropertyValueFactory<>("vLastService"));
        vMile.setCellValueFactory(new PropertyValueFactory<>("vMile"));
    }
}