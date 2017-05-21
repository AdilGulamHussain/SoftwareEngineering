package Parts.src.PartsLogic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import common.logic.SQLiteConnection;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by MohammedKhan on 16/03/2017.
 */
public class EditPartController implements Initializable {

    @FXML
    private TextField nameField1;
    @FXML private TextField descField1;
    @FXML private TextField stockField1;
    @FXML private TextField costField1;
    private SQLiteConnection db = SQLiteConnection.getInstance();
    public StockParts selected = MainController.getSelected();

    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        nameField1.setText(selected.getName());
        descField1.setText(selected.getDescription());
        stockField1.setText(selected.getStockLevel().toString());
        costField1.setText(selected.getCost().toString());
    }

    @FXML
    public void confirm1 (ActionEvent event) {
        try {
            SQLiteConnection db = SQLiteConnection.getInstance();
            String stmt = "UPDATE Parts SET name = '" + nameField1.getText() + "', description = '" + descField1.getText() + "', stockLevel = '" + stockField1.getText() + "', cost = '" + costField1.getText() + "' WHERE partID = " + selected.getpartID();
            try {
                Integer.parseInt(stockField1.getText());
                Double.parseDouble(costField1.getText());
            }
            catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText(""+e);
                alert.showAndWait();
            }
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
    public void cancel1(ActionEvent event)
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
