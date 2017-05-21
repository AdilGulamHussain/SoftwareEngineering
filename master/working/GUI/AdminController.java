package common.logic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by adilh on 27/01/2017.
 */
public class AdminController implements Initializable {

    @FXML
    private Tab Admin_Tab;
    private Tab Booking_Tab;
    private Tab Vehicle_Tab;
    private Tab SPC_Tab;
    private Tab Parts_Tab;
    private Tab Customer_Tab;

    private static boolean isAdmin;

    public static void setIsAdmin(boolean type)
    {
        isAdmin = type;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if (!isAdmin) {
            Admin_Tab.setDisable(true);
        }


    }
}
