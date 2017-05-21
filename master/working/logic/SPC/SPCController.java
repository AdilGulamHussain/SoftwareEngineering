package common.logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Christopher Chrysostomou on 27/02/2017.
 */
public class SPCController implements Initializable{

    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<SPC> spc = new ArrayList();
    private ArrayList<SPCParts> spcParts = new ArrayList<>();
    private ArrayList<SPCVehicle> spcVehicles = new ArrayList<>();

    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, Integer> id;
    @FXML private TableColumn<Customer, String> fName;
    @FXML private TableColumn<Customer, String> sName;
    @FXML private TableColumn<Customer, String> add;
    @FXML private TableColumn<Customer, String> postcode;
    @FXML private TableColumn<Customer, String> phone;
    @FXML private TableColumn<Customer, String> email;
    @FXML private TableColumn<Customer, String> type;

    @FXML private TableView<Vehicle> vehicleTable;
    @FXML private TableColumn<Vehicle, String> reg;
    @FXML private TableColumn<Vehicle, String> model;
    @FXML private TableColumn<Vehicle, String> make;
    @FXML private TableColumn<Vehicle, String> size;
    @FXML private TableColumn<Vehicle, String> fuel;
    @FXML private TableColumn<Vehicle, String> colour;
    @FXML private TableColumn<Vehicle, String> mot;
    @FXML private TableColumn<Vehicle, String> last;
    @FXML private TableColumn<Vehicle, Integer> mile;

    @FXML private TableView<Booking> bookingTable;
    @FXML private TableColumn<Booking, Integer> bookingID;
    @FXML private TableColumn<Booking, String> vehicle;
    @FXML private TableColumn<Booking, String> mechanic;
    @FXML private TableColumn<Booking, Integer> duration;
    @FXML private TableColumn<Booking, String> bookingType;
    @FXML private TableColumn<Booking, String> date;
    @FXML private TableColumn<Booking, Integer> spc;

    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, String> vehicleID;
    @FXML private TableColumn<Part, Integer> partBookingID;
    @FXML private TableColumn<Part, String> name;
    @FXML private TableColumn<Part, String> desc;

    @FXML private TableView<Bill> billTable;
    @FXML private TableColumn<Bill, Integer> billID;
    @FXML private TableColumn<Bill,Integer> billBookingID;
    @FXML private TableColumn<Bill,Float> price;
    @FXML private TableColumn<Bill,Float> paid;
    @FXML private TableColumn<Bill,Float> balance;


    @FXML private TableView<SPC> spcTable;
    @FXML private TableColumn<SPC, String> spcName;
    @FXML private TableColumn<SPC, String> spcPostcode;
    @FXML private TableColumn<SPC, String> spcAddress;
    @FXML private TableColumn<SPC, String> spcPhone;
    @FXML private TableColumn<SPC, String> spcEmail;
    @FXML private TableColumn<SPC, Integer> spcID;

    @FXML private TableView<SPCParts> spcPartsTable;
    @FXML private TableColumn<SPCParts, Integer> spcPartID;
    @FXML private TableColumn<SPCParts, Integer> spcPartDescription;
    @FXML private TableColumn<SPCParts, String> spcPartName;
    @FXML private TableColumn<SPCParts, String> firstName;
    @FXML private TableColumn<SPCParts, Integer> customerID;
    @FXML private TableColumn<SPCParts, Integer> spcID;
    @FXML private TableColumn<SPCParts, String> lastName;
    @FXML private TableColumn<SPCParts, String> deliveryDate;
    @FXML private TableColumn<SPCParts, Float> repairCost;
    @FXML private TableColumn<SPCParts, String> spcName;
    @FXML private TableColumn<SPCParts, String> returnDate;
    @FXML private TableColumn<SPCParts, String> returnStatus;


