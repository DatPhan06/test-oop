package com.example.myjavafxapp.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * Controller cho Scene 1 của ứng dụng JavaFX.
 */
public class Scene1Controller extends DictionaryController {

    /** Primary stage của ứng dụng. */
    @FXML
    private Stage primaryStage;

    /**
     * Thiết lập primaryStage từ bên ngoài.
     *
     * @param primaryStage Stage chính của ứng dụng.
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Chuyển đến Scene 2 khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     * @throws Exception Nếu có lỗi trong quá trình chuyển Scene.
     */
    public void switchToScene2(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/myjavafxapp/Scene2.fxml"));
        Parent root = loader.load();

        Scene2Controller scene2Controller = loader.getController();
        scene2Controller.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    /**
     * Chuyển đến Scene 3 khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     * @throws Exception Nếu có lỗi trong quá trình chuyển Scene.
     */
    public void switchToScene3(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/myjavafxapp/Scene3.fxml"));
        Parent root = loader.load();

        Scene3Controller scene3Controller = loader.getController();
        scene3Controller.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    /**
     * Chuyển đến Scene 4 khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     * @throws Exception Nếu có lỗi trong quá trình chuyển Scene.
     */
    public void switchToScene4(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/myjavafxapp/Scene4.fxml"));
        Parent root = loader.load();

        Scene4Controller scene4Controller = loader.getController();
        scene4Controller.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    /**
     * Chuyển đến Scene 5 khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     * @throws Exception Nếu có lỗi trong quá trình chuyển Scene.
     */
    public void switchToScene5(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/myjavafxapp/Scene5.fxml"));
        Parent root = loader.load();

        Scene5Controller scene5Controller = loader.getController();
        scene5Controller.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    /**
     * Chuyển đến Scene 6 khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     * @throws Exception Nếu có lỗi trong quá trình chuyển Scene.
     */
    public void switchToScene6(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/myjavafxapp/Scene6.fxml"));
        Parent root = loader.load();

        Scene6Controller scene6Controller = loader.getController();
        scene6Controller.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    /**
     * Chuyển đến Scene 7 khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     * @throws Exception Nếu có lỗi trong quá trình chuyển Scene.
     */
    public void switchToScene7(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/myjavafxapp/Scene7.fxml"));
        Parent root = loader.load();

        Scene7Controller scene7Controller = loader.getController();
        scene7Controller.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }
    @FXML
    private void showExitConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");

        // Get the result of the alert (OK or Cancel)
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // If OK is pressed, exit the application
                Platform.exit();
            }
        });
    }
}
