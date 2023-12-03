package com.example.myjavafxapp.model;

import java.util.Objects;

/**
 * Đối tượng đại diện cho một từ trong từ điển.
 */
public class Word {
    private String wordTarget;
    private String wordSound;
    private String wordExplain;

    /**
     * Hàm khởi tạo cho việc tạo một đối tượng Word chỉ với từ và nghĩa.
     *
     * @param wordTarget Từ cần đại diện.
     * @param wordExplain Nghĩa của từ.
     */
    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    /**
     * Hàm khởi tạo cho việc tạo một đối tượng Word với cả âm thanh.
     *
     * @param wordTarget Từ cần đại diện.
     * @param wordExplain Nghĩa của từ.
     * @param wordSound   Âm thanh của từ.
     */
    public Word(String wordTarget, String wordExplain, String wordSound) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.wordSound = wordSound;
    }

    /**
     * Hàm khởi tạo mặc định, tạo một đối tượng Word không có thông tin.
     */
    public Word() {
        this.wordTarget = "";
        this.wordExplain = "";
        this.wordSound = "";
    }

    /**
     * Lấy từ cần đại diện.
     *
     * @return Từ cần đại diện.
     */
    public String getWordTarget() {
        return wordTarget;
    }

    /**
     * Thiết lập từ cần đại diện.
     *
     * @param wordTarget Từ cần đại diện.
     */
    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    /**
     * Lấy nghĩa của từ.
     *
     * @return Nghĩa của từ.
     */
    public String getWordExplain() {
        return wordExplain;
    }

    /**
     * Thiết lập nghĩa của từ.
     *
     * @param wordExplain Nghĩa của từ.
     */
    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    /**
     * Lấy thông tin về âm thanh của từ.
     *
     * @return Âm thanh của từ.
     */
    public String getWordSound() {
        return wordSound;
    }

    /**
     * Thiết lập thông tin về âm thanh của từ.
     *
     * @param wordSound Âm thanh của từ.
     */
    public void setWordSound(String wordSound) {
        this.wordSound = wordSound;
    }

    /**
     * So sánh hai đối tượng Word dựa trên từ cần đại diện.
     *
     * @param o Đối tượng Word cần so sánh.
     * @return true nếu từ cần đại diện giống nhau, ngược lại trả về false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(wordTarget, word.wordTarget);
    }

    /**
     * Hashcode dựa trên từ cần đại diện.
     *
     * @return Giá trị hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(wordTarget);
    }

    /**
     * Chuyển đối tượng Word thành chuỗi.
     *
     * @return Chuỗi biểu diễn đối tượng Word.
     */
    @Override
    public String toString() {
        return wordTarget + '\n' + wordSound + '\n' + wordExplain + '\n';
    }
}
