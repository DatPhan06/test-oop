package com.example.myjavafxapp.controller;

import com.example.myjavafxapp.speechAPI.SpeechAPI;
import com.example.myjavafxapp.translator.Translate;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller cho Scene 3 của ứng dụng JavaFX.
 */
public class Scene3Controller extends DictionaryController {

    @FXML
    private ChoiceBox<String> yourChoiceBox;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea resultTextArea;

    @FXML
    private Button translateButton;

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
     * Xử lý sự kiện khi nút dịch được nhấn.
     *
     * @throws IOException Nếu có lỗi trong quá trình dịch.
     */
    @FXML
    private void translateButtonAction() throws IOException {

        // Logic khi nút translate được nhấn

        String searchTerm = inputTextArea.getText();
        String result;
        String languageChoice = handleChoiceBoxAction();
        if (languageChoice == null) {
            languageChoice = "Vietnam vi";
        }

        // Tìm vị trí của dấu cách cuối cùng
        int lastIndex = languageChoice.lastIndexOf(' ');
        String result1 = "vi";
        // Kiểm tra xem có dấu cách không
        if (lastIndex != -1) {
            // Lấy ra phần tử từ vị trí sau dấu cách cuối cùng đến hết chuỗi
            result1 = languageChoice.substring(lastIndex + 1);
        } else {
            // Nếu không có dấu cách, in ra chuỗi gốc
            System.out.println(languageChoice);
        }
        result = Translate.translate(result1, searchTerm);

        resultTextArea.setText(result);
    }

    /**
     * Xử lý sự kiện khi nút nói được nhấn.
     *
     * @throws IOException Nếu có lỗi trong quá trình nói.
     */
    @FXML
    private void SpeechAction() throws IOException {

        String textresult = resultTextArea.getText();

        String languageChoice = handleChoiceBoxAction();
        if (languageChoice == null) {
            languageChoice = "Vietnam vi";
        }
        SpeechAPI speechAPI = new SpeechAPI();

        // Tìm vị trí của dấu cách cuối cùng
        String result1 = "vi";
        int lastIndex = languageChoice.lastIndexOf(' ');

        // Kiểm tra xem có dấu cách không
        if (lastIndex != -1) {
            // Lấy ra phần tử từ vị trí sau dấu cách cuối cùng đến hết chuỗi
            result1 = languageChoice.substring(lastIndex + 1);
            System.out.println(languageChoice);
        } else {
            // Nếu không có dấu cách, in ra chuỗi gốc
            System.out.println(languageChoice.substring(languageChoice.length() - 2));
        }
        speechAPI.speak(result1, textresult);
    }

    /**
     * Xử lý sự kiện khi người dùng chọn từ ngôn ngữ.
     *
     * @return Giá trị được chọn trong ChoiceBox.
     */
    @FXML
    private String handleChoiceBoxAction() {
        String selectedValue = yourChoiceBox.getValue();
        return selectedValue;
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
     * Chuyển đến Scene 4 khi người dùng nhấn nút.
     *
     * @param event Sự kiện được kích hoạt khi người dùng nhấn nút.
     * @throws Exception Nếu có lỗi trong quá trình chuyển Scene.
     */
    public void switchToScene4(ActionEvent event) throws Exception {
        loadScene("Scene4.fxml");
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
