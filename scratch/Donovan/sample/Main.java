package sample;

import common.logic.databconenction;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Diagnosis and Repairs / Scheduled Maintenance");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }
    
   public static void main(String[] args) {
        launch(args);
    }
}
