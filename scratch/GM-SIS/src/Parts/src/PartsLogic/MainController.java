package Parts.src.PartsLogic;

import Donovan.common.logic.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import common.logic.SQLiteConnection;

import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by MohammedKhan on 16/03/2017.
 */
public class MainController implements Initializable {
    public ArrayList<InstalledParts> Installedp = new ArrayList<>();

    @FXML
    private Accordion mainAccordion;

    @FXML
    private TableView<StockParts> partsTable;

    @FXML
    private TableColumn<StockParts, Integer> columnpartID;

    @FXML
    private TableColumn<StockParts, String> columnName;

    @FXML
    private TableColumn<StockParts, String> columnDescription;

    @FXML
    private TableColumn<StockParts, Integer> columnStock;

    @FXML
    private TableColumn<StockParts, String> columnCost;

    @FXML
    private Button addPartBtn;

    @FXML
    private Button deletePartBtn;

    @FXML
    private Button editPartBtn;

    @FXML
    private Button btnLoad;

    @FXML
    private TableView<InstalledParts> InstalledTable;

    @FXML
    private TableColumn<InstalledParts, Integer> columnPartID;

    @FXML
    private TableColumn<InstalledParts, String> columnRegID;

    @FXML
    private TableColumn<InstalledParts, Integer> columnBookingID;

    @FXML
    private TableColumn<InstalledParts, String> columnInstallation;

    @FXML
    private TableColumn<InstalledParts, String> columnWarrantyExp;

    @FXML
    private TableView<OrderParts> OrderTable;

    @FXML
    private TableColumn<OrderParts, Integer> columnOrderID;

    @FXML
    private TableColumn<OrderParts, Integer> columnPartID1;

    @FXML
    private TableColumn<OrderParts, String> columnDelivery;

    @FXML
    private TableColumn<OrderParts, Integer> columnQuantity;

    @FXML
    private TableView<MergeParts> InstalledPartsP;

    @FXML
    private TableColumn<MergeParts, Integer> columnPartIDP;

    @FXML
    private TableColumn<MergeParts, String> columnRegP;

    @FXML
    private TableColumn<MergeParts, Integer> columnBookingP;

    @FXML
    private TableColumn<MergeParts, String> columnInstallationP;

    @FXML
    private TableColumn<MergeParts, String> columnWarrantyP;

    @FXML
    private TableView<StockParts> PartDetailsTable;

    @FXML
    private TableColumn<StockParts, Integer> columnPartIDD;

    @FXML
    private TableColumn<StockParts, String> columnNameD;

    @FXML
    private TableColumn<StockParts, String> columnDescriptionD;

    @FXML
    private TableColumn<StockParts, Double> columnCostD;


    @FXML
    private TextField search_bar;
    @FXML
    private TextField search_bar1;



    private SQLiteConnection db = SQLiteConnection.getInstance();
    public static StockParts selected;
    public static InstalledParts selected1;
    public static OrderParts selected2;
    public static MergeParts selected3;

