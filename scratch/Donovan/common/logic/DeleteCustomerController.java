package common.logic;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class DeleteCustomerController {
    public Button canceld;
    public Button confirm;


    public void confirmdelete() {
    }

    public void canceldelete() {
        Stage stage = (Stage) canceld.getScene().getWindow();
        stage.close();
    }
}
