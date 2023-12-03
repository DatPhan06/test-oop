package com.example.myjavafxapp.translator;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * Lớp UrlToText cung cấp phương thức để đọc dữ liệu từ một URL và chuyển đổi nó thành văn bản.
 */
public class UrlToText {

    /**
     * Đọc dữ liệu từ một URL và chuyển đổi thành văn bản.
     *
     * @param url URL cần đọc dữ liệu.
     * @return Văn bản sau khi chuyển đổi từ dữ liệu của URL.
     * @throws IOException Nếu có lỗi xảy ra trong quá trình kết nối hoặc đọc dữ liệu từ URL.
     */
    static String urlToText(URL url) throws IOException {
        URLConnection urlConn = url.openConnection(); // Mở kết nối
        // Thêm header để giả mạo User Agent, điều này là cần thiết để tránh bị từ chối yêu cầu từ Google
        urlConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0) Gecko/20100101 Firefox/4.0");
        Reader r = new java.io.InputStreamReader(urlConn.getInputStream(), StandardCharsets.UTF_8); // Lấy dữ liệu và chuyển đổi thành chuỗi
        StringBuilder buf = new StringBuilder();
        while (true) {
            int ch = r.read();
            if (ch < 0)
                break;
            buf.append((char) ch);
        }
        return buf.toString();
    }
}
