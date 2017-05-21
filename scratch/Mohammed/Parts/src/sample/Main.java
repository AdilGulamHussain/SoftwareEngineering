package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("PartsMainTest.fxml"));
        primaryStage.setTitle("Parts Record");
        primaryStage.setScene(new Scene(root, 655, 632));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
