package com.example.myjavafxapp.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Lớp cơ sở cho tất cả các controller liên quan đến từ điển trong ứng dụng JavaFX.
 * Các lớp con cần triển khai phương thức {@code setPrimaryStage} để thiết lập primaryStage.
 */
public abstract class DictionaryController {

    /** Primary stage của ứng dụng. */
    @FXML
    private Stage primaryStage;

    /**
     * Phương thức trừu tượng để thiết lập primaryStage từ bên ngoài.
     *
     * @param primaryStage Stage chính của ứng dụng.
     */
    public abstract void setPrimaryStage(Stage primaryStage);

}
