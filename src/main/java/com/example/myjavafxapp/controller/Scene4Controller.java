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
import com.example.myjavafxapp.util.DictionaryManagement;

import java.io.IOException;

/**
 * Controller cho Scene 4 của ứng dụng JavaFX.
 */
public class Scene4Controller extends DictionaryController {
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
     * Bắt đầu trò chơi từ vựng khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     */
    public void startWordGame(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/myjavafxapp/WordGame.fxml"));
            Parent wordGameRoot = loader.load();

            // Truyền primaryStage cho WordGameController
            WordGameController wordGameController = loader.getController();
            wordGameController.setPrimaryStage(primaryStage);

            Scene scene = new Scene(wordGameRoot);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ, có thể hiển thị thông báo lỗi cho người dùng.
        }
    }

    /**
     * Chuyển đến Scene 1 khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     * @throws Exception Nếu có lỗi trong quá trình chuyển Scene.
     */
    public void switchToScene1(ActionEvent event) throws Exception {
        loadScene("Scene1.fxml");
    }

    /**
     * Chuyển đến Scene 2 khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     * @throws Exception Nếu có lỗi trong quá trình chuyển Scene.
     */
    public void switchToScene2(ActionEvent event) throws Exception {
        loadScene("Scene2.fxml");
    }

    /**
     * Chuyển đến Scene 3 khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     * @throws Exception Nếu có lỗi trong quá trình chuyển Scene.
     */
    public void switchToScene3(ActionEvent event) throws Exception {
        loadScene("Scene3.fxml");
    }

    /**
     * Chuyển đến Scene 5 khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     * @throws Exception Nếu có lỗi trong quá trình chuyển Scene.
     */
    public void switchToScene5(ActionEvent event) throws Exception {
        loadScene("Scene5.fxml");
    }

    /**
     * Chuyển đến Scene 6 khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     * @throws Exception Nếu có lỗi trong quá trình chuyển Scene.
     */
    public void switchToScene6(ActionEvent event) throws Exception {
        loadScene("Scene6.fxml");
    }

    /**
     * Chuyển đến Scene 7 khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     * @throws Exception Nếu có lỗi trong quá trình chuyển Scene.
     */
    public void switchToScene7(ActionEvent event) throws Exception {
        loadScene("Scene7.fxml");
    }

    /**
     * Load Scene mới từ file FXML và thiết lập primaryStage cho controller tương ứng.
     *
     * @param fxmlFileName Tên file FXML của Scene mới.
     * @throws Exception Nếu có lỗi trong quá trình load Scene.
     */
    private void loadScene(String fxmlFileName) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/myjavafxapp/" + fxmlFileName));
        Parent root = loader.load();

        // Lấy controller của scene mới và truyền primaryStage
        Object controller = loader.getController();
        if (controller instanceof Scene1Controller) {
            ((Scene1Controller) controller).setPrimaryStage(primaryStage);
        } else if (controller instanceof Scene2Controller) {
            ((Scene2Controller) controller).setPrimaryStage(primaryStage);
        } else if (controller instanceof Scene3Controller) {
            ((Scene3Controller) controller).setPrimaryStage(primaryStage);
        } else if (controller instanceof Scene4Controller) {
            ((Scene4Controller) controller).setPrimaryStage(primaryStage);
        } else if (controller instanceof Scene5Controller) {
            ((Scene5Controller) controller).setPrimaryStage(primaryStage);
        } else if (controller instanceof Scene6Controller) {
            ((Scene6Controller) controller).setPrimaryStage(primaryStage);
        } else if (controller instanceof Scene7Controller) {
            ((Scene7Controller) controller).setPrimaryStage(primaryStage);
        }

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
