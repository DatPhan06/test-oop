module com.example.myjavafxapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
//    requires java.google.speech.api;
    requires json;
    requires jlayer;
    requires com.opencsv;

    opens com.example.myjavafxapp to javafx.fxml;
    exports com.example.myjavafxapp;
    exports com.example.myjavafxapp.controller;
    opens com.example.myjavafxapp.controller to javafx.fxml;
}