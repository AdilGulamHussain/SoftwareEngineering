/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chris;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import common.logic.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import common.logic.SQLiteConnection;
/**
 *
 * @author adilh
 */
public class LoginController implements Initializable {
    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Label message;
    SQLiteConnection db = SQLiteConnection.getInstance();
    ResultSet rs = null;
    @FXML
    private void login(ActionEvent event) {
        String sql = "SELECT userID, UserName, Password, adminStatus FROM login";

        try {
            rs = db.query(sql);
            String user = txt_username.getText();
            String password = txt_password.getText();
            while (rs.next()) {
                if (rs.getString("UserName").equals(user) && rs.getString("Password").equals(password))
                {
                    AdminController.setIsAdmin(rs.getBoolean("adminStatus"));
                    Parent root = FXMLLoader.load(getClass().getResource("../common/gui/gui.fxml"));
                    Scene scene = new Scene(root);
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("GM SIS");
                    stage.show();
                    return;

                }
            }
        }
        catch (Exception e)
        {e.printStackTrace();
        }
        message.setText("Incorrect username or password");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}