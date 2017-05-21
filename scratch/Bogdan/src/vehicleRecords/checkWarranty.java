package vehicleRecords;

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

    private DBConnection db = DBConnection.getInstance();
    ResultSet rs = null;

    @FXML private Text numberText;
    @FXML private Text companyText;
    @FXML private Text expiryText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ResultSet rs = db.query("SELECT VehicleRegistration, Warranty, WarrantyCompany, WarrantyAddress, WarrantyExpiry FROM Warranty WHERE VehicleRegistration='" + VehicleRecController.getSelected().getvNumber() +"'");

        //SELECT column_name from information_schema.columns where table_name='suppliers';

        try {
            while (rs.next())
            {
                DBConnection db = DBConnection.getInstance();
                Warranty.add(new VehicleWarranty(rs));
                String company = rs.getString("WarrantyCompany");
                String expiry = rs.getString("WarrantyExpiry");

                SetValues(VehicleRecController.getSelected().getvNumber(),company,expiry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void SetValues(String number, String company, String expiry)
    {
        numberText.setText(number);
        companyText.setText(company);
        expiryText.setText(expiry);
    }

}
