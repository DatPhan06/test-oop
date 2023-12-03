package com.example.myjavafxapp.model;

import java.util.ArrayList;

/**
 * Class đại diện cho từ điển trong ứng dụng.
 */
public class Dictionary {
    private static ArrayList<Word> words;

    /**
     * Constructor của Dictionary, khởi tạo danh sách từ.
     */
    public Dictionary() {
        words = new ArrayList<>();
    }

    /**
     * Thêm một từ mới vào danh sách từ của từ điển.
     *
     * @param word Từ cần thêm vào từ điển.
     */
    public void addWord(Word word) {
        words.add(word);
    }

    /**
     * Lấy danh sách tất cả các từ trong từ điển.
     *
     * @return ArrayList chứa tất cả các từ trong từ điển.
     */
    public ArrayList<Word> getAllWords() {
        return words;
    }
}
