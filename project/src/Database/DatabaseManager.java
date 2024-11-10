package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/database_deneme"; // Veritabanı URL'si
    private static final String DB_USER = "postgres";  // PostgreSQL kullanıcısı
    private static final String DB_PASSWORD = "password";  // PostgreSQL şifresi

    private static DatabaseManager ourInstance;

    private DatabaseManager() {

        System.out.println("Database.DatabaseManager is initialized");
    }

    public static DatabaseManager getInstance() {
        if (ourInstance == null) {
            synchronized (DatabaseManager.class) {
                if (ourInstance == null) {
                    ourInstance = new DatabaseManager();
                }
            }
        }
        return ourInstance;
    }

    public Connection connect() {
        Connection con = null;
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            if (con != null) {
                System.out.println("Database connection established");
            }else{
                System.out.println("Database connection failed");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return con;

    }
}
