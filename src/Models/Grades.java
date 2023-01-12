package Models;

import java.sql.*;
import java.util.ArrayList;

import Database.*;

public class Grades {
    static DatabaseConnection db;
    Connection conn;
    private int gradeId;
    private float grade;
    private int studentId;
    private int examId;

    public Grades() {
        db = new DatabaseConnection();
        conn = db.getDatabase();
    }

    public Grades(int gradeId, float grade, int studentId, int examId){
        this.gradeId = gradeId;
        this.grade = grade;
        this.studentId = studentId;
        this.examId = examId;
    }

    public int getExamId() {
        return this.examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getStudentId() {
        return this.studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public float getGrade() {
        return this.grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public ArrayList<Grades> getAllGrades() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Note");

        ArrayList<Grades> data = new ArrayList<>();

        while (rs.next()) {
            Grades ex = new Grades(
                    rs.getInt("GradeId"),
                    rs.getFloat("Grade"),
                    rs.getInt("StudentId"),
                    rs.getInt("ExamId")
            );
            data.add(ex);
        }

        return data;
    }
    public void insertGrade() throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Note (Grade, StudentId, ExamId) VALUES (?, ?, ?);"
        );
        stmt.setString(1, String.valueOf(this.grade));
        stmt.setString(2, String.valueOf(this.studentId));
        stmt.setString(3, String.valueOf(this.examId));
        stmt.executeUpdate();
    }
}
