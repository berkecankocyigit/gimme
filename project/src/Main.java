import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseConnector = DatabaseManager.getInstance();
        Connection con = databaseConnector.connect();

        Student student1 = new Student(1, "student1", "student1@ozu.edu.tr", "s1_password");
        Student student2 = new Student(2, "student2", "student2@ozu.edu.tr", "s2_password");

        Computer windowsComputer = ComputerFactory.createComputer(1, "Windows", "Dell XPS", "16GB", "512GB SSD");
        Computer macComputer = ComputerFactory.createComputer(2,"Mac", "Mac Air", "8GB", "256GB SSD");

        Technician technician = new Technician(3, "technician1", "technician1@ozu.edu.tr", "t1_password");

        technician.addUser(con, student1);
        technician.addUser(con, student2);

        technician.addComputer(con, windowsComputer);
        technician.addComputer(con, macComputer);


        technician.assignComputer(con, student1, windowsComputer);
        technician.assignComputer(con, student1, macComputer);

        student1.showUserComputers(con);
        student2.showUserComputers(con);

        System.out.println("***************************************");
        technician.removeUser(con, student1);
        technician.removeUser(con, student2);
        technician.removeComputer(con, windowsComputer);
        technician.removeComputer(con, macComputer);
        technician.removeAssignedComputer(con, student1, windowsComputer);
        technician.removeAssignedComputer(con, student1, macComputer);


    }
}