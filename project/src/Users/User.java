package Users;
import Computers.Computer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class User {
    private int id;
    private String status;
    private String name;
    private String email;
    private String password;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public abstract String getStatus();

    public void showUserComputers(Connection conn) {
        String query = "SELECT c.id, c.model, c.ram, c.stırage " +
                "FROM computers c " +
                "JOIN user_computers uc ON c.id = uc.computer_id " +
                "JOIN users u ON u.id = uc.user_id " +
                "WHERE u.id = ?;";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Kullanıcı id'sini sorguya ekliyoruz
            stmt.setInt(1, id);

            // Sorguyu çalıştırıyoruz
            ResultSet rs = stmt.executeQuery();

            // Sonuçları yazdırıyoruz
            System.out.println("Computers assigned to Users.User (ID: " + id + "):");
            while (rs.next()) {
                int computerId = rs.getInt("id");
                String model = rs.getString("model");
                String ram = rs.getString("ram");
                String storage = rs.getString("stırage");

                // Bilgisayar bilgilerini ekrana yazdırıyoruz
                System.out.println("Computers.Computer ID: " + computerId + ", Model: " + model + ", RAM: " + ram + ", Storage: " + storage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


