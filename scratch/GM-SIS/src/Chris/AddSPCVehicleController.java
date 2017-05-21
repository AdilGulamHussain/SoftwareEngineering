package Chris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import common.logic.SQLiteConnection;

import java.util.Optional;

/**
 * Created by Christopher Chrysostomou on 27/02/2017.
 */
public class AddSPCVehicleController {
    @FXML private TextField bspcID;
    @FXML private TextField bvehicleRegistrationNumber;
    @FXML private TextField bspcName;
    @FXML private TextField bmodel;
    @FXML private TextField bmake;
    @FXML private TextField bfirstName;
    @FXML private TextField blastName;
    @FXML private TextField bcustomerID;
    @FXML private TextField bdeliveryDate;
    @FXML private TextField breturnDate;
    @FXML private TextField brepairCost;
    @FXML private TextField breturnStatus;


    @FXML
    public void addVehicle(ActionEvent event)
    {
        try {
            SQLiteConnection db = SQLiteConnection.getInstance();
            String stmt = "INSERT INTO SPCVehicle (bvehicleRegistrationNumber, bcustomerID, bspcID, bfirstName, blastName, bspcName, bdeliveryDate, breturnDate, brepairCost, bmodel, bmake) VALUES ('" + bvehicleRegistrationNumber.getText() + "', '" + bcustomerID.getText() + "', '" + bspcID.getText() + "', '" + bfirstName.getText() + "', '" + blastName.getText() + "', '" + bspcName.getText() + "', '" + bdeliveryDate.getText() + "', '" + breturnDate.getText() + "', '" + brepairCost.getText() + "', '" + bmodel.getText() + "', '" + bmake.getText() + "');";
            db.update(stmt);
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("One of the fields is empty");
            alert.showAndWait();
        }
    }

    @FXML
    public void cancel(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GMSIS");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK)
        {
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

}
