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
 * Created by adilh on 19/02/2017.
 */
public class CustomerController implements Initializable{

    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

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
    @FXML private TableColumn<Part, String> cost;

    @FXML private TableView<Bill> billTable;
    @FXML private TableColumn<Bill, Integer> billID;
    @FXML private TableColumn<Bill,Integer> billBookingID;
    @FXML private TableColumn<Bill,Float> price;
    @FXML private TableColumn<Bill,Float> paid;
    @FXML private TableColumn<Bill,Float> balance;

    @FXML private TextField search_bar;


    private SQLiteConnection db = SQLiteConnection.getInstance();
    private static Customer selected;
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        fillTable();
        search_bar.textProperty().addListener((ob, oldVal, newVal) -> { search();});
        String sql = "SELECT *  FROM CustomerAccounts";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                customers.add(new Customer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        customerTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected = customerTable.getSelectionModel().getSelectedItem();
                    fillVehicleTable();
                    fillBookingTable();
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
        ArrayList<Customer> tableValues = new ArrayList<>();
        String sql = "SELECT *  FROM CustomerAccounts";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                tableValues.add(new Customer(rs));
            }
            customerTable.setItems(FXCollections.observableArrayList(tableValues));
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
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/AddCustomer.fxml"));
            newScreen.setTitle("New Customer");
            newScreen.setScene(new Scene(parent));
            newScreen.showAndWait();
            fillTable();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void edit()
    {
        if (selected==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Please select a customer first");
            alert.showAndWait();
        } else {

            try {
                selected = customerTable.getSelectionModel().getSelectedItem();
                Stage newScreen = new Stage();
                newScreen.initModality(Modality.APPLICATION_MODAL);
                Parent parent = FXMLLoader.load(getClass().getResource("../gui/EditCustomer.fxml"));
                newScreen.setTitle("New Customer");
                newScreen.setScene(new Scene(parent));
                newScreen.showAndWait();
                fillTable();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    public void delete()
    {
        if (selected !=null) {
            Customer selected = customerTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("GMSIS");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete " + selected.getfName() + " " + selected.getsName() + "?");
            Optional<ButtonType> response = alert.showAndWait();
            if(response.get() == ButtonType.OK)
            {
                db.update("DELETE FROM CustomerAccounts WHERE ID = " + selected.getId());
                fillTable();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Please select a customer first");
            alert.showAndWait();
        }
    }

    public static Customer getSelected() {
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
            {
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
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
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
            customerTable.getItems().clear();
            setValueFactories();
            ArrayList<Customer> searchedItems = new ArrayList<>();
            for (Customer customer : customers) {
                if ((customer.getfName().toLowerCase().contains(search_bar.getText().toLowerCase())) || (customer.getsName().toLowerCase().contains(search_bar.getText().toLowerCase())) || (search_bar.getText().toLowerCase().contains(customer.getfName().toLowerCase())) || (search_bar.getText().toLowerCase().contains(customer.getsName().toLowerCase()))) {
                    searchedItems.add(customer);
                }
            }
            for (Vehicle vehicle : vehicles)
            {
                if(vehicle.getReg().toLowerCase().contains(search_bar.getText().toLowerCase()))
                {
                    for(Customer c : customers)
                    {
                        if (c.getId() == vehicle.getCustomer())
                        {
                            searchedItems.add(c);
                            break;
                        }
                    }
                }
            }
            ObservableList searched = FXCollections.observableArrayList(searchedItems);
            customerTable.setItems(searched);
        } catch (NullPointerException e)
        {
            System.out.println("Empty");
        }
    }
    private void setValueFactories()
    {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        sName.setCellValueFactory(new PropertyValueFactory<>("sName"));
        add.setCellValueFactory(new PropertyValueFactory<>("add"));
        postcode.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
    }
}
