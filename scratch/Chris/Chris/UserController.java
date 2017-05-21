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
public class UserController implements Initializable{

    private ArrayList<User> user = new ArrayList<>();


    @FXML private TableView<User> userTable;
    @FXML private TableColumn<User, Integer> userID;
    @FXML private TableColumn<User, String> username;
    @FXML private TableColumn<User, String> password;
    @FXML private TableColumn<User, String> adminStatus;

    @FXML private TextField search_bar;


    private SQLiteConnection db = SQLiteConnection.getInstance();
    private static User selected;
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        fillTable();
        search_bar.textProperty().addListener((ob, oldVal, newVal) -> { search();});
        String sql = "SELECT *  FROM User";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                user.add(new User(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        userTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected = userTable.getSelectionModel().getSelectedItem();
                    fillUserTable();

                }
        );
    }

    public void fillTable()
    {
        setValueFactories();
        ArrayList<User> tableValues = new ArrayList<>();
        String sql = "SELECT *  FROM User";
        ResultSet rs = db.query(sql);
        try {
            while (rs.next())
            {
                tableValues.add(new User(rs));
            }
            userTable.setItems(FXCollections.observableArrayList(tableValues));
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
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/AddUser.fxml"));
            newScreen.setTitle("New User");
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
            selected = userTable.getSelectionModel().getSelectedItem();
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("../gui/EditUser.fxml"));
            newScreen.setTitle("New User");
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
        User selected = userTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GMSIS");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete " + selected.getUsername() + "?");
        Optional<ButtonType> response = alert.showAndWait();
        if(response.get() == ButtonType.OK)
        {
            db.update("DELETE FROM User WHERE userID = " + selected.getUserID());
            fillTable();
        }
    }

    public static User getSelected() {
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



    /*public void keyClick(KeyEvent event)
    {
        if(event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.UP || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT)
        {
            selectRow();
        }
    }*/


    private void setValueFactories()
    {
        userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        adminStatus.setCellValueFactory(new PropertyValueFactory<>("adminStatus"));
    }
}
