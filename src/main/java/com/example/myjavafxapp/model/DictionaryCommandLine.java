package com.example.myjavafxapp.model;

import com.example.myjavafxapp.util.DictionaryManagement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Lớp đại diện cho giao diện dòng lệnh của từ điển.
 */
public class DictionaryCommandLine {
    /**
     * Đối tượng quản lý từ điển.
     */
    public static DictionaryManagement dictionaryManagement;

    /**
     * Lấy đối tượng quản lý từ điển.
     *
     * @return Đối tượng quản lý từ điển.
     */
    public static DictionaryManagement getDictionaryManagement() {
        return dictionaryManagement;
    }

    /**
     * Phương thức chạy giao diện dòng lệnh của từ điển.
     */
    public void dictionaryAdvanced() {
        Scanner scanner = new Scanner(System.in);
        dictionaryManagement.insertFromFile("src/main/resources/txt/dictionaryEV.txt");

        while (true) {
            System.out.println("Chào mừng bạn đến với Ứng dụng của tôi!");
            System.out.println("[0] Thoát");
            System.out.println("[1] Thêm từ mới");
            System.out.println("[2] Xóa từ");
            System.out.println("[3] Sửa từ");
            System.out.println("[4] Hiển thị danh sách từ");
            System.out.println("[5] Tra cứu từ");
            System.out.println("[6] Tìm kiếm từ");
            System.out.println("[7] Chơi trò chơi");
            System.out.println("[8] Nhập từ file");
            System.out.println("[9] Xuất ra file");

            System.out.print("Hành động của bạn: ");
            String userAction = scanner.nextLine();

            if (userAction.matches("[0-9]")) {
                int action = Integer.parseInt(userAction);

                switch (action) {
                    case 0:
                        System.out.println("Thoát ứng dụng.");
                        scanner.close();
                        System.exit(0);
                        break;
                    case 1:
                        dictionaryManagement.insertFromCommandLine();
                        System.out.println("Thêm từ thành công!");
                        break;
                    case 2:
                        removeWord();
                        break;
                    case 3:
                        updateWord();
                        break;
                    case 4:
                        showAllWords();
                        break;
                    case 5:
//                        lookupWord();
                        break;
                    case 6:
                        dictionarySearcher();
                        break;
                    case 7:
//                        startGame();
                        break;
                    case 8:
                        importFromFile();
                        break;
                    case 9:
                        //exportToFile();
                        break;
                    default:
                        System.out.println("Hành động không được hỗ trợ. Vui lòng nhập số hợp lệ (0-9).");
                        break;
                }
            } else {
                System.out.println("Hành động không được hỗ trợ. Vui lòng nhập số hợp lệ (0-9).");
            }
        }
    }

    /**
     * Hàm khởi tạo của DictionaryCommandLine.
     */
    public DictionaryCommandLine() {
        dictionaryManagement = new DictionaryManagement();
    }

    /**
     * Phương thức hiển thị toàn bộ từ trong từ điển.
     */
    public void showAllWords() {
        ArrayList<Word> words = dictionaryManagement.getDictionary().getAllWords();
        words.sort(Comparator.comparing(Word::getWordTarget));

        System.out.println("STT | Tiếng Anh | Tiếng Việt");
        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            System.out.println((i + 1) + " | " + word.getWordTarget() + " | " + word.getWordSound() + "|" + word.getWordExplain());
        }
    }

    /**
     * Phương thức xóa từ khỏi từ điển.
     */
    public void removeWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ cần xóa: ");
        String wordToRemove = scanner.nextLine();

        if (DictionaryManagement.removeWord(wordToRemove.trim())) {
            System.out.println("Xóa từ thành công!");
        } else {
            System.out.println("Từ không tồn tại trong từ điển.");
        }
    }

    /**
     * Phương thức sửa từ trong từ điển.
     */
    public void updateWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ cần sửa: ");
        String wordToUpdate = scanner.nextLine();

        System.out.print("Nhập sound mới: ");
        String newSound = scanner.nextLine();

        System.out.print("Nhập định nghĩa mới: ");
        String newDefinition = scanner.nextLine();

        if (DictionaryManagement.updateWord(wordToUpdate.trim(), newSound.trim(), newDefinition.trim())) {
            System.out.println("Sửa từ thành công!");
        } else {
            System.out.println("Từ không tồn tại trong từ điển.");
        }
    }

    /**
     * Phương thức tra cứu từ trong từ điển.
     *
     * @param wordToLookup Từ cần tra cứu.
     * @return Đối tượng Word nếu từ được tìm thấy, ngược lại trả về null.
     */
    public Word lookupWord(String wordToLookup) {
        Word foundWord = dictionaryManagement.dictionaryLookup(wordToLookup.trim());
        if (foundWord != null) {
            System.out.println("Âm thanh là " + foundWord.getWordSound());
            System.out.println("Tiếng Việt là " + foundWord.getWordExplain());
        } else {
            System.out.println("Từ không tồn tại trong từ điển.");
        }
        return foundWord;
    }

    /**
     * Phương thức tìm kiếm từ trong từ điển.
     */
    public void dictionarySearcher() {
        System.out.print("Nhập từ cần tìm kiếm: ");
        Scanner scanner = new Scanner(System.in);
        String searchTerm = scanner.nextLine();
        ArrayList<Word> matchingWords = dictionaryManagement.searchWords(searchTerm.trim());

        if (!matchingWords.isEmpty()) {
            System.out.println("Các từ phù hợp:");
            for (Word word : matchingWords) {
                System.out.println("Tiếng Anh: " + word.getWordTarget() + "Cách đọc: " + word.getWordSound()+ " | Tiếng Việt: " + word.getWordExplain());
            }
        } else {
            System.out.println("Không tìm thấy từ phù hợp.");
        }
    }

    /**
     * Phương thức nhập dữ liệu từ file.
     */
    public void importFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập đường dẫn file cần nhập: ");
        String importFilePath = scanner.nextLine();
        DictionaryManagement.insertFromFile(importFilePath);
    }

    /**
     * Phương thức chơi trò chơi từ điển.
     */
//    public void startGame() {
//        System.out.println("Chào mừng bạn đến với Trò chơi!");
//        System.out.print("Chọn số lượng câu hỏi: ");
//        Scanner scanner = new Scanner(System.in);
//        int numberOfQuestions = scanner.nextInt();
//        scanner.nextLine(); // Để loại bỏ ký tự newline sau khi nhập số
//        WordGame game = new WordGame(dictionaryManagement);
//        game.playGame(numberOfQuestions);
//    }

    /**
     * Chương trình chính.
     *
     * @param args Tham số dòng lệnh.
     */
    public static void main(String[] args) {
        DictionaryCommandLine dictionaryCommandLine = new DictionaryCommandLine();
        dictionaryCommandLine.dictionaryAdvanced();
    }
}
