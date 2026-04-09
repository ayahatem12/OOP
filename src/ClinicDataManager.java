//package com.example.grouped;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//public class HelloApplication extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) throws IOException {
//
//        launch();
//    }
//}

public class ClinicDataManager {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        Clinic clinic = FileHandler.loadClinicData();
       // Clinic clinic = new Clinic("Health Center", "78 El-merghany street ");
        Mainview mainview = new Mainview();
        FileHandler.saveClinicData(clinic);
    }
}
