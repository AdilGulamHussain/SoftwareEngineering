package common.logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;

import java.util.Optional;

/**
 * Created by Christopher Chrysostomou on 27/02/2017.
 */
public class AddSPCPartsController {
    @FXML private TextField spcID;
    @FXML private TextField spcPartID;
    @FXML private TextField spcName;
    @FXML private TextField spcPartName;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField customerID;
    @FXML private TextField spcPartDescription;
    @FXML private TextField deliveryDate;
    @FXML private TextField returnDate;
    @FXML private TextField repairCost;
    @FXML private TextField returnStatus;


    @FXML
    public void addPart(ActionEvent event)
    {
        try {
                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "INSERT INTO SPCParts (spcPartID, spcID, spcName spcRecordsRepairID, spcPartName, spcPartDescription, firstName, lastName, customerID, deliveryDate, returnDate, returnStatus, repairCost) VALUES ('" + spcPartID.getText() + "', '" + customerID.getText() + "', '" + spcID.getText() + "', '" + firstName.getText() + "', '" + lastName.getText() + "', '" + spcName.getText() + "', '" + deliveryDate.getText() + "', '" + returnDate.getText() + "', '" + repairCost.getText() + "', '" + returnStatus.getText() + "', '" + spcRecordsRepairID.getText() + "', '" + spcPartName.getText() + "', '" + spcPartDescription.getText() + ")";
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
