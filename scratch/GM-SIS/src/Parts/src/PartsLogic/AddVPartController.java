package Parts.src.PartsLogic;

import Donovan.common.logic.Booking;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import common.logic.SQLiteConnection;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by MohammedKhan on 16/03/2017.
 */
public class AddVPartController implements Initializable {

    @FXML
    private TextField partIDFieldV;

    @FXML
    private TextField vehicleRegFieldV;

    @FXML
    private TextField bookingFieldV;

    @FXML
    private TextField installationFieldV;

    @FXML
    private TextField warrantyFieldV;

    @FXML
    private Button confirmBtnV;

    @FXML
    private Button closeBtnV;

    boolean flag = false;

    public void initialize(URL location, ResourceBundle resourceBundle) {


    }

    @FXML
    public void confirm2(ActionEvent event) {
        try {
            SQLiteConnection db = SQLiteConnection.getInstance();
            db.update("INSERT INTO Booking (BookingID, VehicleRegistration, Date, Part) VALUES ('" + bookingFieldV.getText() + "', '" + vehicleRegFieldV.getText() + "', '" + installationFieldV.getText() + "', '" + "Yes" + "');");
            String sql2 = "SELECT Parts.cost FROM Parts, InstalledParts WHERE Parts.partID =" + partIDFieldV;
            String sql1 = "SELECT partID  FROM Parts";
            ResultSet rsb = db.query(sql1);
            try {
                while (rsb.next()) {
                    if (rsb.getInt("partID") == Integer.parseInt(partIDFieldV.getText())) {
                        String stmt = "INSERT INTO InstalledParts (partID, vehicleRegistrationNumber, bookingID, installationDate, warrantyDate) VALUES ('" + partIDFieldV.getText() + "', '" + vehicleRegFieldV.getText() + "', '" + bookingFieldV.getText() + "', '" + installationFieldV.getText() + "', '" + warrantyFieldV.getText() + "');";
                        db.update(stmt);
                        flag = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (flag == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("PartID does not exist");
                alert.showAndWait();
            }

            db.update("UPDATE Parts SET stockLevel = stockLevel - 1 WHERE PartID =" + partIDFieldV.getText());
            try {
                Integer.parseInt(partIDFieldV.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("" + e);
                alert.showAndWait();
            }
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("One of the fields is empty");
            alert.showAndWait();
        }
    }


    @FXML
    public void cancel2(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel part add");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> response = alert.showAndWait();
        if (response.get() == ButtonType.OK) {
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

}


