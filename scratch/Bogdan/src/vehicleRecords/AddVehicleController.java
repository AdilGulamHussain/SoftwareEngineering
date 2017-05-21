package vehicleRecords;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import vehicleRecords.DBConnection;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddVehicleController implements Initializable {

    private ArrayList<AddVehicle> VehicleTemplate = new ArrayList<>();

    @FXML private TableView<AddVehicle> vehicleTemplateTable;
    @FXML private TableColumn<AddVehicle, String> Make;
    @FXML private TableColumn<AddVehicle, String> Model;
    @FXML private TableColumn<AddVehicle, String> Engine;
    @FXML private TableColumn<AddVehicle, String> Fuel;

    private DBConnection db = DBConnection.getInstance();
    ResultSet rs = null;

    private static AddVehicle selected;

    @FXML private TextField NumberIn;
    @FXML private TextField CustomerIn;
    @FXML private TextField ModelIn;
    @FXML private TextField MakeIn;
    @FXML private TextField EngineIn;
    @FXML private TextField FuelIn;
    @FXML private TextField ColourIn;
    @FXML private TextField MOTIn;
    @FXML private TextField MileIn;
    @FXML

    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        refresh();
        String sql = "SELECT *  FROM VehicleTemplate";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                VehicleTemplate.add(new AddVehicle(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        vehicleTemplateTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected = vehicleTemplateTable.getSelectionModel().getSelectedItem();
                }
        );

        sql = "SELECT *  FROM VehicleTemplate";
        rs = db.query(sql);
        try {
            while (rs.next())
            {
                VehicleTemplate.add(new AddVehicle(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void refresh()
    {
        setValueFactories();
        ArrayList<AddVehicle> tableValues = new ArrayList<>();
        String sql = "SELECT *  FROM VehicleTemplate";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                tableValues.add(new AddVehicle(rs));
            }
            vehicleTemplateTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static AddVehicle getSelected() {
        return selected;
    }

    private void setValueFactories()
    {
        Make.setCellValueFactory(new PropertyValueFactory<>("Make"));
        Model.setCellValueFactory(new PropertyValueFactory<>("Model"));
        Engine.setCellValueFactory(new PropertyValueFactory<>("Engine"));
        Fuel.setCellValueFactory(new PropertyValueFactory<>("Fuel"));
    }



    public void add(ActionEvent event)
    {
        try {
            DBConnection db = DBConnection.getInstance();
            String stmt = "INSERT INTO Vehicles (VehicleRegistrationNumber, CustomerID, Model, Make, EngineSize, FuelType, Colour, MOTRenewalDate, LastServiceDate, CurrentMileage) VALUES ('" + NumberIn.getText() + "', '" + CustomerIn.getText() + "', '" + ModelIn.getText() + "', '" + MakeIn.getText() + "', '" + EngineIn.getText() + "', '" + FuelIn.getText() + "', '" + ColourIn.getText() + "', '" + MOTIn.getText() + "', '" + "01/01/1001" + "', '" + MileIn.getText() + "')";
            db.update(stmt);
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("One of the fields is empty");
            alert.showAndWait();
        }
    }
}
