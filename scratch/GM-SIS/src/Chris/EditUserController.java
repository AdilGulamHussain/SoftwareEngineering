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
public class EditUserController implements Initializable{
    @FXML private TextField userID;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField adminStatus;
boolean isAdmin = true;

    private SQLiteConnection db = SQLiteConnection.getInstance();
    private User selected = UserController.getSelected();
    public void initialize(URL location, ResourceBundle resourceBundle)
    {

        //userID.setText(selected.getUserID().toString());
        username.setText((selected.getUsername().toString()));
        password.setText(selected.getPassword());
        adminStatus.setText(selected.getAdminStatus());

    }

    @FXML
    public void editUser(ActionEvent event)
    {
        try {

                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "UPDATE login SET username = '" + username.getText() + "', " +
                        "password = '" + password.getText() + "', " +
                        "adminStatus = '" + adminStatus.getText() + "' WHERE userID = " + selected.getUserID() +
                        ";";
                db.update(stmt);
                ((Node) event.getSource()).getScene().getWindow().hide();


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
