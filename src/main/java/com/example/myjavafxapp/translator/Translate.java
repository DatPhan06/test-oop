package com.example.myjavafxapp.translator;

import org.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import static com.example.myjavafxapp.translator.GoogleTranslate.generateURL;
import static com.example.myjavafxapp.translator.UrlToText.urlToText;

/**
 * Lớp Translate cung cấp phương thức để thực hiện dịch văn bản sử dụng dịch vụ Google Translate.
 */
public class Translate {

    /**
     * Dịch văn bản từ ngôn ngữ nguồn tự động đến ngôn ngữ của hệ thống.
     *
     * @param text Văn bản cần dịch.
     * @return Văn bản sau khi dịch.
     * @throws IOException Nếu có lỗi xảy ra trong quá trình thực hiện HTTP request hoặc đọc dữ liệu.
     */
    public static String translate(String text) throws IOException {
        return translate(Locale.getDefault().getLanguage(), text);
    }

    /**
     * Dịch văn bản từ ngôn ngữ nguồn tự động đến ngôn ngữ đích được chỉ định.
     *
     * @param targetLanguage Ngôn ngữ đích (ví dụ: "en" cho tiếng Anh).
     * @param text Văn bản cần dịch.
     * @return Văn bản sau khi dịch.
     * @throws IOException Nếu có lỗi xảy ra trong quá trình thực hiện HTTP request hoặc đọc dữ liệu.
     */
    public static String translate(String targetLanguage, String text) throws IOException {
        return translate("auto", targetLanguage, text);
    }

    /**
     * Dịch văn bản từ ngôn ngữ nguồn đến ngôn ngữ đích được chỉ định.
     *
     * @param sourceLanguage Ngôn ngữ nguồn (ví dụ: "en" cho tiếng Anh).
     * @param targetLanguage Ngôn ngữ đích (ví dụ: "vi" cho tiếng Việt).
     * @param text Văn bản cần dịch.
     * @return Văn bản sau khi dịch.
     * @throws IOException Nếu có lỗi xảy ra trong quá trình thực hiện HTTP request hoặc đọc dữ liệu.
     */
    public static String translate(String sourceLanguage, String targetLanguage, String text) throws IOException {
        String urlText = generateURL(sourceLanguage, targetLanguage, text);
        String rawData = urlToText(new URL(urlText)); // Lấy văn bản từ Google
        String[] raw = rawData.split("\""); // Phân tích cú pháp JSON
        if (raw.length < 2) {
            return null;
        }
        JSONArray arr = new JSONArray(rawData);
        JSONArray arr1 = arr.getJSONArray(0);
        StringBuilder trans = new StringBuilder();
        for (int i = 0; i < arr1.length(); i++) {
            JSONArray arr2 = arr1.getJSONArray(i);
            trans.append(arr2.get(0).toString());
        }
        return trans.toString(); // Trả về văn bản đã dịch
    }
}