    @FXML private TableView<SPCVehicle> spcVehicleTable;
    @FXML private TableColumn<SPCVehicle, String> vehicleRegistrationNumber;
    @FXML private TableColumn<SPCVehicle, String> model;
    @FXML private TableColumn<SPCVehicle, String> make;
    @FXML private TableColumn<SPCVehicle, String> firstName;
    @FXML private TableColumn<SPCVehicle, Integer> customerID;
    @FXML private TableColumn<SPCVehicle, Integer> spcID;
    @FXML private TableColumn<SPCVehicle, String> lastName;
    @FXML private TableColumn<SPCVehicle, String> deliveryDate;
    @FXML private TableColumn<SPCVehicle, Float> repairCost;
    @FXML private TableColumn<SPCVehicle, String> spcName;
    @FXML private TableColumn<SPCVehicle, String> returnDate;
    @FXML private TableColumn<SPCVehicle, String> returnStatus;

    @FXML private TextField search_bar;


    private SQLiteConnection db = SQLiteConnection.getInstance();
    private static Spc selected;
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        fillTable();
        search_bar.textProperty().addListener((ob, oldVal, newVal) -> { search();});
        String sql = "SELECT *  FROM SPC";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                spc.add(new SPC(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        spcTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected = spcTable.getSelectionModel().getSelectedItem();
                    fillVehicleTable();
                    fillSPCPartsTable();
                    fillSPCVehicleTable();
                    fillPartTable();
                    fillBillsTable();
                }
        );

