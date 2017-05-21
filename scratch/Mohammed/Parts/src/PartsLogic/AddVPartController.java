package PartsLogic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
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

    public void initialize(URL location, ResourceBundle resourceBundle){

    }
    @FXML
    public void confirm2(ActionEvent event) {
        try {
            SQLiteConnection db = SQLiteConnection.getInstance();
            String stmt = "INSERT INTO InstalledParts (partID, vehicleRegistrationNumber, bookingID, installationDate, warrantyDate) VALUES ('" + partIDFieldV.getText() + "', '" + vehicleRegFieldV.getText() + "', '" + bookingFieldV.getText() + "', '" + installationFieldV.getText() + "', '" + warrantyFieldV.getText() + "');";
            db.update(stmt);
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
    public void cancel2(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel part add");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK)
        {
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }
}

