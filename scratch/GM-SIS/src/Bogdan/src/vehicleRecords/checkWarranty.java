package Bogdan.src.vehicleRecords;

import common.logic.SQLiteConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Bogdan on 15/03/2017.
 */

public class checkWarranty implements Initializable {

    private ArrayList<VehicleWarranty> Warranty = new ArrayList<>();

    private SQLiteConnection db = SQLiteConnection.getInstance();
    ResultSet rs = null;

    @FXML private Text numberText;
    @FXML private Text companyText;
    @FXML private Text expiryText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ResultSet rs = db.query("SELECT VehicleRegistration, Warranty, WarrantyCompany, WarrantyAddress, WarrantyExpiry FROM Warranty WHERE VehicleRegistration='" + VehicleRecController.getSelected().getvNumber() + "'");

        try {
            while (rs.next())
            {
                SQLiteConnection db = SQLiteConnection.getInstance();
                String company = rs.getString("WarrantyCompany");
                String expiry = rs.getString("WarrantyExpiry");
                if(company == null){company = "No Company"; expiry = "NULL";}

                SetValues(VehicleRecController.getSelected().getvNumber(),company,expiry);
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
            alert.setContentText("Please select a vehicle before attempting to check warranty.");
            alert.showAndWait();
        }


    }

    public void SetValues(String number, String company, String expiry)
    {
        numberText.setText(number);
        companyText.setText(company);
        expiryText.setText(expiry);
    }

}
