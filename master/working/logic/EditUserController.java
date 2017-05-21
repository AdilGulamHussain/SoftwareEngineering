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
public class editUserController implements Initializable{
    @FXML private TextField userID;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField adminStatus;


    private SQLiteConnection db = SQLiteConnection.getInstance();
    private User selected = UserController.getSelected();
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        userID.setText(selected.getuserID());
        username.setText(selected.getUsername());
        password.setText(selected.getPassword());
        adminStatus.setText(selected.getadminStatus());

    }

    @FXML
    public void editUser(ActionEvent event)
    {
        try {if(!isAdmin){

                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "UPDATE Users SET userID = '" + userID.getText() + "', '" + username.getText() + "', '" + password.getText() + "', '" + adminStatus.getText() + "' WHERE userID = " + selected.getuserID();
                db.update(stmt);
                ((Node) event.getSource()).getScene().getWindow().hide();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Must be admin");
                alert.showAndWait();
            }
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
