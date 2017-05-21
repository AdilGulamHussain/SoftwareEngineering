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
public class AddUserController {
    @FXML private TextField userID;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField adminStatus;
boolean isAdmin = true;

    @FXML
    public void addUser(ActionEvent event)
    {
        try {
                SQLiteConnection db = SQLiteConnection.getInstance();
            String stmt = "INSERT INTO login (username, password, adminStatus) VALUES ('" + username.getText() + "','" + password.getText() + "', '" + adminStatus.getText() +"');";
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