    public void initialize(URL location, ResourceBundle resourceBundle){
        fillTable();
        fillInstalledTable();
        fillOrderTable();
        fillInstalledTableP();
        fillPartDetailsTable();

        search_bar.textProperty().addListener((ob, oldVal, newVal) -> { search();});
        String sql = "SELECT *  FROM InstalledParts";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                Installedp.add(new InstalledParts(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        partsTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected = partsTable.getSelectionModel().getSelectedItem();
                }
        );

        InstalledTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected1 = InstalledTable.getSelectionModel().getSelectedItem();
                }
        );
        OrderTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected2 = OrderTable.getSelectionModel().getSelectedItem();
                }
        );
    }

    public void fillTable(){
        setValueFactories();
        ArrayList<StockParts> tableValues = new ArrayList<>();
        String sql = "SELECT * FROM Parts";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                tableValues.add(new StockParts(rs));
            }
            partsTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void setValueFactories(){
        columnpartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnStock.setCellValueFactory(new PropertyValueFactory<>("stockLevel"));
        columnCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
    }
    @FXML
    public void add()
    {
        try {
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
            newScreen.setTitle("Add part");
            newScreen.setScene(new Scene(parent));
            newScreen.showAndWait();
            fillTable();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    public void delete()
    {
        StockParts selected = partsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GMSIS");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK)
        {
            db.update("DELETE FROM Parts WHERE partID = " + selected.getpartID());
            fillTable();
        }
    }

    public void fillInstalledTable(){
        setValueFactories1();
        ArrayList<InstalledParts> tableValues = new ArrayList<>();
        String sql = "SELECT * FROM InstalledParts";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                tableValues.add(new InstalledParts(rs));
            }
            InstalledTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e){
            e.printStackTrace();
        }
        InstalledTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected1 = InstalledTable.getSelectionModel().getSelectedItem();
                }
        );
    }
    public void setValueFactories1(){
        columnPartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        columnRegID.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistrationNumber"));
        columnBookingID.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        columnInstallation.setCellValueFactory(new PropertyValueFactory<>("installationDate"));
        columnWarrantyExp.setCellValueFactory(new PropertyValueFactory<>("warrantyDate"));
    }

    public void fillOrderTable(){
        setValueFactories2();
        ArrayList<OrderParts> tableValues = new ArrayList<>();
        String sql = "SELECT * FROM PartsOrder";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                tableValues.add(new OrderParts(rs));
            }
            OrderTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void setValueFactories2(){
        columnOrderID.setCellValueFactory(new PropertyValueFactory<OrderParts, Integer>("orderID"));
        columnPartID1.setCellValueFactory(new PropertyValueFactory<OrderParts, Integer>("partID"));
        columnDelivery.setCellValueFactory(new PropertyValueFactory<OrderParts, String>("expectedDate"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<OrderParts, Integer>("quantity"));
    }
    @FXML
    public void edit()
    {
        try {
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("EditPart.fxml"));
            newScreen.setTitle("Edit part");
            newScreen.setScene(new Scene(parent));
            newScreen.showAndWait();
            fillTable();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static StockParts getSelected(){
        return  selected;
    }
    @FXML
    public void add1()
    {
        try {
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("AddVPart.fxml"));
            newScreen.setTitle("Add part");
            newScreen.setScene(new Scene(parent));
            newScreen.showAndWait();
            fillTable();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    public void edit1()
    {
        try {
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("EditVPart.fxml"));
            newScreen.setTitle("Edit part");
            newScreen.setScene(new Scene(parent));
            newScreen.showAndWait();
            fillInstalledTable();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    public void delete1()
    {
        InstalledParts selected = InstalledTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GMSIS");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK)
        {
            db.update("DELETE FROM InstalledParts WHERE partID = " + selected.getPartID());
            db.update("UPDATE Parts SET stockLevel = stockLevel + 1 WHERE PartID =" + selected.getPartID());
            db.update("DELETE FROM Booking WHERE BookingID = " + selected.getBookingID());
            fillInstalledTable();
        }
    }
    public static InstalledParts getSelected1(){
        return  selected1;
    }
    @FXML
    public void order()
    {
        try {
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("OrderPart.fxml"));
            newScreen.setTitle("Order part");
            newScreen.setScene(new Scene(parent));
            newScreen.showAndWait();
            fillOrderTable();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void editOrder()
    {
        try {
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("EditOrder.fxml"));
            newScreen.setTitle("Order part");
            newScreen.setScene(new Scene(parent));
            newScreen.showAndWait();
            fillOrderTable();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    public void delete2()
    {
        OrderParts selected = OrderTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GMSIS");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK)
        {
            db.update("DELETE FROM PartsOrder WHERE orderID = " + selected.getOrderID());
            fillOrderTable();
        }
    }
    public static OrderParts getSelected2(){
        return selected2;
    }

    @FXML
    public void refresh(){
        fillTable();
        fillInstalledTable();
        fillOrderTable();
        fillPartDetailsTable();
        fillInstalledTableP();
    }
    @FXML
    public void refresh1(){
        fillInstalledTable();
        fillTable();
        fillOrderTable();
        fillPartDetailsTable();
        fillInstalledTableP();
    }
    @FXML
    public void refresh2(){
        fillOrderTable();
        fillTable();
        fillInstalledTable();
        fillPartDetailsTable();
        fillInstalledTableP();
    }
    public void fillInstalledTableP() {
        setValueFactories3();
        ArrayList<MergeParts> tableValues = new ArrayList<>();
        String sql = "SELECT InstalledParts.partID, Booking.VehicleRegistration, Booking.BookingID, Booking.Date, InstalledParts.warrantyDate\n" +
                "FROM Booking\n" +
                "LEFT JOIN InstalledParts\n" +
                "ON InstalledParts.bookingID = Booking.BookingID\n" +
                "WHERE Booking.Part = \"Yes\"";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next()) {
                tableValues.add(new MergeParts(rs));
            }
            InstalledPartsP.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        InstalledPartsP.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected3 = InstalledPartsP.getSelectionModel().getSelectedItem();
                }
        );
    }
    public void setValueFactories3(){
        columnPartIDP.setCellValueFactory(new PropertyValueFactory<>("partID"));
        columnRegP.setCellValueFactory(new PropertyValueFactory<>("vehicleRegistrationNumber"));
        columnBookingP.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        columnInstallationP.setCellValueFactory(new PropertyValueFactory<>("installationDate"));
        columnWarrantyP.setCellValueFactory(new PropertyValueFactory<>("warrantyDate"));
    }
    private void fillPartDetailsTable()
    {
        columnPartIDD.setCellValueFactory(new PropertyValueFactory<>("partID"));
        columnNameD.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDescriptionD.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnCostD.setCellValueFactory(new PropertyValueFactory<>("cost"));
        ArrayList<StockParts> tableValues = new ArrayList<>();
        String sql;
        try {
            sql = "SELECT Parts.partID, Parts.name, Parts.description, Parts.stockLevel, Parts.cost FROM Parts, InstalledParts WHERE Parts.partID = InstalledParts.partID";

            ResultSet rs = db.query(sql);
            try {
                while (rs.next()) {
                    tableValues.add(new StockParts(rs));
                }
                PartDetailsTable.setItems(FXCollections.observableArrayList(tableValues));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e)
        {

        }
    }
    @FXML
    public void search()
    {

        try {
            InstalledTable.getItems().clear();
            setValueFactories1();
            ArrayList<InstalledParts> searchedItems = new ArrayList<>();
            for (InstalledParts installedParts : Installedp) {
                if ((installedParts.getVehicleRegistrationNumber().toLowerCase().contains(search_bar.getText().toLowerCase())) || (installedParts.getBookingID().toString().toLowerCase().contains(search_bar.getText().toLowerCase())) || (search_bar.getText().toLowerCase().contains(installedParts.getVehicleRegistrationNumber().toLowerCase())) || (search_bar.getText().toLowerCase().contains(installedParts.getBookingID().toString().toLowerCase()))) {
                    searchedItems.add(installedParts);
                }
            }

            ObservableList searched = FXCollections.observableArrayList(searchedItems);
            InstalledTable.setItems(searched);
        } catch (NullPointerException e)
        {
            System.out.println("Empty");
        }
    }
}