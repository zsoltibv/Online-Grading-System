package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import Database.*;

public class Groups {
    static DatabaseConnection db;
    Connection conn;

    public Groups() {
        db = new DatabaseConnection();
        conn = db.getDatabase();
    }

    public ArrayList<String[]> getAllGroups() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Grupe");

        ArrayList<String[]> data = new ArrayList<>();

        while (rs.next()) {
            int groupId = rs.getInt("GroupId");
            String fullName = rs.getString("FullName");
            String shortName = rs.getString("ShortName");
            String[] record = {String.valueOf(groupId), fullName, shortName};
            data.add(record);
        }

        return data;
    }

    public void insertGroup(String fullName, String shortName) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Grupe (FullName, ShortName) VALUES (?, ?);"
        );
        stmt.setString(1, String.valueOf(fullName));
        stmt.setString(2, String.valueOf(shortName));
        stmt.executeUpdate();
    }
}
