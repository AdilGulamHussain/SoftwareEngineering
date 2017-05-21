package Parts.src.PartsLogic;

import common.logic.SQLiteConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.util.Optional;

import static javax.swing.JOptionPane.*;

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
    public void confirm(ActionEvent event) {
        try {
            if (validation()) {
                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "INSERT INTO Parts (name, description, stockLevel, cost) VALUES ('" + nameField.getText() + "', '" + descField.getText() + "', '" + stockField.getText() + "', '" + costField.getText() + "');";
                db.update(stmt);
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("One of the fields is empty");
            alert.showAndWait();
        }
    }

    private boolean validation() {
        boolean isValid = true;

        if (nameField == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Name field cannot be empty ");
            alert.showAndWait();
            isValid = false;
            return isValid;
        }
        if (!nameField.getText().matches("^[a-zA-Z]*$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Name field cannot include a number ");
            alert.showAndWait();
            isValid = false;
            return isValid;
        }
            try {
                Integer.parseInt(stockField.getText());
                Double.parseDouble(costField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a number in the stock/cost field ");
                alert.showAndWait();
                isValid = false;
                return isValid;
            }

        return isValid;
    }

    @FXML
    public void cancel(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel part add");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> response = alert.showAndWait();
        if (response.get() == ButtonType.OK) {
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

}
