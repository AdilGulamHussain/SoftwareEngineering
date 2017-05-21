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
public class EditSPCPartsController implements Initializable{
    @FXML private TextField aspcID;
    @FXML private TextField aspcPartID;
    @FXML private TextField aspcName;
    @FXML private TextField aspcPartName;
    @FXML private TextField afirstName;
    @FXML private TextField alastName;
    @FXML private TextField acustomerID;
    @FXML private TextField aspcPartDescription;
    @FXML private TextField adeliveryDate;
    @FXML private TextField areturnDate;
    @FXML private TextField arepairCost;
    @FXML private TextField areturnStatus;

    private SQLiteConnection db = SQLiteConnection.getInstance();
    private SPCParts selected = SPCController.getSelectedPart();
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        aspcID.setText(selected.getAspcID().toString());
        aspcName.setText(selected.getAspcName());
        aspcPartID.setText(selected.getAspcPartID().toString());
        aspcPartName.setText(selected.getAspcPartName());
        afirstName.setText(selected.getAfirstName());
        alastName.setText(selected.getAlastName());
        acustomerID.setText(selected.getAcustomerID().toString());
        aspcPartDescription.setText(selected.getAspcPartDescription());
        adeliveryDate.setText(selected.getAdeliveryDate());
        areturnDate.setText(selected.getAreturnDate());
        arepairCost.setText(selected.getArepairCost().toString());
        areturnStatus.setText(selected.getAreturnStatus());
    }

    @FXML
    public void editPart(ActionEvent event)
    {
        try {

            SQLiteConnection db = SQLiteConnection.getInstance();
            String stmt = "UPDATE SPCParts SET acustomerID = '" + acustomerID.getText() + "', " +
                    "aspcID = '" + aspcID.getText() + "', " +
                    "afirstName = '" + afirstName.getText() + "', " +
                    "alastName = '" + alastName.getText() + "', " +
                    "aspcPartDescription = '" + aspcPartDescription.getText() + "', " +
                    "aspcPartName = '" + aspcPartName.getText() + "'," +
                    "areturnDate = '" + areturnDate.getText() + "'," +
                    "aspcName = '" + aspcName.getText() + "', " +
                    "arepairCost = '" + arepairCost.getText() + "', " +
                    "areturnStatus = '" + areturnStatus.getText() + "', adeliveryDate = '" + adeliveryDate.getText() +
                    "' WHERE aspcPartID = " + selected.getAspcPartID() +
                    ";";
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
