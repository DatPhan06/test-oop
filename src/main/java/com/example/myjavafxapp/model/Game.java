package com.example.myjavafxapp.model;

import com.example.myjavafxapp.util.DictionaryManagement;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private final ArrayList<Word> words;
    private int score;

    public Game(DictionaryManagement dictionaryManagement) {
        this.words = dictionaryManagement.getDictionary().getAllWords();
        this.score = 0;
    }

    public void playGame(int numberOfQuestions) {
        for (int i = 0; i < numberOfQuestions; i++) {
            int randomIndex = new Random().nextInt(words.size());
            Word randomWord = words.get(randomIndex);

            String[] answerOptions = generateAnswerOptions(randomWord);

            System.out.println("Translate the following word to Vietnamese: " + randomWord.getWordTarget());
            for (int j = 0; j < answerOptions.length; j++) {
                System.out.println((j + 1) + ". " + answerOptions[j]);
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Your answer (enter the number): ");
            int userChoice = scanner.nextInt();

            if (isValidChoice(userChoice)) {
                String userAnswer = answerOptions[userChoice - 1];
                if (userAnswer.equalsIgnoreCase(randomWord.getWordExplain())) {
                    System.out.println("Correct! Good job!");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer is: " + randomWord.getWordExplain());
                }
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and " + answerOptions.length);
                i--; // Decrement i to repeat the current question
            }
        }

        System.out.println("Game over! Your final score: " + score);
    }


    private String[] generateAnswerOptions(Word correctWord) {
        String[] answerOptions = new String[4];
        int correctAnswerIndex = new Random().nextInt(answerOptions.length);
        answerOptions[correctAnswerIndex] = correctWord.getWordExplain();

        for (int i = 0; i < answerOptions.length; i++) {
            if (i != correctAnswerIndex) {
                int randomWordIndex = new Random().nextInt(words.size());
                answerOptions[i] = words.get(randomWordIndex).getWordExplain();
            }
        }

        return answerOptions;
    }

    private boolean isValidChoice(int choice) {
        return choice > 0 && choice <= 4;
    }

    public int getScore() {
        return score;
    }
}
