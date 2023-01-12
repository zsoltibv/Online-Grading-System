package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    Connection conn;
    String userName;
    String password;
    String url;

    public DatabaseConnection(){
        url = "jdbc:jtds:sqlserver://DESKTOP-CS7UBUD:60569/dbHZ;instance=SQLEXPRESS";
        userName = "sa1";
        password = "ZSoltika23";
        conn = null;
    }

    public Connection getDatabase(){
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch(SQLException e) {
            System.out.println("SQL exception occured" + e);
        }
        return conn;
    }
}