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
public class EditSPCController implements Initializable{

    @FXML private TextField spcName;
    @FXML private TextField spcPostcode;
    @FXML private TextField spcAddress;
    @FXML private TextField spcPhone;
    @FXML private TextField spcEmail;
    @FXML private TextField spcID;
    @FXML private RadioButton b;
    @FXML private RadioButton p;
    private static boolean isAdmin;

    public static void setIsAdmin(boolean type)
    {
        isAdmin = type;
    }
    private SQLiteConnection db = SQLiteConnection.getInstance();
    private SPC selected = SPCController.getSelected();
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        spcName.setText(selected.getSpcName());
        spcPostcode.setText(selected.getSpcPostcode());
        spcAddress.setText(selected.getSpcAddress());
        spcPhone.setText(selected.getSpcPhone());
        spcEmail.setText(selected.getSpcEmail());
        spcID.setText(selected.getSpcID().toString());
    }

    @FXML
    public void editSPC(ActionEvent event)
    {
        try {
            if (!isAdmin) {
                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "UPDATE SPC SET spcName = '" + spcName.getText() + "', " +
                        "spcPostcode = '" + spcPostcode.getText() + "', " +
                        "spcAddress = '" + spcAddress.getText() + "', " +
                        "spcPhone = '" + spcPhone.getText() +
                        "', spcEmail = '" + spcEmail.getText() +
                        "' WHERE spcID = " + selected.getSpcID() +
                        ";";
                db.update(stmt);
                ((Node) event.getSource()).getScene().getWindow().hide();
            }else {
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
