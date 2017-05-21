package common.logic;

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
    @FXML public TextField fName;
    @FXML public TextField sName;
    @FXML public TextField ID;
    @FXML public TextField manufacturer;
    @FXML public TextField reg;
    @FXML public TextField mechanicName;
    @FXML public TextField duration;
    @FXML public TextField bookingT;
    @FXML public TextField Date;
    @FXML public TextField Spc;
    @FXML public TextField part;
    @FXML public TextField complete;


    public void addNewBooking(ActionEvent a) {
        try {
            databconenction dbc = databconenction.getInstance();
            String stmt = "INSERT INTO Booking (FirstName, Surname, BookingID, VehicleManufacturer, VehicleRegistration, Mechanic, Duration, BookingType, Date, SPC, Part, Complete) VALUES ('" + fName.getText() + "', '" + sName.getText() + "', '" + Integer.parseInt(ID.getText()) + "', '" + manufacturer.getText() + "', '" + reg.getText() + "', '" + mechanicName.getText() + "', '" + Integer.parseInt(duration.getText()) + "', '" + bookingT.getText() + "', '" + Date.getText() + "', '" + Spc.getText() + "', '" + part.getText() + "', '" + complete.getText() + "')";
            dbc.update(stmt);
            ((Node) (a.getSource())).getScene().getWindow().hide();



        } catch (NullPointerException c) {
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


