package Bogdan.src.vehicleRecords;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import common.logic.SQLiteConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import Bogdan.src.vehicleRecords.VehicleRecController;
import Bogdan.src.vehicleRecords.vehicleRec;

public class EditVehicleController implements Initializable {
    private SQLiteConnection db = SQLiteConnection.getInstance();
    ResultSet rs = null;
    @FXML
    private TextField RegEdit;
    @FXML
    private TextField ModelEdit;
    @FXML
    private TextField MakeEdit;
    @FXML
    private TextField EngineEdit;
    @FXML
    private TextField FuelEdit;
    @FXML
    private TextField ColourEdit;
    @FXML
    private TextField MOTEdit;
    @FXML
    private TextField MileEdit;
    @FXML
    public vehicleRec selected = Bogdan.src.vehicleRecords.VehicleRecController.getSelected();

    public EditVehicleController() {
    }

    public void initialize(URL location, ResourceBundle resourceBundle) {

        try {

            this.RegEdit.setText(this.selected.getvNumber());
            this.ModelEdit.setText(this.selected.getvModel());
            this.MakeEdit.setText(this.selected.getvMake());
            this.EngineEdit.setText(this.selected.getvEngine());
            this.FuelEdit.setText(this.selected.getvFuel());
            this.ColourEdit.setText(this.selected.getvColour());
            this.MOTEdit.setText(this.selected.getvMOT());
            this.MileEdit.setText(String.valueOf(this.selected.getvMile()));

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

    @FXML
    public void edit(ActionEvent event) {
        try {
            SQLiteConnection e = SQLiteConnection.getInstance();
            String alert1 = "UPDATE Vehicles SET VehicleRegistrationNumber = \'" + this.RegEdit.getText() + "\', Model = \'" + this.ModelEdit.getText() + "\', Make = \'" + this.MakeEdit.getText() + "\', EngineSize = \'" + this.EngineEdit.getText() + "\', FuelType = \'" + this.FuelEdit.getText() + "\', Colour = \'" + this.ColourEdit.getText() + "\', MOTRenewalDate = \'" + this.MOTEdit.getText() + "\', CurrentMileage = \'" + this.MileEdit.getText() + "\' WHERE VehicleRegistrationNumber = \'" + this.selected.getvNumber() + "\'";
            e.update(alert1);
            ((Node)event.getSource()).getScene().getWindow().hide();
        } catch (NullPointerException var4) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("One of the fields is empty");
            alert.showAndWait();
        }

    }
}
