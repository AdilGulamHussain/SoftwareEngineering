package Donovan.common.logic;

import common.logic.SQLiteConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookingController{


    @FXML public Button cancel;
    @FXML public Button add;
    @FXML public TextField FName;
    @FXML public TextField SName;
    @FXML public TextField ID;
    @FXML public TextField vReg;
    @FXML public TextField bookingT;
    @FXML public TextField Date;
    @FXML public TextField Spc;
    @FXML public TextField part;
    @FXML public TextField complete;
    @FXML public TextField costs;

    public int paid = 0;

    public void addNewBooking(ActionEvent a) {
        try {
            SQLiteConnection dbc = SQLiteConnection.getInstance();
            String stmt = "INSERT INTO Booking (FirstName, Surname, BookingID, VehicleRegistration, BookingType, Date, SPC, Part, Complete, Costs) VALUES ('" + FName.getText() + "', '" + SName.getText() + "', '" + ID.getText() + "', '" + vReg.getText() + "', '" + bookingT.getText() + "', '" + Date.getText() + "', '" + Spc.getText() + "', '" + part.getText().toLowerCase() + "', '" + complete.getText() + "' ,'" + Integer.parseInt(costs.getText()) + "')";
            dbc.update(stmt);
            String stmt1 = "INSERT INTO Bills (BookingID, TotalCost, Paid) VALUES ('" + ID.getText() + "', '" + Integer.parseInt(costs.getText()) + "', '" + paid + "')";
            dbc.update(stmt1);
            dbc.update("INSERT INTO InstalledParts (vehicleRegistrationNumber, bookingID, installationDate )\n" +
                    "SELECT VehicleRegistration, BookingID, Date FROM Booking\n" +
                    "WHERE Part = \"yes\";");



            ((Node) (a.getSource())).getScene().getWindow().hide();

        }
        catch (NullPointerException c) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("One of the fields is empty");
            alert.showAndWait();

        }

    }

    public void cancelNewBooking(ActionEvent c) {
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        }


}


