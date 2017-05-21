package PartsLogic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;

/**
 * Created by MohammedKhan on 16/03/2017.
 */
public class OrderPartController {
    @FXML
    private TextField orderPartID;

    @FXML
    private TextField expDel;

    @FXML
    private TextField quantity;

    @FXML
    private TextField orderID;

    @FXML
    private Button orderBtn;

    @FXML
    private Button orderCloseBtn;

    @FXML
    public void confirmO(ActionEvent event){
        try {
            SQLiteConnection db = SQLiteConnection.getInstance();
            String stmt = "INSERT INTO PartsOrder (orderID, partID, expectedDate, quantity) VALUES ('" + orderID.getText() + "', '" + orderPartID.getText() + "', '" + expDel.getText() + "', '" + quantity.getText() +"');";
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
    public void cancelO(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel order");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK)
        {
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }
}

