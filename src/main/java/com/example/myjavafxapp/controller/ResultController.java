package com.example.myjavafxapp.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

/**
 * Controller cho cửa sổ hiển thị kết quả của trò chơi.
 */
public class ResultController extends DictionaryController{

    /**
     * Nhãn hiển thị điểm số.
     */
    @FXML
    private Label scoreLabel;

    /**
     * ListView hiển thị các câu trả lời sai.
     */
    @FXML
    private ListView<String> wrongAnswersListView;

    // Các biến và phương thức khác...

    /**
     * Thiết lập điểm số hiển thị trên nhãn.
     *
     * @param score Điểm số của người chơi.
     */
    public void setScore(int score) {
        scoreLabel.setText("Điểm số của bạn: " + score);
    }

    /**
     * Thiết lập danh sách các câu trả lời sai hiển thị trên ListView.
     *
     * @param wrongAnswers Danh sách các câu trả lời sai.
     */
    public void setWrongAnswers(List<String> wrongAnswers) {
        wrongAnswersListView.getItems().addAll(wrongAnswers);
    }

    /**
     * Xử lý sự kiện đóng cửa sổ kết quả.
     *
     * @param actionEvent Sự kiện được kích hoạt khi người dùng nhấn nút đóng.
     */
    public void closeResult(ActionEvent actionEvent) {
        // Thêm logic xử lý khi người dùng muốn đóng cửa sổ kết quả.
        Platform.exit();
    }

    /**
     * Phương thức trừu tượng để thiết lập primaryStage từ bên ngoài.
     *
     * @param primaryStage Stage chính của ứng dụng.
     */
    @Override
    public void setPrimaryStage(Stage primaryStage) {

    }
}
