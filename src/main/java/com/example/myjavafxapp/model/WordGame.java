package com.example.myjavafxapp.model;

import com.example.myjavafxapp.util.DictionaryManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WordGame {
    private final DictionaryManagement dictionaryManagement;
    private int score;
    private Random random;
    private List<Word> wordList;

    private List<String> wrongAnswers;

    // Thêm getter cho thuộc tính đó
    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public WordGame(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
        dictionaryManagement.insertFromFile1("src/main/resources/txt/dictionaryEV.txt");
        this.score = 0;
        this.random = new Random();
        // Initialize the word list with a shuffled list of all words from the dictionary
        this.wordList = dictionaryManagement.getDictionary().getAllWords();
        Collections.shuffle(wordList);
        this.wrongAnswers = new ArrayList<>(); // Khởi tạo danh sách câu trả lời sai
    }

    public Word getRandomWord() {
        if (!wordList.isEmpty()) {
            return wordList.remove(0); // Removes and returns the first element of the list
        }
        return null; // or handle the case where the word list is empty
    }

    public boolean checkAnswer(String wordTarget, String userAnswer) {
        Word word = DictionaryManagement.dictionaryLookup(wordTarget);
        Collections.shuffle(wordList);
        System.out.println(word);
        if (word != null && word.getWordExplain().equalsIgnoreCase(userAnswer)) {
            score++;
            return true;
        }else {
            // Thêm câu trả lời sai vào danh sách
            if (word != null) {
                wrongAnswers.add(wordTarget + " " + word.getWordExplain());
            }
            return false;
        }

    }

    public int getScore() {
        return score;
    }

    // Reset the game to play again
    public void resetGame() {
        score = 0;
        // Reinitialize the word list and shuffle it again
        wordList = dictionaryManagement.getDictionary().getAllWords();
        Collections.shuffle(wordList);
    }

    // Use this method to start the game in your controller
    public void playGame(int numberOfRounds) {
        for (int round = 0; round < numberOfRounds; round++) {
            Word currentWord = getRandomWord();
            if (currentWord == null) {
                System.out.println("No more words available.");
                break;
            }
            // Here you would ask the user for the answer and check it
            // Example: String userAnswer = getUserAnswer();
            // boolean isCorrect = checkAnswer(currentWord.getWordTarget(), userAnswer);
        }
        // At the end of the game, you can show the score
        System.out.println("Your score is: " + getScore());
    }

    public List<String> generateAnswerOptions(Word correctWord) {
        List<String> options = new ArrayList<>();
        options.add(correctWord.getWordExplain()); // Câu trả lời đúng

        // Thêm 3 câu trả lời sai
        while (options.size() < 4) {
            Word randomWord = getRandomWordFromDictionary();
            // Đảm bảo rằng câu trả lời sai không trùng lặp với câu trả lời đúng
            if (!randomWord.getWordExplain().equalsIgnoreCase(correctWord.getWordExplain())
                    && !options.contains(randomWord.getWordExplain())) {
                options.add(randomWord.getWordExplain());
            }
        }

        Collections.shuffle(options); // Xáo trộn các tùy chọn
        return options;
    }
    private Word getRandomWordFromDictionary() {
        // Phương thức này trả về một từ ngẫu nhiên từ danh sách từ vựng
        // Đảm bảo rằng phương thức này không trả về null
        int randomIndex = random.nextInt(wordList.size());
        return wordList.get(randomIndex);
    }

}