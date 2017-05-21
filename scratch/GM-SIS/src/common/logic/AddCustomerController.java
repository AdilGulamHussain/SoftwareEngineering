package common.logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.SQLException;
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
        if(validation()){
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
        }
    }

    boolean validation() {

        boolean counter = true;

        if (firstName.getText() == null) {
            JOptionPane.showMessageDialog(null, "ENTER a First Name");
            counter = false;
            return counter;
        }
        if (surname.getText() == null) {
            JOptionPane.showMessageDialog(null, "Enter a Surname");
            counter = false;
            return counter;
        }
        if (address == null) {
            JOptionPane.showMessageDialog(null, "Enter an address");
            counter = false;
            return false;
        }
        if (phone == null) {
            JOptionPane.showMessageDialog(null, "Enter an phone number");
            counter = false;
            return counter;
        }
        if (email == null) {
            JOptionPane.showMessageDialog(null, "Enter an email");
            counter = false;
            return false;
        }
        if (postcode == null) {
            JOptionPane.showMessageDialog(null, "add a postcode");
            counter = false;
            return counter;
        }
        if(phone==null)
        {
            JOptionPane.showMessageDialog(null, "please enter a phone number");
            counter = false;
            return counter;
            // int n = Integer.parseInt(phone.getText());
        }
        if (!firstName.getText().matches("^[a-zA-Z\\s]*$"))
        {
            JOptionPane.showMessageDialog(null, "You entered a number in first name");
            counter = false;
            return counter;
        }
        if (!surname.getText().matches("^[a-zA-Z\\s]*$"))
        {
            JOptionPane.showMessageDialog(null, "You entered a number in surname name");
            counter = false;
            return counter;
        }
        if (!phone.getText().matches("^[0-9]{11}$"))
        {
            JOptionPane.showMessageDialog(null, "Please enter a 11 digit number");
            counter = false;
            return counter;
        }
        if (!email.getText().matches("^(?=.*?\\b@\\b).*$"))
        {
            JOptionPane.showMessageDialog(null, "Please write a correct email address");
            counter = false;
            return counter;
        }
        return counter;
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
