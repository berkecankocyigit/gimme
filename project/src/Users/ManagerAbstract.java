package Users;
import Computers.Computer;

import java.sql.Connection;
import java.sql.Statement;

public abstract class ManagerAbstract extends User {
    public ManagerAbstract(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public void addUser(Connection conn, User user){
        Statement statement;
        try {
            String query=String.format("INSERT INTO users(id, name, email, password, status) VALUES ('%s','%s','%s','%s','%s');",
                    user.getId(),user.getName(),user.getEmail(),user.getPassword(),user.getStatus()
            );

            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Student with ID " + user.getId() + " added");
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
            System.out.println("Computer with ID " + computer.getId() + " added");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void assignComputer(Connection conn, Student student, Computer computer) {
        Statement statement;
        try {
            String query = String.format(
                    "INSERT INTO user_computers (user_id, computer_id) VALUES ('%s', '%s');",
                    student.getId(), computer.getId()
            );

            statement = conn.createStatement();
            statement.executeUpdate(query);

            student.addAssignedComputer(computer);
            computer.addAssignedStudent(student);

            System.out.println("Relation between Student ID " + student.getId() + " and Computer ID " + computer.getId() + " added");

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

            System.out.println("Student with ID " + user.getId() + " removed");

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

    public void removeAssignedComputer(Connection conn, Student student, Computer computer) {
        Statement statement;
        try {
            String query = String.format(
                    "DELETE FROM user_computers WHERE user_id = %d AND computer_id = %d;",
                    student.getId(), computer.getId()
            );

            statement = conn.createStatement();
            statement.executeUpdate(query);

            student.removeAssignedComputer(computer);
            computer.removeAssignedStudent(student);

            System.out.println("Relation between Student ID " + student.getId() + " and Computer ID " + computer.getId() + " removed");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
