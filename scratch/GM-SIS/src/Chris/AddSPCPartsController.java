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
public class AddSPCPartsController {
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


    @FXML
    public void addPart(ActionEvent event)
    {
        try {
            SQLiteConnection db = SQLiteConnection.getInstance();
            String stmt = "INSERT INTO SPCParts (acustomerID, aspcID, aspcPartName, aspcPartDescription, afirstName, alastName, aspcName, adeliveryDate, areturnDate, arepairCost, areturnStatus) VALUES ('" + acustomerID.getText() + "', '" + aspcID.getText() + "', '" + aspcPartName.getText() + "', '" + aspcPartDescription.getText() + "', '" + afirstName.getText() + "', '" + alastName.getText() + "', '" + aspcName.getText() + "', '" + adeliveryDate.getText() + "', '" + areturnDate.getText() + "', '" + arepairCost.getText() + "', '" + areturnStatus.getText() + "');";
            db.update(stmt);
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (NullPointerException e)
        {
            e.printStackTrace();
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
