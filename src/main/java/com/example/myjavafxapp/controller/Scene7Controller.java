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

import java.util.Objects;
import java.util.Optional;

/**
 * Controller cho Scene 7 của ứng dụng JavaFX.
 */
public class Scene7Controller extends DictionaryController {
    @FXML
    private Stage primaryStage;

    @FXML
    private Alert SearchHelp; // Reference to the defined Alert

    @FXML
    private void SearchHelpShow() throws Exception {
        SearchHelp.setTitle("Search Help!");
        SearchHelp.setHeaderText("Bạn hãy nhập từ muốn tra vào mục searchField và sau đó ấn nút Search! \n "
                + "*Chúng tôi đã tích hợp phần Speech(phát âm từ) ngay trong nút Search*\n\n"
                + "Chúng tôi đã tích hợp 3 tính năng Add, Delete, Change để cải tiến từ điển.\n"
                + "Đối với phần từ điển Anh - Việt, bạn hãy nhập đủ thông tin vào 3 ô wordTargetArea, wordSoundArea, wordExplainArea.\n"
                + "Đối với phần từ điển Anh - Anh, bạn hãy ấn Search khi searchField trống để hiện ra form cần nhập vào, sau đó hãy \n nhập theo form cho trước sau đó lựa chọn nút của bạn.");
        SearchHelp.setContentText("Bạn có muốn chuyển sang Search không?");


        // Show the alert and wait for user interaction
        Optional<ButtonType> result = SearchHelp.showAndWait();

        // Process the result
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("User clicked OK");
            loadScene("Scene2.fxml");

            // Do something when OK is clicked
        } else {
            System.out.println("User clicked Cancel or closed the dialog");
            // Do something when Cancel or closed
        }
    }

    @FXML
    private void TranslateHelpShow() throws Exception {
        SearchHelp.setTitle("Translate Help");
        SearchHelp.setHeaderText("Chúng tôi giúp bạn có thể dịch tự động từ bất kì ngôn ngữ nào ở trong phần inputTextArea.\n"
                + "Sau đó chọn ngôn ngữ kết quả mà bạn muốn dịch thành ở trong ChoiceBox và ấn nút Translate.\n"
                + "Kết quả sẽ được hiển thị ra ở resultTextArea, khi đó bạn chỉ cần ấn Speech và chúng tôi sẽ giúp bạn đọc toàn bộ văn bản.");
        SearchHelp.setContentText("Bạn có muốn chuyển sang Translate không?");


        // Show the alert and wait for user interaction
        Optional<ButtonType> result = SearchHelp.showAndWait();

        // Process the result
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("User clicked OK");
            loadScene("Scene3.fxml");

            // Do something when OK is clicked
        } else {
            System.out.println("User clicked Cancel or closed the dialog");
            // Do something when Cancel or closed
        }
    }

    @FXML
    private void GameHelpShow() throws Exception {
        SearchHelp.setTitle("Game Help");
        SearchHelp.setHeaderText("Chúng tôi có 50s đếm ngược, nhiệm vụ của bạn là hãy nhanh tay nhanh mắt đọc từ hiển\n"
        +"thị trên màn hình và chọn nhanh 1 trong 4 đáp án để chọn ra đúng nghĩa của từ đã cho.\n"
        + "Mỗi lần trả lời đúng bạn sẽ được cộng 1 điểm.\n"
        +"Sau khi hoàn thành trò chơi tôi sẽ hiện ra điểm số của bạn và 1 bảng danh sách những câu bạn đã trả lời sai \n để bạn có thể học lại" +
                " những từ đó 1 lần nữa.");
        SearchHelp.setContentText("Bạn có muốn chuyển sang Game không?");

        // Show the alert and wait for user interaction
        Optional<ButtonType> result = SearchHelp.showAndWait();

        // Process the result
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("User clicked OK");
            loadScene("Scene4.fxml");
            // Do something when OK is clicked
        } else {
            System.out.println("User clicked Cancel or closed the dialog");
            // Do something when Cancel or closed
        }
    }

    @FXML
    private void GrammarHelpShow() throws Exception {
        SearchHelp.setTitle("Grammar Help");
        SearchHelp.setHeaderText("Chúng tôi cung cấp một số ngữ pháp tiếng anh quan trọng.");
        SearchHelp.setContentText("Bạn có muốn chuyển sang Grammar không?");

        // Show the alert and wait for user interaction
        Optional<ButtonType> result = SearchHelp.showAndWait();

        // Process the result
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("User clicked OK");
            loadScene("Scene5.fxml");
            // Do something when OK is clicked
        } else {
            System.out.println("User clicked Cancel or closed the dialog");
            // Do something when Cancel or closed
        }
    }

    @FXML
    private void VocabularyHelpShow() throws Exception {
        SearchHelp.setTitle("Vocabulary Help");
        SearchHelp.setHeaderText("Chúng tôi cung cấp một số từ vựng tiếng anh phổ biến nhất trong các lĩnh vực.");
        SearchHelp.setContentText("Bạn có muốn chuyển sang Vocabulary không?");

        // Show the alert and wait for user interaction
        Optional<ButtonType> result = SearchHelp.showAndWait();

        // Process the result
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("User clicked OK");
            loadScene("Scene6.fxml");
            // Do something when OK is clicked
        } else {
            System.out.println("User clicked Cancel or closed the dialog");
            // Do something when Cancel or closed
        }
    }

    /**
     * Thiết lập primaryStage từ bên ngoài.
     *
     * @param primaryStage Stage chính của ứng dụng.
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
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
