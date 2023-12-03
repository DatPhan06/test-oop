package com.example.myjavafxapp;

import com.example.myjavafxapp.controller.Scene1Controller;
import com.example.myjavafxapp.speechAPI.SpeechAPI;
import com.example.myjavafxapp.sqlite.DatabaseManager;
import com.example.myjavafxapp.util.DictionaryManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DictionaryApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        DatabaseManager.createTable();
        SpeechAPI speechAPI =  new SpeechAPI();

        speechAPI.speak("en"," Welcome to MLD dictionary");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
        Parent root = loader.load();

        Scene1Controller scene1Controller = loader.getController();
        scene1Controller.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setResizable(false); // Thiết lập cố định cửa sổ
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scene");
        primaryStage.show();
    }

    public static void main(String[] args) {

        try {
            DictionaryManagement.insertFromFile("src/main/resources/txt/dictionaryEVplus.txt");
            System.out.println("Import dictionary sucessfully. \n");

        } catch (Exception e) {
            System.out.println(e);
        }

        launch(args);
        DictionaryManagement.exportToFile("src/main/resources/txt/dictionaryEVout.txt");

    }
}

