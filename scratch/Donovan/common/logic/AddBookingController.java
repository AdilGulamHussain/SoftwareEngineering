package common.logic;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookingController{

    public TextField customerID;
    public TextField manufacturer;
    public Button cancel;

    public void cancelNewBooking() {
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        }
    }
