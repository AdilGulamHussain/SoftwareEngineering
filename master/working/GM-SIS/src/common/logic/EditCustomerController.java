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
 * Created by adilh on 19/02/2017.
 */
public class EditCustomerController implements Initializable{

    @FXML private TextField firstName;
    @FXML private TextField surname;
    @FXML private TextField address;
    @FXML private TextField postcode;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private RadioButton b;
    @FXML private RadioButton p;
    private int selectedType = -1;
    private SQLiteConnection db = SQLiteConnection.getInstance();
    private Customer selected = CustomerController.getSelected();
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        firstName.setText(selected.getfName());
        surname.setText(selected.getsName());
        address.setText(selected.getAdd());
        postcode.setText(selected.getPostcode());
        phone.setText(selected.getPhone());
        email.setText(selected.getEmail());
        if (selected.getType().equals("Business"))
        {
            b.setSelected(true);
            selectedType = 1;
        }
        else
        {
            p.setSelected(true);
            selectedType = 0;
        }
    }

    @FXML
    public void edit(ActionEvent event)
    {
        try {
            if (selectedType != -1) {
                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "UPDATE CustomerAccounts SET firstName = '" + firstName.getText() + "', Surname = '" + surname.getText() + "', Address = '" + address.getText() + "', PostCode = '" + postcode.getText() + "', Phone = '" + phone.getText() +"', Business = " + selectedType + ", Email ='" + email.getText() +"' WHERE ID = " + selected.getId();
                db.update(stmt);
                ((Node) event.getSource()).getScene().getWindow().hide();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Please select type");
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
