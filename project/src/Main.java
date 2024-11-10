import Computers.Computer;
import Computers.ComputerFactory;
import Computers.ComputerType;
import Database.DatabaseManager;
import Job.Job;
import Users.Student;
import Users.Technician;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        ComputerFactory computerFactory = new ComputerFactory();

        Student student1 = new Student(1, "student1", "student1@ozu.edu.tr", "s1_password");
        Student student2 = new Student(2, "student2", "student2@ozu.edu.tr", "s2_password");

        Computer windowsComputer = computerFactory.createComputer(ComputerType.WindowsComputer, 1, "Dell XPS", "16GB", "512GB SSD");
        Computer macComputer = computerFactory.createComputer(ComputerType.MacComputer, 2, "Mac Air", "8GB", "256GB SSD");

        Technician technician = new Technician(3, "technician1", "technician1@ozu.edu.tr", "t1_password");

        System.out.println("----------------------------------");

        technician.addUser(student1);
        technician.addUser(student2);
        System.out.println("----------------------------------");

        technician.addComputer(windowsComputer);
        technician.addComputer(macComputer);
        System.out.println("----------------------------------");

        technician.assignComputer(student1, windowsComputer);
        technician.assignComputer(student1, macComputer);
        System.out.println("----------------------------------");

        System.out.println("Student 1");
        student1.printAssignedComputers();
        System.out.println("Student 2");
        student2.printAssignedComputers();

        System.out.println("----------------------------------");
        Job job1 = new Job(1, "java main.java");
        Job job2 = new Job(2, "java deneme.java");
        System.out.println("Student 1");
        student1.addAssignedJob(job1, windowsComputer);
        System.out.println("Student 2");
        student2.addAssignedJob(job2, macComputer);

        System.out.println("----------------------------------");
        student1.printAssignedJob();
        student2.printAssignedJob();

        System.out.println("***************************************");
        technician.removeUser(student1);
        technician.removeUser(student2);
        technician.removeComputer(windowsComputer);
        technician.removeComputer(macComputer);
        technician.removeAssignedComputer(student1, windowsComputer);
        technician.removeAssignedComputer(student1, macComputer);


    }
}