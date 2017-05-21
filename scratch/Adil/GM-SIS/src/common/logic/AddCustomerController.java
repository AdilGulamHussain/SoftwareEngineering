package common.logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;

import java.util.Optional;

/**
 * Created by adilh on 19/02/2017.
 */
public class AddCustomerController {

    @FXML private TextField firstName;
    @FXML private TextField surname;
    @FXML private TextField address;
    @FXML private TextField postcode;
    @FXML private TextField phone;
    @FXML private TextField email;
    private int selectedType = -1;
    @FXML
    public void add(ActionEvent event)
    {
        if((firstName.getText().length() != 0) && (surname.getText().length() != 0) && (address.getText().length() != 0) && (postcode.getText().length() != 0) && (phone.getText().length() != 0) && (firstName.getText().length() != 0) && (email.getText().length() != 0)){
            if (selectedType != -1) {
                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "INSERT INTO CustomerAccounts (firstName, Surname, Address, PostCode, Phone, Email, Business) VALUES ('" + firstName.getText() + "', '" + surname.getText() + "', '" + address.getText() + "', '" + postcode.getText() + "', '" + phone.getText() + "', '" + email.getText() +"'," + selectedType + ")";
                db.update(stmt);
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Please select type");
                alert.showAndWait();
            }
        } else
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

    @FXML
    public void selectBusiness()
    {
        selectedType = 1;
    }

    @FXML
    public void selectPrivate()
    {
        selectedType = 0;
    }
}
