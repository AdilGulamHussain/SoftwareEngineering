package common.logic;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

/**
 * Created by Don on 02/03/2017.
 */
public class RepairController implements Initializable {

    private ArrayList<Booking> bookings = new ArrayList<>();

    @FXML public TableView <Booking> bookingTable;;
    @FXML public TableColumn<Booking, Integer> id;
    @FXML public TableColumn<Booking,String> vReg;
    @FXML public TableColumn<Booking, String> mechanic;
    @FXML public TableColumn<Booking, Integer> duruation;
    @FXML public TableColumn<Booking, String> bType;
    @FXML public TableColumn<Booking, String> pDate;
    @FXML public TableColumn<Booking, String> fDate;
    @FXML public TableColumn<Booking, Integer> mileage;

    @FXML public Button editbooking1;
    @FXML public Button addbooking1;
    @FXML public Button deletebooking1;

    @FXML private TextField search_bar;


    private databconenction dbc = databconenction.getInstance();
    private static Booking selected;

    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        fillTable();
        search_bar.textProperty().addListener((ob, oldVal, newVal) -> Search());
        String sql1 = "SELECT *  FROM Booking";
        ResultSet rsb = dbc.query(sql1);
        try {
            while (rsb.next())
            {
                bookings.add(new Booking(rsb));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


}
    private void Search() {
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
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        vReg.setCellValueFactory(new PropertyValueFactory<>("vReg"));
        mechanic.setCellValueFactory(new PropertyValueFactory<>("mechanic"));
        duruation.setCellValueFactory(new PropertyValueFactory<>("duruation"));
        bType.setCellValueFactory(new PropertyValueFactory<>("bTtype"));
        pDate.setCellValueFactory(new PropertyValueFactory<>("pDate"));
        fDate.setCellValueFactory(new PropertyValueFactory<>("fDate"));
        mileage.setCellValueFactory(new PropertyValueFactory<>("mileage"));
    }

    @FXML

    public void addB() {
        System.out.println("You want to add a booking");
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/add.fxml"));
            Parent root1 = fxmlLoader.load();
            stage.setScene(new Scene(root1));
            stage.show();

            }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
@FXML

    public void editB(ActionEvent f) {
    System.out.println("You want to edit a booking");
    try {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/edit.fxml"));
        Parent root2 = fxmlLoader.load();
        stage.setScene(new Scene(root2));
        stage.show();

    }
    catch (IOException a)
    {
        a.printStackTrace();
    }
    }
@FXML
    public void deleteB(ActionEvent d) {
        System.out.println("You want to delete a booking");
           try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/delete.fxml"));
            Parent root3 = fxmlLoader.load();
            stage.setScene(new Scene(root3));
            stage.show();

        }
        catch (IOException ie)
        {
            ie.printStackTrace();
        }

    }


}
