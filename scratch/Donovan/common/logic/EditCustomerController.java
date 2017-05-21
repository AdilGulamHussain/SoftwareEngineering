package common.logic;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditCustomerController {
    public TextField customerID;
    public TextField manufacturer;
    public TextField registration;
    public TextField date;
    public TextField mechanic;
    public TextField abc;
    public TextField bType;
    public TextField email11;
    public TextField aType;
    public Button edit;
    public Button cancelb;

    public void editBooking() {
    }

    public void cancelEditBooking() {
        Stage stage = (Stage) cancelb.getScene().getWindow();
        stage.close();
    }

}
