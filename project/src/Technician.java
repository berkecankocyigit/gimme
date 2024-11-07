import java.sql.Connection;
import java.sql.Statement;

public class Technician extends User { ;
    String status;

    public Technician(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.status="Technician";
        System.out.println(name + " technician created");
    }

    public String getStatus() { return status; }

    public void addUser(Connection conn, User user){
        Statement statement;
        try {
            String query=String.format("INSERT INTO users(id, name, email, password, status) VALUES ('%s','%s','%s','%s','%s');",
                    user.getId(),user.getName(),user.getEmail(),user.getPassword(),user.getStatus()
            );

            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("User Inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void addComputer(Connection conn, Computer computer){
        Statement statement;
        try {
            String query=String.format("INSERT INTO computers(id, model, ram, stırage) VALUES ('%s','%s','%s','%s');",
                    computer.getId(), computer.getModel(), computer.getRam(), computer.getStorage()
            );

            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Computer Inserted");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void assignComputer(Connection conn, User user, Computer computer) {
        Statement statement;
        try {
            String query = String.format(
                    "INSERT INTO user_computers (user_id, computer_id) VALUES ('%s', '%s');",
                    user.getId(), computer.getId()
            );

            statement = conn.createStatement();
            statement.executeUpdate(query);

            System.out.println("Row Inserted into user_computers");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeUser(Connection conn, User user) {
        Statement statement;
        try {
            String query = String.format(
                    "DELETE FROM users WHERE id = %d;",
                    user.getId()
            );

            statement = conn.createStatement();
            statement.executeUpdate(query);

            System.out.println("User with ID " + user.getId() + " removed");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeComputer(Connection conn, Computer computer) {
        Statement statement;
        try {
            String query = String.format(
                    "DELETE FROM computers WHERE id = %d;",
                    computer.getId()
            );

            statement = conn.createStatement();
            statement.executeUpdate(query);

            System.out.println("Computer with ID " + computer.getId() + " removed");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeAssignedComputer(Connection conn, User user, Computer computer) {
        Statement statement;
        try {
            String query = String.format(
                    "DELETE FROM user_computers WHERE user_id = %d AND computer_id = %d;",
                    user.getId(), computer.getId()
            );

            statement = conn.createStatement();
            statement.executeUpdate(query);

            System.out.println("Relation between User ID " + user.getId() + " and Computer ID " + computer.getId() + " removed");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}