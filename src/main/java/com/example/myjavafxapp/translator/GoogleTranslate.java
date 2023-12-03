package com.example.myjavafxapp.translator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.myjavafxapp.translator.CreateToken.createToken;

/**
 * Lớp GoogleTranslate cung cấp chức năng dịch văn bản sử dụng dịch vụ Google Translate.
 */
public final class GoogleTranslate {

    private static final String GOOGLE_TRANSLATE_URL = "https://translate.googleapis.com/translate_a/single";

    private GoogleTranslate() {
    }

    /**
     * Tạo URL để thực hiện dịch văn bản từ ngôn ngữ nguồn sang ngôn ngữ đích.
     *
     * @param sourceLanguage Mã ngôn ngữ nguồn.
     * @param targetLanguage Mã ngôn ngữ đích.
     * @param text           Văn bản cần dịch.
     * @return URL được tạo.
     * @throws UnsupportedEncodingException Nếu có vấn đề với việc mã hóa URL.
     */
    static String generateURL(String sourceLanguage, String targetLanguage, String text) throws UnsupportedEncodingException {
        String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8); //Encode
        String url = GOOGLE_TRANSLATE_URL +
                "?client=webapp" + "&hl=en" + "&sl=" + sourceLanguage + "&tl=" + targetLanguage
                + "&q=" + encodedText + "&multiples=1" + "&otf=0" + "&pc=0" + "&trs=1" + "&sell=0"
                + "&tsl=0" + "&kc=1" + "&dt=t" + "&ie=UTF-8" + "&oe=UTF-8" + "&tk=" + createToken(text);
        return url;
    }

}
