package Donovan.common.logic;

import common.logic.SQLiteConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class EditBookingController implements Initializable {
    @FXML public Button cancel;
    @FXML public Button edit;
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

    private SQLiteConnection dbc = SQLiteConnection.getInstance();
    private Booking selected = RepairController.getSelected();

    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        FName.setText(selected.getFirstName());
        SName.setText(selected.getSurname());
        ID.setText(String.valueOf(selected.getBookingID()));
        vReg.setText(selected.getVehicleRegistration());
        bookingT.setText(selected.getBookingTtype());
        Date.setText(selected.getDate());
        Spc.setText(selected.getSPC());
        part.setText(selected.getPart());
        complete.setText(selected.getComplete());
        costs.setText(String.valueOf(selected.getCosts()));
    }
@FXML
    public void editBooking(ActionEvent eb) {
        try {
            SQLiteConnection dbc = SQLiteConnection.getInstance();
            String stmt = "UPDATE Booking SET FirstName = '" +FName.getText() + "',  " +
                    "Surname = '" +SName.getText() + "',  " +
                    "VehicleRegistration = '" +vReg.getText() + "',  " +
                    "BookingID = '" + ID.getText() + "', " +
                    "BookingType = '" + bookingT.getText() + "', " +
                    "Date = '" + Date.getText() + "', " +
                    "SPC = '" + Spc.getText() + "', " +
                    "Part = '" + part.getText() + "', " +
                    "Complete = '" + complete.getText() +"', " +
                    "Costs = '" + costs.getText() +
                    "'WHERE BookingID = " + selected.getBookingID() + ";";

            dbc.update(stmt);
            /* float oldcosts = 0;
            String stmt3 = "SELECT costs from Booking WHERE BookingID = " + selected.getBookingID();
            ResultSet rs2 = dbc.query(stmt3);
            try {
                rs2.next();
                oldcosts = rs2.getFloat(String.valueOf(costs));
                //old value 50
            } catch (SQLException e) {
                e.printStackTrace();
            }



           /* float newcosts = Float.parseFloat(costs.getText());//new entered value 170
            newcosts = newcosts - oldcosts;//170-50 = 120

           // float totalcosts = Float.parseFloat("SELECT Costs FROM Booking WHERE BookingID = " + selected.getBookingID());
            String stmt1 = "UPDATE Bills SET TotalCost = '" + (costs = costs + newcosts) + "' WHERE BookingID = " + selected.getBookingID();
            dbc.update(stmt1);
            ((Node) eb.getSource()).getScene().getWindow().hide();*/
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