        sql = "SELECT *  FROM Vehicles";
        rs = db.query(sql);
        try {
            while (rs.next())
            {
                vehicles.add(new Vehicle(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void fillTable()
    {
        setValueFactories();
        ArrayList<SPC> tableValues = new ArrayList<>();
        String sql = "SELECT *  FROM SPC";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                tableValues.add(new SPC(rs));
            }
            spcTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void add()
    {
        try {
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/AddSPC.fxml"));
            newScreen.setTitle("New SPC");
            newScreen.setScene(new Scene(parent));
            newScreen.show();
            fillTable();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void edit()
    {
        try {
            selected = spcTable.getSelectionModel().getSelectedItem();
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/EditSPC.fxml"));
            newScreen.setTitle("Edit SPC");
            newScreen.setScene(new Scene(parent));
            newScreen.show();
            fillTable();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void delete()
    {
        SPC selected = spcTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GMSIS");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete " + selected.getspcName() + "?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK)
        {
            db.update("DELETE FROM SPC WHERE ID = " + selected.getSPCID());
            fillTable();
        }
    }

    public static SPC getSelected() {
        return selected;
    }
    @FXML
    /*public void selectRow()
    {
        selected = customerTable.getSelectionModel().getSelectedItem();
        fillVehicleTable();
        fillBookingTable();
        fillPartTable();
        fillBillsTable();
    }*/

    private void fillVehicleTable()
    {
        reg.setCellValueFactory(new PropertyValueFactory<>("reg"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        make.setCellValueFactory(new PropertyValueFactory<>("make"));
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
        fuel.setCellValueFactory(new PropertyValueFactory<>("fuel"));
        colour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        mot.setCellValueFactory(new PropertyValueFactory<>("mot"));
        last.setCellValueFactory(new PropertyValueFactory<>("last"));
        mile.setCellValueFactory(new PropertyValueFactory<>("mile"));
        ArrayList<Vehicle> tableValues = new ArrayList<>();
        String sql;
        try {
            sql = "SELECT *  FROM Vehicles WHERE CustomerID = " + selected.getId();

            ResultSet rs = db.query(sql);
            try {
                while (rs.next()) {
                    tableValues.add(new Vehicle(rs));
                }
                vehicleTable.setItems(FXCollections.observableArrayList(tableValues));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e)
        {

        }
    }

    private void fillBookingTable()
    {
        bookingID.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        vehicle.setCellValueFactory(new PropertyValueFactory<>("vehicleReg"));
        mechanic.setCellValueFactory(new PropertyValueFactory<>("mechanic"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        bookingType.setCellValueFactory(new PropertyValueFactory<>("bookingType"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        spc.setCellValueFactory(new PropertyValueFactory<>("spc"));
        ArrayList<Booking> tableValues = new ArrayList<>();
        try
        {

        String sql = "SELECT VehicleRegistrationNumber FROM Vehicles WHERE CustomerID = " + selected.getId();
        System.out.println(selected.getId());
        ResultSet rs = db.query(sql); 
        try {
            while (rs.next())
            {21457
                ResultSet rs1 = db.query("SELECT * FROM Booking WHERE VehicleRegistration = '"+ rs.getString("VehicleRegistrationNumber") +"'");
                while(rs1.next()) {
                    tableValues.add(new Booking(rs1));
                    //System.out.print(tableValues);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bookingTable.setItems(FXCollections.observableArrayList(tableValues));
    } catch (NullPointerException e)
        {

        }

    }

    private void fillPartTable()
    {
        vehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        partBookingID.setCellValueFactory(new PropertyValueFactory<>("booking"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        ArrayList<Part> tableValues = new ArrayList<>();
        try {
            String sql = "SELECT VehicleRegistrationNumber  FROM Vehicles WHERE CustomerID = " + selected.getId();
            ResultSet rs = db.query(sql);

            while (rs.next()) {
                ResultSet rs2 = db.query("SELECT * FROM InstalledParts WHERE VehicleID = '" + rs.getString("VehicleRegistrationNumber") + "'");
                while (rs2.next())
                {
                    tableValues.add(new Part(rs2));
                }
            }
            partTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e)
        {
        }

    }

    private void fillBillsTable()
    {
        billBookingID.setCellValueFactory(new PropertyValueFactory<>("booking"));
        billID.setCellValueFactory(new PropertyValueFactory<>("id"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        ArrayList<Bill> tableValues = new ArrayList<>();
        try {
            String sql = "SELECT VehicleRegistrationNumber FROM Vehicles WHERE CustomerID = " + selected.getId();
            ResultSet rs = db.query(sql);

            while (rs.next()) {
                ResultSet rs1 = db.query("SELECT BookingID FROM Booking WHERE VehicleRegistration = '" + rs.getString("VehicleRegistrationNumber") + "'");
                while(rs1.next())
                {
                    ResultSet rs2 = db.query("SELECT * FROM Bills WHERE BookingID = " + rs1.getString("BookingID"));
                    while (rs2.next()) {
                        tableValues.add(new Bill(rs2));
                    }
                }
            }
            billTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e)
        {

        }

    }



    private void fillSPCTable()
    {
        spcID.setCellValueFactory(new(PropertyValueFactory<>("spcID"));
        spcName.setCellValueFactory(new PropertyValueFactory<>("spcName"));
        spcPostcode.setCellValueFactory(new PropertyValueFactory<>("spcPostcode"));
        spcAddress.setCellValueFactory(new PropertyValueFactory<>("spcAddress"));
        spCPhone.setCellValueFactory(new PropertyValueFactory<>("spCPhone"));
        spcEmail.setCellValueFactory(new PropertyValueFactory<>("spcEmail"));
        ArrayList<Vehicle> tableValues = new ArrayList<>();
        String sql;
        try {
            sql = "SELECT *  FROM SPC WHERE Name = " + selected.getSPCID();

            ResultSet rs = db.query(sql);
            try {
                while (rs.next()) {
                    tableValues.add(new SPC(rs));
                }
                spcTable.setItems(FXCollections.observableArrayList(tableValues));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e)
        {

        }
    }



    private void fillSPCPartsTable()
    {
        spcPartID.setCellValueFactory(new PropertyValueFactory<>("spcPartID"));
        spcPartName.setCellValueFactory(new PropertyValueFactory<>("spcPartName"));
        spcPartDescription.setCellValueFactory(new PropertyValueFactory<>("spcPartDescription"));
        spcID.setCellValueFactory(new PropertyValueFactory<>("spcID"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        spcName.setCellValueFactory(new PropertyValueFactory<>("spcName"));
        deliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        repairCost.setCellValueFactory(new PropertyValueFactory<>("repairCost"));
        returnStatus.setCellValueFactory(new PropertyValueFactory<>("returnStatus"));
        ArrayList<SPCParts> tableValues = new ArrayList<>();
        String sql;
        try {
            sql = "SELECT *  FROM SPCParts WHERE spcPartID = " + selected.getSPCPartID();

            ResultSet rs = db.query(sql);
            try {
                while (rs.next()) {
                    tableValues.add(new SPCParts(rs));
                }
                SPCPartsTable.setItems(FXCollections.observableArrayList(tableValues));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e)
        {

        }
    }


    private void fillSPCVehicleTable()
    {
        spcVehicleID.setCellValueFactory(new PropertyValueFactory<>("spcVehicleID"));
        vehicleRegistrationNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistrationNumber"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        make.setCellValueFactory(new PropertyValueFactory<>("make"));
        spcID.setCellValueFactory(new PropertyValueFactory<>("spcID"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        spcName.setCellValueFactory(new PropertyValueFactory<>("spcName"));
        deliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        repairCost.setCellValueFactory(new PropertyValueFactory<>("repairCost"));
        returnStatus.setCellValueFactory(new PropertyValueFactory<>("returnStatus"));

        ArrayList<SPCVehicle> tableValues = new ArrayList<>();
        String sql;
        try {
            sql = "SELECT *  FROM SPCVehicle WHERE vehicleRegistrationNumber = " + selected.getvehicleRegistrationNumber();

            ResultSet rs = db.query(sql);
            try {
                while (rs.next()) {
                    tableValues.add(new SPCVehicle(rs));
                }
                SPCVehicleTable.setItems(FXCollections.observableArrayList(tableValues));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e)
        {

        }
    }



    /*public void keyClick(KeyEvent event)
    {
        if(event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.UP || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT)
        {
            selectRow();
        }
    }*/

    @FXML
    public void search()
    {

        try {
            spcVehicleTable.getItems().clear();
            setValueFactories();
            ArrayList<spcVehicles> searchedItems = new ArrayList<>();
            for (SPCVehicles spcVehicles : spcVehicles) {
                if ((spcVehicles.getFirstName().toLowerCase().contains(search_bar.getText().toLowerCase())) || (spcVehicles.getLastName().toLowerCase().contains(search_bar.getText().toLowerCase())) || (search_bar.getText().toLowerCase().contains(spcVehicles.getFirstName().toLowerCase())) || (search_bar.getText().toLowerCase().contains(spcVehicles.getLastName().toLowerCase()))) {
                    searchedItems.add(spcVehicles);
                }
            }
            for (SPCVehicles vehicle : vehicles)
            {
                if(vehicle.getReg().toLowerCase().contains(search_bar.getText().toLowerCase()))
                {
                    for(Customer c : customers)
                    {
                        if (c.getId() == vehicle.getCustomerID())
                        {
                            searchedItems.add(c);
                            break;
                        }
                    }
                }
            }
            ObservableList searched = FXCollections.observableArrayList(searchedItems);
            spcVehicleTable.setItems(searched);
        } catch (NullPointerException e)
        {
            System.out.println("Empty");
        }
    }
    private void setValueFactories()
    {
        spcID.setCellValueFactory(new PropertyValueFactory<>("spcID"));
        spcPostcode.setCellValueFactory(new PropertyValueFactory<>("spcPostcode"));
        spcAddress.setCellValueFactory(new PropertyValueFactory<>("spcAddress"));
        spcPhone.setCellValueFactory(new PropertyValueFactory<>("spcPhone"));
        spcEmail.setCellValueFactory(new PropertyValueFactory<>("spcEmail"));
        spcName.setCellValueFactory(new PropertyValueFactory<>("spcName"));
    }
}
