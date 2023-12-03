package com.example.myjavafxapp.speechAPI;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.IOException;

/**
 * Lớp SpeechAPI cung cấp phương thức để tổng hợp giọng nói và phát âm thanh.
 */
public class SpeechAPI {

    // Đối tượng getVoice để tạo và tải về giọng nói
    private getVoice middle = new getVoice("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

    /**
     * Phát âm thanh cho văn bản được chỉ định và ngôn ngữ xác định.
     *
     * @param lang Ngôn ngữ của văn bản (ví dụ: "vi" cho tiếng Việt).
     * @param text Văn bản cần phát âm.
     */
    public void speak(String lang, String text) {
        // Đặt ngôn ngữ cho đối tượng getVoice
        middle.setLanguage(lang);
        System.out.println(text);

        // Tạo một luồng để thực hiện việc phát âm thanh
        Thread thread = new Thread(() -> {
            AdvancedPlayer player = null;
            try {
                // Tạo đối tượng AdvancedPlayer với dữ liệu âm thanh từ getVoice
                player = new AdvancedPlayer(middle.getVoiceAudio(text));
            } catch (JavaLayerException | IOException e) {
                throw new RuntimeException(e);
            }

            try {
                // Phát âm thanh
                player.play();
            } catch (JavaLayerException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Successfully got back synthesizer data");
        });

        // Không đặt luồng làm nền
        thread.setDaemon(false);

        // Khởi chạy luồng
        thread.start();
    }
}
