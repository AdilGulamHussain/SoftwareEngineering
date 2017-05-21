package common.logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Christopher Chrysosotmou on 27/02/2017.
 */
public class editSPCPartsController implements Initializable{
    @FXML private TextField spcID
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

    private SQLiteConnection db = SQLiteConnection.getInstance();
    private SPCParts selected = SPCController.getSelected();
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        spcID.setText(selected.getSPCID());
        spcName.setText(selected.getSPCName());
        spcPartID.setText(selected.getspcPartID());
        spcPartName.setText(selected.getSPCPartName());
        firstName.setText(selected.getFirstName());
        lastName.setText(selected.getLastName());
        customerID.setText(selected.getCustomerID());
        spcPartDescription.setText(selected.getSPCPartDescription());
        deliveryDate.setText(selected.getDeliveryDate());
        returnDate.setText(selected.getReturnDate());
        repairCost.setText(selected.getRepairCost());
        returnStatus.setText(selected.getReturnStatus());
    }

    @FXML
    public void editPart(ActionEvent event)
    {
        try {

                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "UPDATE SPCParts SET spcName = '" + spcPartID.getText() + "', '" + customerID.getText() + "', '" + spcID.getText() + "', '" + firstName.getText() + "', '" + lastName.getText() + "', '" + spcName.getText() + "', '" + deliveryDate.getText() + "', '" + returnDate.getText() + "', '" + repairCost.getText() + "', '" + returnStatus.getText() + "', '" + spcPartName.getText() + "', '" + spcPartDescription.getText() + "' WHERE spcName = " + selected.getSPCName();
                db.update(stmt);
                ((Node) event.getSource()).getScene().getWindow().hide();
            }
        catch (NullPointerException e)
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
