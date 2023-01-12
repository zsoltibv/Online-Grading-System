package Models;

import java.sql.*;
import java.util.ArrayList;

import Database.*;

public class Exams {
    static DatabaseConnection db;
    Connection conn;
    private int examId;
    private String examName;

    public Exams() {
        db = new DatabaseConnection();
        conn = db.getDatabase();
    }

    public Exams(int examId, String examName){
        this.examId = examId;
        this.examName = examName;
    }

    public String getExamName() {
        return this.examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getExamId() {
        return this.examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public ArrayList<Exams> getAllExams() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Examene");

        ArrayList<Exams> data = new ArrayList<>();

        while (rs.next()) {
            Exams ex = new Exams(rs.getInt("ExamId"), rs.getString("ExamName"));
            data.add(ex);
        }

        return data;
    }

    public void insertExam() throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Examene (ExamName) VALUES (?);"
        );
        stmt.setString(1, String.valueOf(this.examName));
        stmt.executeUpdate();
    }
}
