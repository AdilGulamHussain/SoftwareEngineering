package common.logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class EditBookingController {
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

    private databconenction dbc = databconenction.getInstance();
    private Booking selected = RepairController.getSelected();

    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        fName.setText(selected.getFirstName());
        sName.setText(selected.getSurname());
        ID.setText(String.valueOf(selected.getBookingID()));
        manufacturer.setText(selected.getVehicleManufacturer());
        reg.setText(selected.getVehicleRegistration());
        mechanicName.setText(selected.getMechanic());
        duration.setText(String.valueOf(selected.getDuration()));
        bookingT.setText(selected.getBookingTtype());
        Date.setText(selected.getDate());
        Spc.setText(selected.getSPC());
        part.setText(selected.getPart());
        complete.setText(selected.getComplete());
    }

    public void editBooking(ActionEvent eb) {
        try {
            databconenction dbc = databconenction.getInstance();
            String stmt = "UPDATE Booking SET FirstName = '" + fName.getText() + "', Surname = '" + sName.getText() + "', BookingID = '" + ID.getText() + "', VehicleManufacturer = '" + manufacturer.getText() + "', VehicleRegistration = '" + reg.getText() + "', Mechanic = '" + mechanicName.getText() + "', Duration = '" + duration.getText() + "', BookingType = '" + bookingT.getText() + "', Date = '" + Date.getText() + "', SPC = '" + Spc.getText() + "', Part = '" + part.getText() + "', WHERE Complete = '" + complete.getText();
            dbc.update(stmt);
            ((Node) eb.getSource()).getScene().getWindow().hide();
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("One of the fields is empty");
            alert.showAndWait();
        }
    }


    public void cancelEditBooking(ActionEvent actionEvent) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

}


