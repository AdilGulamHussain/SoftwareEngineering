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
 * Created by Christopher Chrysostomou on 27/02/2017.
 */
public class addSPCController {

    @FXML private TextField spcName;
    @FXML private TextField spcPostcode;
    @FXML private TextField spcAddress;
    @FXML private TextField spcPhone;
    @FXML private TextField spcEmail;
    @FXML private TextField spcID;

    private static boolean isAdmin;

    public static void setIsAdmin(boolean type)
    {
        isAdmin = type;
    }
    @FXML
    public void addSPC(ActionEvent event)
    {
        try {
            if (!isAdmin) {//need to test if admin
                SQLiteConnection db = SQLiteConnection.getInstance();
                String stmt = "INSERT INTO SPC (spcName, spcID spcPostcode, spcAddress, spcPhone, spcEmail) VALUES ('" + spcName.getText() + "', '" + spcPostcode.getText() + "', '" + spcID.getText() + "','" + spcID.getText() + "', '" + spcAddress.getText() + "', '" + spcPhone.getText() + "', '" + spcEmail.getText() + ")";
                db.update(stmt);
                ((Node) (event.getSource())).getScene().getWindow().hide();
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
