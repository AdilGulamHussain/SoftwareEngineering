package Bogdan.src.vehicleRecords;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import common.logic.SQLiteConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import Bogdan.src.vehicleRecords.VehicleRecController;
import Bogdan.src.vehicleRecords.vehicleRec;
import javafx.scene.text.Text;

public class EditVehicleWarranty implements Initializable {
    ResultSet rs = null;
    @FXML
    private Text RegW;
    @FXML
    private TextField CompanyW;
    @FXML
    private TextField AddressW;
    @FXML
    private TextField ExpiryW;
    @FXML

    private ArrayList<VehicleWarranty> Warranty = new ArrayList<>();

    private SQLiteConnection db = SQLiteConnection.getInstance();

    public EditVehicleWarranty() {
    }

    public void initialize(URL location, ResourceBundle resourceBundle) {


            try {
                ResultSet rs = db.query("SELECT VehicleRegistration, Warranty, WarrantyCompany, WarrantyAddress, WarrantyExpiry FROM Warranty WHERE VehicleRegistration='" + VehicleRecController.getSelected().getvNumber() + "'");

                try {
                    while (rs.next())
                    {
                        SQLiteConnection db = SQLiteConnection.getInstance();
                        String company = rs.getString("WarrantyCompany");
                        String expiry = rs.getString("WarrantyExpiry");
                        String address = rs.getString("WarrantyAddress");
                        if(company == null){company = "No Company"; expiry = "NULL";}

                        SetValues(VehicleRecController.getSelected().getvNumber(),company,expiry,address);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            catch (NullPointerException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Please select a vehicle before attempting to edit warranty.");
                alert.showAndWait();
            }


        }

    public void SetValues(String number, String company, String expiry, String address)
    {
        RegW.setText(number);
        CompanyW.setText(company);
        ExpiryW.setText(expiry);
        AddressW.setText(address);
    }


    @FXML
    public void edit(ActionEvent event) throws Exception{

        try {
            SQLiteConnection db = SQLiteConnection.getInstance();
            ResultSet rs = db.query("SELECT VehicleRegistration, Warranty, WarrantyCompany, WarrantyAddress, WarrantyExpiry FROM Warranty WHERE VehicleRegistration='" + VehicleRecController.getSelected().getvNumber() + "'");

            if (rs.next()) {
                String alert1 = "UPDATE Warranty SET VehicleRegistration = '" + VehicleRecController.getSelected().getvNumber() + "\', Warranty = \'" + "1" + "\', WarrantyCompany = \'" + this.CompanyW.getText() + "\', WarrantyAddress = \'" + this.AddressW.getText() + "\', WarrantyExpiry = \'" + this.ExpiryW.getText() + "\' WHERE VehicleRegistration = \'" + this.RegW.getText() + "\'";
                db.update(alert1);
                ((Node) event.getSource()).getScene().getWindow().hide();
            }
            else{
                String stmt2 = "INSERT INTO Warranty (VehicleRegistration, Warranty, WarrantyCompany, WarrantyAddress, WarrantyExpiry) VALUES ('" + VehicleRecController.getSelected().getvNumber() + "', '" + "1" + "', '" + this.CompanyW.getText() + "', '" + this.AddressW.getText() + "', '" + this.ExpiryW.getText() + "')";
                db.update(stmt2);
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        } catch (NullPointerException var4) {

        }

    }

    @FXML
    public void delete(ActionEvent event) {
        SQLiteConnection e = SQLiteConnection.getInstance();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Warranty");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete " + this.RegW.getText() + " warranty?");
        Optional<ButtonType> response = alert.showAndWait();
        if (response.get() == ButtonType.OK) {
            String stmt = "DELETE FROM Warranty WHERE VehicleRegistration = \'" + this.RegW.getText() + "\'";
            db.update(stmt);
        }
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
