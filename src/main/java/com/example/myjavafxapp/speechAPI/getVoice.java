package com.example.myjavafxapp.speechAPI;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Lớp getVoice cung cấp phương thức để tải về âm thanh từ API tổng hợp giọng nói của Google.
 */
public class getVoice {
    // Đường dẫn của Google Speech API
    private static final String GOOGLE_SYNTHESISER_URL = "https://www.google.com/speech-api/v2/synthesize?enc=mpeg&client=chromium";
    private final String key; // Khóa API của bạn
    private String language = "vi"; // Ngôn ngữ mặc định là tiếng Việt

    /**
     * Khởi tạo một đối tượng getVoice với khóa API được cung cấp.
     *
     * @param key Khóa API của Google Speech API.
     */
    public getVoice(String key) {
        this.key = key;
    }

    /**
     * Đặt ngôn ngữ cho tổng hợp giọng nói.
     *
     * @param language Mã ngôn ngữ (ví dụ: "vi" cho tiếng Việt).
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Lấy luồng đầu vào chứa âm thanh từ văn bản tổng hợp.
     *
     * @param synthText Văn bản cần tổng hợp thành giọng nói.
     * @return InputStream chứa dữ liệu âm thanh.
     * @throws IOException Nếu có lỗi xảy ra trong quá trình kết nối hoặc đọc dữ liệu.
     */
    public InputStream getVoiceAudio(String synthText) throws IOException {
        // Encode văn bản để sử dụng trong URL
        String encoded = URLEncoder.encode(synthText, StandardCharsets.UTF_8);

        // Thiết lập các thông số như tốc độ và pitch
        double pitch = 1.0;
        double speed = 1.0;
        String sb = GOOGLE_SYNTHESISER_URL + "&key=" + key + "&text=" + encoded +
                "&lang=" + language + "&speed=" + speed / 2.0 +
                "&pitch=" + pitch / 2.0;

        // Tạo URL và mở kết nối
        URL url = new URL(sb);
        URLConnection urlConn = url.openConnection();
        urlConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0) Gecko/20100101 Firefox/4.0");

        // Trả về luồng đầu vào
        return urlConn.getInputStream();
    }
}
