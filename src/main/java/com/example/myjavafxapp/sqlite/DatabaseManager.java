package com.example.myjavafxapp.sqlite;

import java.sql.*;

/**
 * Lớp quản lý cơ sở dữ liệu SQLite cho ứng dụng từ điển.
 */
public class DatabaseManager {

    /**
     * Kết nối đến cơ sở dữ liệu SQLite.
     */
    private static final Connection connection = SQLiteConnector.connect();

    /**
     * Hàm tạo của lớp DatabaseManager.
     */
    public DatabaseManager() {
    }

    /**
     * Tạo bảng English trong cơ sở dữ liệu nếu nó chưa tồn tại.
     */
    public static void createTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS English (Id INTEGER PRIMARY KEY, Word varchar(255), Type varchar(255), Meaning varchar(255), Pronunciation varchar(255), Example varchar(255), Synonym varchar(255), Antonyms varchar(255));";
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Chèn dữ liệu mới vào bảng English trong cơ sở dữ liệu.
     *
     * @param word          Từ vựng.
     * @param type          Loại từ.
     * @param meaning       Ý nghĩa.
     * @param pronunciation Phát âm.
     * @param example       Ví dụ.
     * @param synonym       Từ đồng nghĩa.
     * @param antonyms      Từ trái nghĩa.
     */
    public static void insertData(String word, String type, String meaning, String pronunciation, String example, String synonym, String antonyms) {
        try {
            String sql = "INSERT INTO English (Word, Type, Meaning, Pronunciation, Example, Synonym, Antonyms) VALUES (?, ?, ?, ?, ?, ?, ?);";
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, word);
                statement.setString(2, type);
                statement.setString(3, meaning);
                statement.setString(4, pronunciation);
                statement.setString(5, example);
                statement.setString(6, synonym);
                statement.setString(7, antonyms);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Xóa một từ vựng khỏi bảng English của cơ sở dữ liệu.
     *
     * @param word Từ vựng cần xóa.
     */
    public static void deleteWord(String word) {
        try {
            String sql = "DELETE FROM English WHERE Word = ?";
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, word);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Cập nhật dữ liệu cho từ vựng có sẵn trong bảng English của cơ sở dữ liệu.
     *
     * @param word          Từ vựng cần cập nhật.
     * @param type          Loại từ mới.
     * @param meaning       Ý nghĩa mới.
     * @param pronunciation Phát âm mới.
     * @param example       Ví dụ mới.
     * @param synonym       Từ đồng nghĩa mới.
     * @param antonyms      Từ trái nghĩa mới.
     */
    public static void updateWord(String word, String type, String meaning, String pronunciation, String example, String synonym, String antonyms) {
        try {
            String sql = "UPDATE English SET Type = ?, Meaning = ?, Pronunciation = ?, Example = ?, Synonym = ?, Antonyms = ? WHERE Word = ?";
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, type);
                statement.setString(2, meaning);
                statement.setString(3, pronunciation);
                statement.setString(4, example);
                statement.setString(5, synonym);
                statement.setString(6, antonyms);
                statement.setString(7, word);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Tìm kiếm từ trong bảng English và trả về thông tin chi tiết nếu tìm thấy.
     *
     * @param wordSearch Từ cần tìm kiếm.
     * @return Thông tin chi tiết về từ nếu tìm thấy, ngược lại trả về null.
     */
    public static String searchWord(String wordSearch) {
        try {
            StringBuilder resultBuilder = new StringBuilder();
            String sql = "SELECT * FROM English WHERE Word = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                wordSearch = wordSearch.trim().toLowerCase();
                statement.setString(1, wordSearch); // Sử dụng tham số để tránh SQL injection

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {

                        String word = resultSet.getString("Word");
                        String type = resultSet.getString("Type");
                        String meaning = resultSet.getString("Meaning");
                        String pronunciation = resultSet.getString("Pronunciation");
                        String example = resultSet.getString("Example");
                        String synonym = resultSet.getString("Synonym");
                        String antonyms = resultSet.getString("Antonyms");

                        // Xây dựng nội dung cho StringBuilder
                        resultBuilder.append("- Word: ").append(word).append("\n");
                        resultBuilder.append("- Type: ").append(type).append("\n");
                        resultBuilder.append("- Meaning: ").append(meaning).append("\n");
                        resultBuilder.append("- Pronunciation: ").append(pronunciation).append("\n");
                        resultBuilder.append("- Example: ").append(example).append("\n");
                        resultBuilder.append("- Synonym: ").append(synonym).append("\n");
                        resultBuilder.append("- Antonyms: ").append(antonyms).append("\n");
                        return resultBuilder.toString();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
