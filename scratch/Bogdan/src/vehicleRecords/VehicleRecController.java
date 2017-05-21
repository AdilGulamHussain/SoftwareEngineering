package vehicleRecords;

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

    private DBConnection db = DBConnection.getInstance();
    ResultSet rs = null;

    private static vehicleRec selected;

    public void addVehicle(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/addVehicle.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void checkWarranty(ActionEvent event) throws Exception {
        try {
            vehicleRec selected = vehicleTable.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/vehicleWarranty.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteVehicle(ActionEvent event)
    {
        vehicleRec selected = vehicleTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GMSIS");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete " + selected.getvNumber() + " " + selected.getCustomer() + "?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK)
        {
            String stmt = "DELETE FROM Vehicles WHERE VehicleRegistrationNumber = \'" + selected.getvNumber() +"\'";
            db.update(stmt);
            refreshTable();
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
