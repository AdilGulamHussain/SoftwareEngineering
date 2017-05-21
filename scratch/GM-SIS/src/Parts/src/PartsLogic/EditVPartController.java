package Parts.src.PartsLogic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import common.logic.SQLiteConnection;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by MohammedKhan on 16/03/2017.
 */
public class EditVPartController implements Initializable {
    @FXML
    private TextField partIDFieldV1;

    @FXML
    private TextField vehicleField;


    @FXML
    private TextField installationField;

    @FXML
    private TextField warrantyField;

    @FXML
    private Button confirmBtnV1;

    @FXML
    private Button closeBtnV1;
    private SQLiteConnection db = SQLiteConnection.getInstance();
    boolean flag = false;

    public InstalledParts selected1 = MainController.getSelected1();

    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        partIDFieldV1.setText(selected1.getPartID().toString());
        vehicleField.setText(selected1.getVehicleRegistrationNumber());
        installationField.setText(selected1.getInstallationDate());
        warrantyField.setText(selected1.getWarrantyDate());


    }

    @FXML
    public void confirmV1 (ActionEvent event) {
        try {
            SQLiteConnection db = SQLiteConnection.getInstance();

            db.update("UPDATE Parts SET stockLevel = stockLevel - 1 WHERE PartID =" + partIDFieldV1.getText());
            db.update("UPDATE Parts SET stockLevel = stockLevel + 1 WHERE PartID =" + selected1.getPartID());
            String sql1 = "SELECT partID  FROM Parts";
            ResultSet rsb = db.query(sql1);
            try {
                while (rsb.next()) {
                    if (rsb.getInt("partID") == Integer.parseInt(partIDFieldV1.getText())) {
                        String stmt = "UPDATE InstalledParts SET partID = '" + partIDFieldV1.getText() + "', vehicleRegistrationNumber = '" + vehicleField.getText() + "', installationDate = '" + installationField.getText() + "', warrantyDate = '" + warrantyField.getText() + "' WHERE bookingID = " + selected1.getBookingID();                        db.update(stmt);
                        db.update(stmt);
                        flag = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (flag == false){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("PartID does not exist");
                alert.showAndWait();
            }
            try {
                Integer.parseInt(partIDFieldV1.getText());
            }
            catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText(""+e);
                alert.showAndWait();
            }

            ((Node) event.getSource()).getScene().getWindow().hide();
        }
        catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("One of the fields is empty");
            alert.showAndWait();
        }
    }


    @FXML
    public void cancelV1(ActionEvent event)
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
