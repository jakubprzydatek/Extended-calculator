package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.calculations.Argument;
import sample.calculations.Calculation;

import java.math.BigInteger;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Kalkulator");
        primaryStage.setScene(new Scene(root, 1062, 467));
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.out.println(Calculation.convertResult("1010", 2, 16));
        launch(args);
    }

}
