package Database;

import Computers.Computer;
import Computers.ComputerIterator;
import Computers.WindowsComputerFactory;
import Users.Student;
import Users.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DatabaseManager {
    DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
    Connection conn = databaseConnector.connect();

    public void addComputer(Computer computer) {
        Statement statement;
        try {
            String query=String.format("INSERT INTO computers(id, model, ram, storage) VALUES ('%s','%s','%s','%s');",
                    computer.getId(), computer.getModel(), computer.getRam(), computer.getStorage()
            );

            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Computer with ID " + computer.getId() + " added");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void addUser(User user) {
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

    public void assignComputer(Computer computer, Student student) {
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

    public void removeAssignedComputer(Computer computer, Student student) {
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

    public void removeComputer(Computer computer) {
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

    public void removeUser(User user) {
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

    public Iterator getComputers() {
        Set<Computer> computers = new HashSet<>();
        Statement statement;
        ResultSet resultSet;

        WindowsComputerFactory windowsComputerFactory = new WindowsComputerFactory();

        try {
            String query = "SELECT id, model, ram, storage FROM computers";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String model = resultSet.getString("model");
                String ram = resultSet.getString("ram");
                String storage = resultSet.getString("storage");

                // Computer nesnesini oluştur ve listeye ekle
                Computer computer = windowsComputerFactory.createComputer(id, model, ram, storage);
                computers.add(computer);
            }

            // Kaynakları serbest bırak
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error retrieving computers: " + e);
        }

        // Bilgisayar listesini döndür
        return new ComputerIterator(computers);
    }
}
