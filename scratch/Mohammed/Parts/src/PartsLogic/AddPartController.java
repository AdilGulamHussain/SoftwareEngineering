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
public class AddPartController {
    @FXML
    private TextField partIDField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField descField;

    @FXML
    private TextField stockField;

    @FXML
    private TextField costField;

    @FXML
    private Button submitBtn;

    @FXML
    private Button closeBtn;
    @FXML
    public void confirm(ActionEvent event){
        try {
            SQLiteConnection db = SQLiteConnection.getInstance();
            String stmt = "INSERT INTO Parts (partID, name, description, stockLevel, cost) VALUES ('" + Integer.parseInt(partIDField.getText()) + "', '" + nameField.getText() + "', '" + descField.getText() + "', '" + stockField.getText() + "', '" + costField.getText() +"');";
            db.update(stmt);
            ((Node) (event.getSource())).getScene().getWindow().hide();


        } catch (NullPointerException e)
        {
            System.err.println(e);
        }
    }
    @FXML
    public void cancel(ActionEvent event)
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