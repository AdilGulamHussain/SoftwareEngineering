package Chris;

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
import common.logic.SQLiteConnection;

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
//inner join on spcID
public class SPCController implements Initializable{

    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<SPC> spc = new ArrayList();
    private ArrayList<SPCParts> spcParts = new ArrayList<>();
    private ArrayList<SPCVehicle> spcVehicles = new ArrayList<>();

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
    @FXML private TableColumn<SPCParts, Integer> aspcPartID;
    @FXML private TableColumn<SPCParts, Integer> aspcPartDescription;
    @FXML private TableColumn<SPCParts, String> aspcPartName;
    @FXML private TableColumn<SPCParts, String> afirstName;
    @FXML private TableColumn<SPCParts, Integer> acustomerID;
    @FXML private TableColumn<SPCParts, Integer>  aspcID;
    @FXML private TableColumn<SPCParts, String> alastName;
    @FXML private TableColumn<SPCParts, String> adeliveryDate;
    @FXML private TableColumn<SPCParts, Float> arepairCost;
    @FXML private TableColumn<SPCParts, String> aspcName;
    @FXML private TableColumn<SPCParts, String> areturnDate;
    @FXML private TableColumn<SPCParts, String> areturnStatus;


    @FXML private TableView<SPCVehicle> spcVehicleTable;
    @FXML private TableColumn<SPCVehicle, String> bvehicleRegistrationNumber;
    @FXML private TableColumn<SPCVehicle, String> bmodel;
    @FXML private TableColumn<SPCVehicle, String> bmake;
    @FXML private TableColumn<SPCVehicle, String> bfirstName;
    @FXML private TableColumn<SPCVehicle, Integer> bcustomerID;
    @FXML private TableColumn<SPCVehicle, Integer> bspcID;
    @FXML private TableColumn<SPCVehicle, String> blastName;
    @FXML private TableColumn<SPCVehicle, String> bdeliveryDate;
    @FXML private TableColumn<SPCVehicle, Float> brepairCost;
    @FXML private TableColumn<SPCVehicle, String> bspcName;
    @FXML private TableColumn<SPCVehicle, String> breturnDate;
    @FXML private TableColumn<SPCVehicle, String> breturnStatus;

    @FXML private TextField search_bar;


