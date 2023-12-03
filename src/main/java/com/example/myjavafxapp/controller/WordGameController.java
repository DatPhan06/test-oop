package com.example.myjavafxapp.controller;

import com.example.myjavafxapp.model.Game;
import com.example.myjavafxapp.model.Word;
import com.example.myjavafxapp.model.WordGame;
import com.example.myjavafxapp.util.DictionaryManagement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class WordGameController extends DictionaryController {

    @FXML
    private RadioButton optionA;
    @FXML
    private RadioButton optionB;
    @FXML
    private RadioButton optionC;
    @FXML
    private RadioButton optionD;
    @FXML
    private ToggleGroup answerToggleGroup;

    @FXML
    private Label wordLabel;
    @FXML
    private TextField answerTextField;
    @FXML
    private Button checkButton;
    @FXML
    private Label resultLabel;
    @FXML
    private Label timeLabel;


    private WordGame wordGame;
    private Word currentWord;
    private int timeLeft;
    private Timeline timeline;

    @FXML
    private Button exitButton;

    private Stage primaryStage;


    // Các phương thức và biến khác...

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) throws Exception {
        // Logic để thoát khỏi ứng dụng
//        Platform.exit();
        loadScene("Scene4.fxml");

    }

    private void loadScene(String fxmlFileName) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/myjavafxapp/" + fxmlFileName));
        Parent root = loader.load();

        // Lấy controller của scene mới và truyền primaryStage
        Object controller = loader.getController();
        if (controller instanceof Scene1Controller) {
            ((Scene1Controller) controller).setPrimaryStage(primaryStage);
        } else if (controller instanceof Scene4Controller) {
            ((Scene4Controller) controller).setPrimaryStage(primaryStage);
        }


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

//    public void setDictionaryManagement(DictionaryManagement dictionaryManagement) {
//        if (dictionaryManagement != null) {
//            System.out.println("Đã load file");
//            wordGame = new WordGame(dictionaryManagement);
//            nextWord();
//        } else {
//            System.err.println("Không thể khởi tạo WordGame với DictionaryManagement null ngay từ set");
//        }
//    }

    public void initialize() {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        dictionaryManagement.insertFromFile1("src/main/resources/txt/dictionaryEV.txt");
        wordGame = new WordGame(dictionaryManagement);
        timeLeft = 50; // Đặt thời gian ban đầu là 60 giây
        // Khởi tạo ToggleGroup
        // Liên kết các RadioButton với ToggleGroup
        optionA.setToggleGroup(answerToggleGroup);
        optionB.setToggleGroup(answerToggleGroup);
        optionC.setToggleGroup(answerToggleGroup);
        optionD.setToggleGroup(answerToggleGroup);


        optionA.setText("");
        optionB.setText("");
        optionC.setText("");
        optionD.setText("");
        startTimer();
        nextWord();
    }

    private void startTimer() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            timeLeft--;
            updateTimerLabel();
            if (timeLeft <= 0) {
                endGame();
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private void endGame() {
        timeline.stop();
        resultLabel.setText("Hết thời gian!");
        checkButton.setDisable(true);

        // Thu thập thông tin điểm số và câu trả lời sai
        int score = wordGame.getScore();
        List<String> wrongAnswers = wordGame.getWrongAnswers();

        // Tạo FXMLLoader để nạp file FXML kết quả
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/myjavafxapp/result.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Lấy controller của cửa sổ kết quả
        ResultController resultController = loader.getController();

        // Truyền thông tin điểm số và câu trả lời sai vào controller của cửa sổ kết quả
        resultController.setScore(score);
        resultController.setWrongAnswers(wrongAnswers);

        // Tạo và hiển thị cửa sổ kết quả
        Stage resultStage = new Stage();
        resultStage.setTitle("Kết quả trò chơi");
        resultStage.setScene(new Scene(root));
        resultStage.show();

    }

    private void updateTimerLabel() {

        timeLabel.setText("Thời gian còn lại: " + timeLeft + " giây");
    }


//    private void initialize() {
//        // Khởi tạo game với DictionaryManagement
////        wordGame = new WordGame(DictionaryCommandLine.getDictionaryManagement());
////        nextWord();
//        DictionaryManagement dictionaryManagement = DictionaryCommandLine.getDictionaryManagement();
////        dictionaryManagement.insertFromFile("src/main/resources/txt/dictionaryEVplus.txt");
//        if (dictionaryManagement != null) {
//            System.out.println("Đã load file");
//            wordGame = new WordGame(dictionaryManagement);
//            nextWord();
//        } else {
//            // Xử lý trường hợp DictionaryManagement trả về null
//            System.err.println("Không thể khởi tạo WordGame với DictionaryManagement null.");
//        }
//    }

    @FXML
    private void handleCheckButtonAction() {
        System.out.println("Người dùng  nhấn kiểm tra");
        System.out.println(wordGame);
        if (timeLeft <= 0) {
            // Thời gian đã hết
            resultLabel.setText("Hết thời gian!");
            return;
        }
        if (currentWord == null) {
            resultLabel.setText("Hết từ!");
            endGame();
            return;
        }

        RadioButton selectedRadioButton = (RadioButton) answerToggleGroup.getSelectedToggle();
        System.out.println(selectedRadioButton);
        System.out.println(answerToggleGroup.getToggles());
        if (selectedRadioButton != null) {
            String userAnswer = selectedRadioButton.getText();
            boolean isCorrect = wordGame.checkAnswer(currentWord.getWordTarget(), userAnswer);
            if (isCorrect) {
                System.out.println("Đáp án đúng");
                resultLabel.setText("Đúng!");
            } else {
                System.out.println("Đáp án sai");
                resultLabel.setText("Sai! Đáp án đúng là: " + currentWord.getWordExplain());
            }
            updateScore();
            nextWord();
        } else {
            resultLabel.setText("Vui lòng chọn một đáp án!");
            System.out.println("Chưa chọn đáp án");
        }
    }

    //    private void nextWord() {
//        currentWord = wordGame.getRandomWord();
//        if (currentWord != null) {
//            wordLabel.setText(currentWord.getWordTarget());
//        } else {
//            wordLabel.setText("Hết từ!");
//            // Có thể thêm logic để kết thúc trò chơi hoặc bắt đầu lại
//            endGame();
//        }
//        answerTextField.clear();
//    }
    private void nextWord() {
        currentWord = wordGame.getRandomWord();
        if (currentWord != null) {
            wordLabel.setText(currentWord.getWordTarget());

            List<String> answerOptions = wordGame.generateAnswerOptions(currentWord);
            // Đảm bảo rằng bạn có đủ tùy chọn để đặt vào các RadioButton
            optionA.setText(answerOptions.get(0));
            optionB.setText(answerOptions.get(1));
            optionC.setText(answerOptions.get(2));
            optionD.setText(answerOptions.get(3));
        } else {
            wordLabel.setText("Hết từ!");
            endGame();
        }
    }

    private void updateScore() {
        // Cập nhật điểm số vào resultLabel hoặc một Label khác dành cho điểm
        resultLabel.setText("Điểm: " + wordGame.getScore());

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