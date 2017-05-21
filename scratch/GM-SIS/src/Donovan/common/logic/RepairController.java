package Donovan.common.logic;

import common.logic.Bill;
import common.logic.Customer;
import common.logic.SQLiteConnection;
import common.logic.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * Created by Don on 02/03/2017.
 */
public class RepairController implements Initializable {

    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Mechanic> mechanics = new ArrayList<>();


    @FXML public TableView<Booking> bookingTable;
    @FXML public TableColumn<Booking, String> fname;
    @FXML public TableColumn<Booking, String> sname;
    @FXML public TableColumn<Booking, Integer> bid;
    @FXML public TableColumn<Booking, String> vreg;
    @FXML public TableColumn<Booking, String> bType;
    @FXML public TableColumn<Booking, String> Date;
    @FXML public TableColumn<Booking, String> SPC;
    @FXML public TableColumn<Booking, String> Part;
    @FXML public TableColumn<Booking, String> complete;
    @FXML public TableColumn<Booking, Float> costs;


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

    @FXML private TableView<Bill> billTable;
    @FXML private TableColumn<Bill, Integer> billID;
    @FXML private TableColumn<Bill,Integer> billBookingID;
    @FXML private TableColumn<Bill,Float> price;
    @FXML private TableColumn<Bill,Float> paid;
    @FXML private TableColumn<Bill,Float> balance;

    @FXML private TableView<Mechanic> mechanicTable;
    @FXML private TableColumn<Mechanic, String> mName;
    @FXML private TableColumn<Mechanic,Integer> duration;

    @FXML public Button editbooking1;
    @FXML public Button addbooking1;
    @FXML public Button deletebooking1;
    @FXML public Button refresh;

    @FXML private TextField search_bar;


    private SQLiteConnection dbc = SQLiteConnection.getInstance();
    private static Booking selected;

    public static Booking getSelected() {
        return selected;
    }

    public void initialize(URL location, ResourceBundle resourceBundle) {
        fillTable();
        search_bar.textProperty().addListener((ob, oldVal, newVal) -> {Search(); });
        String sql1 = "SELECT *  FROM Booking";
        ResultSet rsb = dbc.query(sql1);
        try {
            while (rsb.next()) {
                bookings.add(new Booking(rsb));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        bookingTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected = bookingTable.getSelectionModel().getSelectedItem();
                    fillCustomerTable();
                    fillVehicleTable();


                }
        );




    }


    public void Search() {

        try {
            bookingTable.getItems().clear();
            setValueFactories();
            ArrayList<Booking> searchedItems = new ArrayList<>();
            for (Booking booking : bookings) {
                if ((booking.getBookingTtype().toLowerCase().contains(search_bar.getText().toLowerCase()))
                  || booking.getVehicleRegistration().toLowerCase().contains(search_bar.getText().toLowerCase())
                  || booking.getFirstName().toLowerCase().contains(search_bar.getText().toLowerCase())
                  || booking.getSurname().toLowerCase().contains(search_bar.getText().toLowerCase()))

                {
                    searchedItems.add(booking);
                }
            }

            for (Vehicle vehicle : vehicles) {
                if ((vehicle.getMake().toLowerCase().contains(search_bar.getText().toLowerCase()))) {
                    for(Booking v : bookings)
                    {
                        if (v.getBookingID() == vehicle.getCustomer())
                        {
                            searchedItems.add(v);
                            break;
                        }
                    }
                }
            }


            ObservableList<Booking> searched = FXCollections.observableArrayList(searchedItems);
            bookingTable.setItems(searched);
        } catch (NullPointerException e)
        {
            System.out.println("Empty");
        }
    }

    public void fillTable()
    {
        setValueFactories();
        ArrayList<Booking> tableValues = new ArrayList<>();
        String sql = "SELECT *  FROM Booking";
        ResultSet rsb = dbc.query(sql);
        try {
            while (rsb.next())
            {
                tableValues.add(new Booking(rsb));
            }
            bookingTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setValueFactories() {
        fname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        sname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        bid.setCellValueFactory(new PropertyValueFactory<>("BookingID"));
        vreg.setCellValueFactory(new PropertyValueFactory<>("VehicleRegistration"));
        bType.setCellValueFactory(new PropertyValueFactory<>("BookingTtype"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        SPC.setCellValueFactory(new PropertyValueFactory<>("SPC"));
        Part.setCellValueFactory(new PropertyValueFactory<>("Part"));
        complete.setCellValueFactory(new PropertyValueFactory<>("Complete"));
        costs.setCellValueFactory(new PropertyValueFactory<>("Costs"));


    }

    @FXML

    public void addB() {
        System.out.println("You want to add a booking");
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../sample/add.fxml"));
            Parent root1 = fxmlLoader.load();
            stage.setTitle("New Booking");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
                    }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        fillTable();
    }
    @FXML

    public void editB(ActionEvent f) {
        System.out.println("You want to edit a booking");
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Please select a booking first");
            alert.showAndWait();
        } else {
            try {
                selected = bookingTable.getSelectionModel().getSelectedItem();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../sample/editb.fxml"));
                stage.setTitle("Edit Booking");
                Parent root2 = fxmlLoader.load();
                stage.setScene(new Scene(root2));
                stage.showAndWait();
                fillTable();
            } catch (IOException a) {
                a.printStackTrace();
            }
        }
    }
    @FXML
    public void deleteB(ActionEvent d) {
        System.out.println("You want to delete a booking");
        if (selected != null) {
            Booking selected = bookingTable.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Booking");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete booking " + selected.getBookingID() + "?");
            Optional<ButtonType> response = alert.showAndWait();

            if (response.get() == ButtonType.OK) {
                dbc.update("DELETE FROM Booking WHERE BookingID = " + selected.getBookingID());
                dbc.update("DELETE FROM InstalledParts WHERE BookingID = "+ selected.getBookingID());
                fillTable();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Please select a booking first");
            alert.showAndWait();


        }
    }

    public void refresh(ActionEvent r){
        fillTable();

    }

    private void fillCustomerTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        sName.setCellValueFactory(new PropertyValueFactory<>("sName"));
        add.setCellValueFactory(new PropertyValueFactory<>("add"));
        postcode.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        ArrayList<Customer> tableValuesc = new ArrayList<>();
        String sql2;
        System.out.println(selected.getBookingID());
        try {
            sql2 = "SELECT *  FROM CustomerAccounts WHERE ID = " + selected.getBookingID();

            ResultSet rsc = dbc.query(sql2);
            try {
                while (rsc.next()) {
                    tableValuesc.add(new Customer(rsc));
                }
                customerTable.setItems(FXCollections.observableArrayList(tableValuesc));
            } catch (SQLException f) {
                f.printStackTrace();
            }
        } catch (NullPointerException e)
        {

        }
    }

    private void fillVehicleTable() {
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
            sql = "SELECT *  FROM Vehicles WHERE CustomerID = " + selected.getBookingID();

            ResultSet rs = dbc.query(sql);
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

    }