    private SQLiteConnection db = SQLiteConnection.getInstance();
    private static SPC selected1;
    private static SPCParts selected2;
    private static SPCVehicle selected3;
    //private static Customer selected;
    //need to have multiple selected
    //need to use foreign keys
    //add numbers under variables
    //check filltable methods
    //fix search
    //get check outstanding, create query and search working in spcfxml
    public void initialize(URL location, ResourceBundle resourceBundle){
        fillTable();
        fillSPCPartsTable();
        fillSPCVehicleTable();

        spcTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected1 = spcTable.getSelectionModel().getSelectedItem();
                }
        );

        spcPartsTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected2 = spcPartsTable.getSelectionModel().getSelectedItem();
                }
        );
        spcVehicleTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected3 = spcVehicleTable.getSelectionModel().getSelectedItem();
                }
        );

    }
    /*public void initialize(URL location, ResourceBundle resourceBundle)
    {
        fillTable();
        //search_bar.textProperty().addListener((ob, oldVal, newVal) -> { search();});
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
                    selected1 = spcTable.getSelectionModel().getSelectedItem();
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
*/
    public void fillTable(){
        fillSPCPartsTable();
        fillSPCVehicleTable();
        spcID.setCellValueFactory(new PropertyValueFactory<>("spcID"));
        spcPostcode.setCellValueFactory(new PropertyValueFactory<>("spcPostcode"));
        spcAddress.setCellValueFactory(new PropertyValueFactory<>("spcAddress"));
        spcPhone.setCellValueFactory(new PropertyValueFactory<>("spcPhone"));
        spcEmail.setCellValueFactory(new PropertyValueFactory<>("spcEmail"));
        spcName.setCellValueFactory(new PropertyValueFactory<>("spcName"));
        ArrayList<SPC> tableValues = new ArrayList<>();
        String sql = "SELECT * FROM SPC";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                tableValues.add(new SPC(rs));
            }
            spcTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void add()
    {
        try {
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("addSPC.fxml"));
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
            selected1 = spcTable.getSelectionModel().getSelectedItem();
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("editSPC.fxml"));
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
        selected1 = spcTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GMSIS");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete " + selected1.getSpcName() + "?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK)
        {
            db.update("DELETE FROM SPC WHERE spcID = " + selected1.getSpcID());
            fillTable();
        }
    }

    @FXML
    public void addVehicle()
    {
        try {
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("addVehicle.fxml"));
            newScreen.setTitle("New Vehicle");
            newScreen.setScene(new Scene(parent));
            newScreen.show();
            fillSPCVehicleTable();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void editVehicle()
    {
        try {
            selected3 = spcVehicleTable.getSelectionModel().getSelectedItem();
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("editVehicle.fxml"));
            newScreen.setTitle("Edit Vehicle");
            newScreen.setScene(new Scene(parent));
            newScreen.show();
            fillTable();
            fillTable();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteVehicle()
    {
        SPCVehicle selected3 = spcVehicleTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GMSIS");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the vehicle with Registration: " + selected3.getbvehicleRegistrationNumber() + "?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK)
        {
            db.update("DELETE FROM SPCVehicle WHERE vehicleRegistration = " + selected3.getbvehicleRegistrationNumber());
            fillTable();
        }
    }

    @FXML
    public void addPart()
    {
        try {
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("addPart.fxml"));
            newScreen.setTitle("New Part");
            newScreen.setScene(new Scene(parent));
            newScreen.show();
            fillTable();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void editPart()
    {
        try {
            selected2 = spcPartsTable.getSelectionModel().getSelectedItem();
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("editPart.fxml"));
            newScreen.setTitle("Edit Part");
            newScreen.setScene(new Scene(parent));
            newScreen.show();
            fillSPCPartsTable();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void deletePart()
    {
        SPCParts selected2 = spcPartsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GMSIS");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the part " + selected2.getAspcPartDescription() + "?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK)
        {
            db.update("DELETE FROM SPCParts WHERE spcPartID = " + selected2.getAspcPartID());
            fillTable();
        }
    }


    public static SPC getSelected() {
        return selected1;
    }
    public static SPCParts getSelectedPart() { return selected2; }
    public static SPCVehicle getSelectedVehicle() { return selected3; }
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
            sql = "SELECT *  FROM Vehicles";

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

    private void fillPartTable()
    {
        vehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        partBookingID.setCellValueFactory(new PropertyValueFactory<>("booking"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        ArrayList<Part> tableValues = new ArrayList<>();
        try {
            String sql = "SELECT *  FROM Parts";
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
            String sql = "SELECT * FROM Bills";
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
        spcID.setCellValueFactory(new PropertyValueFactory<>("spcID"));
        spcName.setCellValueFactory(new PropertyValueFactory<>("spcName"));
        spcPostcode.setCellValueFactory(new PropertyValueFactory<>("spcPostcode"));
        spcAddress.setCellValueFactory(new PropertyValueFactory<>("spcAddress"));
        spcPhone.setCellValueFactory(new PropertyValueFactory<>("spCPhone"));
        spcEmail.setCellValueFactory(new PropertyValueFactory<>("spcEmail"));
        ArrayList<SPC> tableValues = new ArrayList<>();
        String sql;
        if(selected1 == null) {
            sql = "SELECT *  FROM SPC";

            ResultSet rs = db.query(sql);
            try {
                while (rs.next()) {
                    tableValues.add(new SPC(rs));
                }
                spcTable.setItems(FXCollections.observableArrayList(tableValues));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            sql = "SELECT *  FROM SPC WHERE spcID = " + selected1.getSpcID();

            ResultSet rs = db.query(sql);
            try {
                while (rs.next()) {
                    tableValues.add(new SPC(rs));
                }
                spcTable.setItems(FXCollections.observableArrayList(tableValues));
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
    }



    private void fillSPCPartsTable()
    {
        aspcPartID.setCellValueFactory(new PropertyValueFactory<>("aspcPartID"));
        aspcPartName.setCellValueFactory(new PropertyValueFactory<>("aspcPartName"));
        aspcPartDescription.setCellValueFactory(new PropertyValueFactory<>("aspcPartDescription"));
        aspcID.setCellValueFactory(new PropertyValueFactory<>("aspcID"));
        acustomerID.setCellValueFactory(new PropertyValueFactory<>("acustomerID"));
        afirstName.setCellValueFactory(new PropertyValueFactory<>("afirstName"));
        alastName.setCellValueFactory(new PropertyValueFactory<>("alastName"));
        aspcName.setCellValueFactory(new PropertyValueFactory<>("aspcName"));
        adeliveryDate.setCellValueFactory(new PropertyValueFactory<>("adeliveryDate"));
        areturnDate.setCellValueFactory(new PropertyValueFactory<>("areturnDate"));
        arepairCost.setCellValueFactory(new PropertyValueFactory<>("arepairCost"));
        areturnStatus.setCellValueFactory(new PropertyValueFactory<>("areturnStatus"));
        ArrayList<SPCParts> tableValues = new ArrayList<>();
        String sql;
       if(selected2 == null) {
           sql = "SELECT *  FROM SPCParts";

           ResultSet rs = db.query(sql);
           try {
               while (rs.next()) {
                   tableValues.add(new SPCParts(rs));
               }
               spcPartsTable.setItems(FXCollections.observableArrayList(tableValues));
           } catch (SQLException e) {
               e.printStackTrace();
               }
       }       else {

               sql = "SELECT *  FROM SPCParts WHERE aspcPartID = " + selected2.getAspcPartID();

           ResultSet rs = db.query(sql);
           try {
               while (rs.next()) {
                   tableValues.add(new SPCParts(rs));
               }
               spcPartsTable.setItems(FXCollections.observableArrayList(tableValues));
           } catch (SQLException e) {
               e.printStackTrace();
           }


       }


    }


    private void fillSPCVehicleTable()
    {

        bvehicleRegistrationNumber.setCellValueFactory(new PropertyValueFactory<>("bvehicleRegistrationNumber"));
        bmodel.setCellValueFactory(new PropertyValueFactory<>("bmodel"));
        bmake.setCellValueFactory(new PropertyValueFactory<>("bmake"));
        bspcID.setCellValueFactory(new PropertyValueFactory<>("bspcID"));
        bcustomerID.setCellValueFactory(new PropertyValueFactory<>("bcustomerID"));
        bfirstName.setCellValueFactory(new PropertyValueFactory<>("bfirstName"));
        blastName.setCellValueFactory(new PropertyValueFactory<>("blastName"));
        bspcName.setCellValueFactory(new PropertyValueFactory<>("bspcName"));
        bdeliveryDate.setCellValueFactory(new PropertyValueFactory<>("bdeliveryDate"));
        breturnDate.setCellValueFactory(new PropertyValueFactory<>("breturnDate"));
        brepairCost.setCellValueFactory(new PropertyValueFactory<>("brepairCost"));
        breturnStatus.setCellValueFactory(new PropertyValueFactory<>("breturnStatus"));

        ArrayList<SPCVehicle> tableValues = new ArrayList<>();
        String sql;
        if(selected3 == null) {
            sql = "SELECT *  FROM SPCVehicle";

            ResultSet rs = db.query(sql);
            try {
                while (rs.next()) {
                    tableValues.add(new SPCVehicle(rs));
                }
                spcVehicleTable.setItems(FXCollections.observableArrayList(tableValues));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            sql = "SELECT *  FROM SPCVehicle WHERE bvehicleRegistrationNumber = " + selected3.getbvehicleRegistrationNumber();

            ResultSet rs = db.query(sql);
            try {
                while (rs.next()) {
                    tableValues.add(new SPCVehicle(rs));
                }
                spcVehicleTable.setItems(FXCollections.observableArrayList(tableValues));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    /*public void keyClick(KeyEvent event)
    {
        if(event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.UP || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT)
        {
            selectRow();
        }
    }*/
//break up into 3
  /*  @FXML
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
    }*/
    private void setValueFactories()
    {
        spcID.setCellValueFactory(new PropertyValueFactory<>("spcID"));
        spcPostcode.setCellValueFactory(new PropertyValueFactory<>("spcPostcode"));
        spcAddress.setCellValueFactory(new PropertyValueFactory<>("spcAddress"));
        spcPhone.setCellValueFactory(new PropertyValueFactory<>("spcPhone"));
        spcEmail.setCellValueFactory(new PropertyValueFactory<>("spcEmail"));
        spcName.setCellValueFactory(new PropertyValueFactory<>("spcName"));
    }
    private void setValueFactories2()
    {
        spcID.setCellValueFactory(new PropertyValueFactory<>("spcID"));
        spcPostcode.setCellValueFactory(new PropertyValueFactory<>("spcPostcode"));
        spcAddress.setCellValueFactory(new PropertyValueFactory<>("spcAddress"));
        spcPhone.setCellValueFactory(new PropertyValueFactory<>("spcPhone"));
        spcEmail.setCellValueFactory(new PropertyValueFactory<>("spcEmail"));
        spcName.setCellValueFactory(new PropertyValueFactory<>("spcName"));
    }
    private void setValueFactories3()
    {
        spcID.setCellValueFactory(new PropertyValueFactory<>("spcID"));
        spcPostcode.setCellValueFactory(new PropertyValueFactory<>("spcPostcode"));
        spcAddress.setCellValueFactory(new PropertyValueFactory<>("spcAddress"));
        spcPhone.setCellValueFactory(new PropertyValueFactory<>("spcPhone"));
        spcEmail.setCellValueFactory(new PropertyValueFactory<>("spcEmail"));
        spcName.setCellValueFactory(new PropertyValueFactory<>("spcName"));
    }
}
