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
public class EditOrderController implements Initializable {

    @FXML
    private TextField orderPartID1;
    @FXML
    private TextField expDel1;
    @FXML
    private TextField quantity1;
    @FXML
    private Button orderBtn1;
    @FXML
    private Button orderCloseBtn1;
    private SQLiteConnection db = SQLiteConnection.getInstance();
    public OrderParts selected2 = MainController.getSelected2();

    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        orderPartID1.setText(selected2.getPartID());
        expDel1.setText(selected2.getExpectedDate());
        quantity1.setText(selected2.getQuantity());
    }
    @FXML
    public void confirm2 (ActionEvent event) {
        try {
            SQLiteConnection db = SQLiteConnection.getInstance();
            String stmt = "UPDATE PartsOrder SET partID  = '" + orderPartID1.getText() + "', expectedDate = '" + expDel1.getText() + "', quantity = '" + quantity1.getText() + "' WHERE orderID = " + selected2.getOrderID();
            db.update(stmt);
            ((Node) event.getSource()).getScene().getWindow().hide();
        }
        catch (NullPointerException e) {
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
