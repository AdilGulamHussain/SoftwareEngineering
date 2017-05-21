package common.logic;

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

    @FXML
    public TableView<Booking> bookingTable;
    @FXML
    public TableColumn<Booking, String> fName;
    @FXML
    public TableColumn<Booking, String> sName;
    @FXML
    public TableColumn<Booking, Integer> id;
    @FXML
    public TableColumn<Booking, String> manufacturer;
    @FXML
    public TableColumn<Booking, String> vReg;
    @FXML
    public TableColumn<Booking, String> mechanic;
    @FXML
    public TableColumn<Booking, Integer> duration;
    @FXML
    public TableColumn<Booking, String> bType;
    @FXML
    public TableColumn<Booking, String> Date;
    @FXML
    public TableColumn<Booking, String> SPC;
    @FXML
    public TableColumn<Booking, String> Part;
    @FXML
    public TableColumn<Booking, String> complete;

    @FXML
    public Button editbooking1;
    @FXML
    public Button addbooking1;
    @FXML
    public Button deletebooking1;
    @FXML
    public Button refresh;

    @FXML
    private TextField search_bar;


    private databconenction dbc = databconenction.getInstance();
    private static Booking selected;

    public static Booking getSelected() {
        return selected;
    }

    public void initialize(URL location, ResourceBundle resourceBundle) {
        fillTable();
        search_bar.textProperty().addListener((ob, oldVal, newVal) -> {
            Search();
        });
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
                }
        );
    }


    public void Search() {
        try {
            bookingTable.getItems().clear();
            setValueFactories();
            ArrayList<Booking> searchedItems = new ArrayList<>();
            for (Booking booking : bookings) {
                if ((booking.getVehicleRegistration().toLowerCase().contains(search_bar.getText().toLowerCase()))  )  {
                    searchedItems.add(booking);
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
        fName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        sName.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        id.setCellValueFactory(new PropertyValueFactory<>("BookingID"));
        manufacturer.setCellValueFactory((new PropertyValueFactory<>("VehicleManufacturer")));
        vReg.setCellValueFactory(new PropertyValueFactory<>("VehicleRegistration"));
        mechanic.setCellValueFactory(new PropertyValueFactory<>("Mechanic"));
        duration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        bType.setCellValueFactory(new PropertyValueFactory<>("BookingTtype"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        SPC.setCellValueFactory(new PropertyValueFactory<>("SPC"));
        Part.setCellValueFactory(new PropertyValueFactory<>("Part"));
        complete.setCellValueFactory(new PropertyValueFactory<>("Complete"));
    }

    @FXML

    public void addB() {
        System.out.println("You want to add a booking");
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/add.fxml"));
            Parent root1 = fxmlLoader.load();
            stage.setTitle("New Booking");
            stage.setScene(new Scene(root1));
            stage.show();
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/editb.fxml"));
                stage.setTitle("Edit Booking");
                Parent root2 = fxmlLoader.load();
                stage.setScene(new Scene(root2));
                stage.show();
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



}