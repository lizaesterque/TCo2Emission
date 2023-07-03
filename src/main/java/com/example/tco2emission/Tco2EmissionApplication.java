package com.example.tco2emission;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class Tco2EmissionApplication extends Application {


//    start the app this method will load the fmlx file, and created the fist windows
    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Tco2EmissionApplication.class.getResource("Graphview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 700);
        stage.setTitle("CO2 emissions");
        Image icon = new Image("file:C:/Users/lizae/OneDrive/Desktop/Tco2Emission/images/icon.jpg");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}