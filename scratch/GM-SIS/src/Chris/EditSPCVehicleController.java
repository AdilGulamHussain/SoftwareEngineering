package Chris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import common.logic.SQLiteConnection;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Christopher Chrysosotmou on 27/02/2017.
 */
public class EditSPCVehicleController implements Initializable{
    @FXML private TextField bspcID;
    @FXML private TextField bvehicleRegistrationNumber;
    @FXML private TextField bspcName;
    @FXML private TextField bmodel;
    @FXML private TextField bfirstName;
    @FXML private TextField blastName;
    @FXML private TextField bcustomerID;
    @FXML private TextField bmake;
    @FXML private TextField bdeliveryDate;
    @FXML private TextField breturnDate;
    @FXML private TextField brepairCost;
    @FXML private TextField breturnStatus;

    private SQLiteConnection db = SQLiteConnection.getInstance();
    private SPCVehicle selected = SPCController.getSelectedVehicle();
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        bspcID.setText(selected.getbspcID().toString());
        bspcName.setText(selected.getbspcName());
        bmodel.setText(selected.getbmodel());
        bmake.setText(selected.getBmake());
        bfirstName.setText(selected.getbfirstName());
        blastName.setText(selected.getblastName());
        bcustomerID.setText(selected.getbcustomerID().toString());
        bvehicleRegistrationNumber.setText(selected.getbvehicleRegistrationNumber());
        bdeliveryDate.setText(selected.getbdeliveryDate());
        breturnDate.setText(selected.getbreturnDate());
        brepairCost.setText(selected.getbrepairCost().toString());
        breturnStatus.setText(selected.getbreturnStatus());
    }

    @FXML
    public void editVehicle(ActionEvent event)
    {
        try {

            SQLiteConnection db = SQLiteConnection.getInstance();
            String stmt = "UPDATE SPCVehicle SET bvehicleRegistrationNumber = '" + bvehicleRegistrationNumber.getText() + "', " +
                    "bmodel = '" + bmodel.getText() + "', " +
                    "bcustomerID = '" + bcustomerID.getText() + "', " +
                    "bdeliveryDate = '" + bdeliveryDate.getText() + "', " +
                    "bspcName = '" + bspcName.getText() + "', " +
                    "bmake = '" + bmake.getText() + "'," +
                    "breturnDate = '" + breturnDate.getText() + "', " +
                    "brepairCost = '" + brepairCost.getText() + "', " +
                    "breturnStatus = '" + breturnStatus.getText() + "', " +
                    "bspcID = '" + bspcID.getText() + "', " +
                    "bfirstName = '" + bfirstName.getText() + "', blastName = '" + blastName.getText() +
                    "' WHERE bvehicleRegistrationNumber = " + selected.getbvehicleRegistrationNumber() +
                    ";";
            db.update(stmt);
            ((Node) event.getSource()).getScene().getWindow().hide();

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
    public void cancelVehicle(ActionEvent event)
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
