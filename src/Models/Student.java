package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import Database.*;

public class Student {
    static DatabaseConnection db;
    Connection conn;

    public Student() {
        db = new DatabaseConnection();
        conn = db.getDatabase();
    }

    public ArrayList<String[]> getAllStudents() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Student");

        ArrayList<String[]> data = new ArrayList<>();

        while (rs.next()) {
            int studentId = rs.getInt("StudentId");
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            String cnp = rs.getString("Cnp");
            String email = rs.getString("Email");
            int groupId = rs.getInt("GroupId");
            String[] record = {String.valueOf(studentId), firstName, lastName, cnp, email, String.valueOf(groupId)};
            if(groupId == 0){
                record[5] = "Nu are grupa";
            }
            data.add(record);
        }

        return data;
    }

    public void deleteStudent(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Student WHERE StudentId = ?");
        stmt.setString(1, String.valueOf(id));
        stmt.executeUpdate();
    }

    public void insertStudent(String firstName, String lastName, String cnp, String email) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Student (FirstName, LastName, Cnp, Email) VALUES (?, ?, ?, ?);"
        );
        stmt.setString(1, String.valueOf(firstName));
        stmt.setString(2, String.valueOf(lastName));
        stmt.setString(3, String.valueOf(cnp));
        stmt.setString(4, String.valueOf(email));
        stmt.executeUpdate();
    }

    public void assignGroup(int studentId, int groupId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Student SET GroupId = ? WHERE StudentId = ?;"
        );
        stmt.setString(1, String.valueOf(groupId));
        stmt.setString(2, String.valueOf(studentId));
        stmt.executeUpdate();
    }
}
