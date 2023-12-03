package com.example.myjavafxapp.controller;

import com.example.myjavafxapp.model.Word;
import com.example.myjavafxapp.speechAPI.SpeechAPI;
import com.example.myjavafxapp.sqlite.DatabaseManager;
import com.example.myjavafxapp.util.DictionaryManagement;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class Scene2Controller extends DictionaryController implements Initializable {

    /**
     * Trường TextField để nhập từ khóa tìm kiếm từ điển.
     */
    @FXML
    private TextField searchField;

    /**
     * Trường TextField để hiển thị âm thanh của từ.
     */
    @FXML
    private TextField wordSoundArea;

    /**
     * Trường TextField để hiển thị từ mục tiêu (target word).
     */
    @FXML
    private TextField wordTargetArea;

    /**
     * Trường TextArea để hiển thị giải nghĩa của từ.
     */
    @FXML
    private TextArea wordExplainArea;

    /**
     * Trường TextArea để hiển thị kết quả tìm kiếm từ điển hoặc thông tin chi tiết của từ.
     */
    @FXML
    private TextArea resultArea;

    /**
     * Trường Button để kích hoạt tìm kiếm từ khóa.
     */
    @FXML
    private Button searchButton;

    /**
     * Trường Stage chính của ứng dụng.
     */
    @FXML
    private Stage primaryStage;

    /**
     * Trường Alert để hiển thị cảnh báo khi thêm từ mới vào từ điển.
     */
    @FXML
    private Alert AddcustomAlert;

    /**
     * Trường Alert để hiển thị cảnh báo khi thêm từ mới vào cơ sở dữ liệu từ điển.
     */
    @FXML
    private Alert AddcustomAlert1;

    /**
     * Trường Alert để hiển thị cảnh báo khi thay đổi thông tin từ trong từ điển.
     */
    @FXML
    private Alert ChangecustomAlert;

    /**
     * Trường Alert để hiển thị cảnh báo khi thay đổi thông tin từ trong cơ sở dữ liệu từ điển.
     */
    @FXML
    private Alert ChangecustomAlert1;

    /**
     * Trường Alert để hiển thị cảnh báo khi xóa từ khỏi từ điển.
     */
    @FXML
    private Alert DeletecustomAlert;

    /**
     * Trường Alert để hiển thị cảnh báo khi xóa từ khỏi cơ sở dữ liệu từ điển.
     */
    @FXML
    private Alert DeletecustomAlert1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Mặc định nội dung cho resultArea
        resultArea.setText("Word: \n" +
                "Type: \n" +
                "Meaning: \n" +
                "Pronunciation: \n" +
                "Example: \n" +
                "Synonym: \n" +
                "Antonyms: ");
    }

    /**
     * Hiển thị cảnh báo khi người dùng muốn sửa từ trong từ điển Anh - Việt.
     */
    @FXML
    private void ChangeshowAlert() {
        ChangecustomAlert.setTitle("Change Word");
        ChangecustomAlert.setHeaderText("Bạn có chắc chắn muốn sửa từ " + wordTargetArea.getText()
                + " trong từ điển Anh - Việt không?");

        Optional<ButtonType> result = ChangecustomAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("User clicked OK");
            if (Objects.equals(wordTargetArea.getText(), "")) {
                System.out.println("Please insert Word!");
            } else if (Objects.equals(wordSoundArea.getText(), "")) {
                System.out.println("Please insert Word Sound!");
            } else if (Objects.equals(wordExplainArea.getText(), "")) {
                System.out.println("Please insert Word Explain!");
            } else {
                changeNewWordToDictionary(wordTargetArea.getText(),
                        wordSoundArea.getText(), wordExplainArea.getText());
            }
        } else {
            System.out.println("User clicked Cancel or closed the dialog");
        }
    }

    /**
     * Hiển thị cảnh báo khi người dùng muốn sửa từ trong cơ sở dữ liệu từ điển Anh - Anh.
     */
    @FXML
    private void ChangeshowAlert1() {
        ChangecustomAlert1.setTitle("Change Word");
        String[] lines = resultArea.getText().split("\n");
        String Word = "";
        String Type = "";
        String Meaning = "";
        String Pronunciation = "";
        String Example = "";
        String Synonym = "";
        String Antonyms = "";
        for (String line : lines) {
            String[] parts = line.split(":", 2);
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (key) {
                    case "- Word":
                        Word = value;
                        break;
                    case "- Type":
                        Type = value;
                        break;
                    case "- Meaning":
                        Meaning = value;
                        break;
                    case "- Pronunciation":
                        Pronunciation = value;
                        break;
                    case "- Example":
                        Example = value;
                        break;
                    case "- Synonym":
                        Synonym = value;
                        break;
                    case "- Antonyms":
                        Antonyms = value;
                        break;
                }
            }
        }
        ChangecustomAlert1.setHeaderText("Bạn có chắc chắn muốn sửa từ " + Word
                + " trong cơ sở dữ liệu từ điển Anh - Anh không?");
        Optional<ButtonType> result1 = ChangecustomAlert1.showAndWait();

        if (result1.isPresent() && result1.get() == ButtonType.OK) {
            System.out.println("User clicked OK");
            if (Objects.equals(resultArea.getText(), "")) {
                System.out.println("Please insert Word!");
            } else {
                changeNewWordToDataBase(Word, Type, Meaning,
                        Pronunciation, Example, Synonym, Antonyms);
            }
        } else {
            System.out.println("User clicked Cancel or closed the dialog");
        }
    }

    /**
     * Hiển thị cảnh báo khi người dùng muốn xóa từ khỏi từ điển Anh - Việt.
     */
    @FXML
    private void DeleteshowAlert() {
        DeletecustomAlert.setTitle("Delete Word");
        DeletecustomAlert.setHeaderText("Bạn có chắc chắn muốn xóa từ "
                + wordTargetArea.getText() + " trong từ điển Anh - Việt không?");

        Optional<ButtonType> result = DeletecustomAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("User clicked OK");
            if (Objects.equals(wordTargetArea.getText(), "")) {
                System.out.println("Please insert Word!");
            } else {
                deleteNewWordFromDictionary(wordTargetArea.getText());
            }
        } else {
            System.out.println("User clicked Cancel or closed the dialog");
        }
    }

    /**
     * Hiển thị cảnh báo khi người dùng muốn xóa từ khỏi cơ sở dữ liệu từ điển Anh - Anh.
     */
    @FXML
    private void DeleteshowAlert1() {
        DeletecustomAlert1.setTitle("Delete Word");
        String[] lines = resultArea.getText().split("\n");
        String Word = "";

        for (String line : lines) {
            String[] parts = line.split(":", 2);
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();

                if (key.equals("- Word")) {
                    Word = value;
                }
            }
        }
        DeletecustomAlert1.setHeaderText("Bạn có chắc chắn xóa sửa từ " + Word
                + " trong cơ sở dữ liệu từ điển Anh - Anh không?");

        Optional<ButtonType> result1 = DeletecustomAlert1.showAndWait();

        if (result1.isPresent() && result1.get() == ButtonType.OK) {
            System.out.println("User clicked OK");
            if (Objects.equals(resultArea.getText(), "")) {
                System.out.println("Please insert Word!");
            } else {
                deleteNewWordToDataBase(Word);
                System.out.println("Word deleted");
            }
        } else {
            System.out.println("User clicked Cancel or closed the dialog");
        }
    }

    /**
     * Xóa từ khỏi từ điển Anh - Việt.
     */
    public void deleteNewWordFromDictionary(String wordTarget) {
        if (DictionaryManagement.removeWord(wordTarget.trim())) {
            System.out.println("Delete word successfully");
        } else {
            System.out.println("Can't delete word");
        }
    }

    /**
     * Xóa từ khỏi cơ sở dữ liệu từ điển Anh - Anh.
     */
    public void deleteNewWordToDataBase(String Word) {
        try {
            DatabaseManager.deleteWord(Word);
            System.out.println("Delete word successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sửa từ trong từ điển Anh - Việt.
     */
    public void changeNewWordToDictionary(String wordTarget, String wordSound, String wordExplain) {
        if (DictionaryManagement.updateWord(wordTarget, wordSound, wordExplain)) {
            System.out.println("Change word successfully");
        } else {
            System.out.println("Can't change word");
        }
    }

    /**
     * Sửa từ trong cơ sở dữ liệu từ điển Anh - Anh.
     */
    public void changeNewWordToDataBase(String Word, String Type, String Meaning,
                                        String Pronunciation, String Example, String Synonym,
                                        String Antonyms) {
        try {
            DatabaseManager.updateWord(Word, Type, Meaning, Pronunciation, Example, Synonym, Antonyms);
            System.out.println("Change word successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Hiển thị cảnh báo khi người dùng muốn thêm từ vào từ điển Anh - Việt.
     */
    @FXML
    private void AddshowAlert() {
        AddcustomAlert.setTitle("Add Word");
        AddcustomAlert.setHeaderText("Bạn có chắc chắn muốn thêm từ "
                + wordTargetArea.getText() + " vào từ điển Anh - Việt không?");

        Optional<ButtonType> result = AddcustomAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("User clicked OK");
            if (Objects.equals(wordTargetArea.getText(), "")) {
                System.out.println("Please insert Word!");
            } else if (Objects.equals(wordSoundArea.getText(), "")) {
                System.out.println("Please insert Word Sound!");
            } else if (Objects.equals(wordExplainArea.getText(), "")) {
                System.out.println("Please insert Word Explain!");
            } else {
                addNewWordToDictionary(wordTargetArea.getText(), wordSoundArea.getText(), wordExplainArea.getText());
            }
        } else {
            System.out.println("User clicked Cancel or closed the dialog");
        }
    }

    /**
     * Hiển thị cảnh báo khi người dùng muốn thêm từ vào cơ sở dữ liệu từ điển Anh - Anh.
     */
    @FXML
    private void AddshowAlert1() {
        AddcustomAlert1.setTitle("Add Word");
        String[] lines = resultArea.getText().split("\n");

        String Word = "";
        String Type = "";
        String Meaning = "";
        String Pronunciation = "";
        String Example = "";
        String Synonym = "";
        String Antonyms = "";
        for (String line : lines) {
            String[] parts = line.split(":", 2);
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (key) {
                    case "- Word":
                        Word = value;
                        break;
                    case "- Type":
                        Type = value;
                        break;
                    case "- Meaning":
                        Meaning = value;
                        break;
                    case "- Pronunciation":
                        Pronunciation = value;
                        break;
                    case "- Example":
                        Example = value;
                        break;
                    case "- Synonym":
                        Synonym = value;
                        break;
                    case "- Antonyms":
                        Antonyms = value;
                        break;
                }
            }
        }

        AddcustomAlert1.setHeaderText("Bạn có chắc chắn muốn thêm từ " + Word
                + " vào cơ sở dữ liệu từ điển Anh - Anh không?");
        AddcustomAlert1.setContentText("Hãy chắc chắn bạn nhập theo đúng định dạng (mỗi chữ cái 1 dòng): \n " +
                "Word: " + Word + "\n" +
                "Type: " + Type + "\n" +
                "Meaning: " + Meaning + "\n" +
                "Pronunciation: " + Pronunciation + "\n" +
                "Example: " + Example + "\n" +
                "Synonym: " + Synonym + "\n" +
                "Antonyms: " + Antonyms);

        Optional<ButtonType> result1 = AddcustomAlert1.showAndWait();

        if (result1.isPresent() && result1.get() == ButtonType.OK) {
            System.out.println("User clicked OK");
            if (Objects.equals(resultArea.getText(), "")) {
                System.out.println("Please insert Word!");
            } else {
                addNewWordToDataBase(Word, Type, Meaning, Pronunciation, Example, Synonym, Antonyms);
            }
        } else {
            System.out.println("User clicked Cancel or closed the dialog");
        }
    }

    /**
     * Thêm từ vào từ điển Anh - Việt.
     */
    public void addNewWordToDictionary(String wordTarget, String wordSound, String wordExplain) {
        if (DictionaryManagement.addWord(wordTarget, wordSound, wordExplain)) {
            System.out.println("Add word successfully");
        } else {
            System.out.println("Can't add word because word has been added");
        }
    }


    public void addNewWordToDataBase(String Word, String Type, String Meaning, String Pronunciation, String Example, String Synonym, String Antonyms) {
        try {
            DatabaseManager.insertData(Word, Type, Meaning, Pronunciation, Example, Synonym, Antonyms);
            System.out.println("Add word successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // Phương thức để thiết lập primaryStage từ bên ngoài
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void searchButtonAction() {
        // Logic khi nút search được nhấn
        String searchTerm = searchField.getText();
        SpeechAPI speechAPI = new SpeechAPI();
        speechAPI.speak("en", searchTerm);
        try {
            Word result = performSearch(searchTerm);
            String result1 = DatabaseManager.searchWord(searchTerm);


            if (result == null) {
                wordTargetArea.setText("Can't search word.");
                wordSoundArea.setText("");
                wordExplainArea.setText("");
            } else {
                wordTargetArea.setText(result.getWordTarget());
                wordSoundArea.setText(result.getWordSound());
                wordExplainArea.setText(result.getWordExplain());
            }
            if (result1 == null) {
                resultArea.setText("Can't search word.");
            } else {
                resultArea.setText(result1);
            }


        } catch (Exception e) {
            System.out.println(e);
        }


    }

    //
    private Word performSearch(String searchTerm) {
        // Thực hiện tìm kiếm các từ khớp với searchTerm
        Word result = DictionaryManagement.dictionaryLookup(searchTerm);


        // Kiểm tra xem có từ nào khớp không
        if (result != null) {

            // Trả về kết quả tìm kiếm
            return result;
        } else {
            // Trả về thông báo khi từ không được tìm thấy trong từ điển
            return null;
        }
    }


    public void switchToScene1(ActionEvent event) throws Exception {
        loadScene("Scene1.fxml");
    }

    public void switchToScene3(ActionEvent event) throws Exception {
        loadScene("Scene3.fxml");
    }

    public void switchToScene4(ActionEvent event) throws Exception {
        loadScene("Scene4.fxml");
    }

    public void switchToScene5(ActionEvent event) throws Exception {
        loadScene("Scene5.fxml");
    }

    public void switchToScene6(ActionEvent event) throws Exception {
        loadScene("Scene6.fxml");
    }

    public void switchToScene7(ActionEvent event) throws Exception {
        loadScene("Scene7.fxml");
    }

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