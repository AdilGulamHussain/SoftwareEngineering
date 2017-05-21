package common.logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by adilh on 27/01/2017.
 */
public class AdminController implements Initializable {

    @FXML
    private Tab Admin_Tab;

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
    @FXML
    public void logOut(ActionEvent event){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../gui/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
