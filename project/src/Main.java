import Computers.Computer;
import Computers.WindowsComputerFactory;
import Job.Job;
import Users.Student;
import Users.Technician;

public class Main {
    public static void main(String[] args) {

        WindowsComputerFactory windowsComputerFactory = new WindowsComputerFactory();

        // Öğrencileri oluştur
        Student student1 = new Student(1, "student1", "student1@ozu.edu.tr", "s1_password");
        Student student2 = new Student(2, "student2", "student2@ozu.edu.tr", "s2_password");

        // 5 Windows bilgisayar oluştur
        Computer windowsComputer1 = windowsComputerFactory.createComputer(1, "Dell XPS", "16GB", "512GB SSD");
        Computer windowsComputer2 = windowsComputerFactory.createComputer(2, "HP Pavilion", "8GB", "256GB SSD");
        Computer windowsComputer3 = windowsComputerFactory.createComputer(3, "Lenovo ThinkPad", "16GB", "1TB HDD");
        Computer windowsComputer4 = windowsComputerFactory.createComputer(4, "Acer Aspire", "8GB", "128GB SSD");
        Computer windowsComputer5 = windowsComputerFactory.createComputer(5, "Asus VivoBook", "32GB", "1TB SSD");

        // Teknik görevlileri oluştur
        Technician technician = new Technician(3, "technician1", "technician1@ozu.edu.tr", "t1_password");

        System.out.println("----------------------------------");

        // Öğrencileri teknik görevlilere ekle
        technician.addUser(student1);
        technician.addUser(student2);
        System.out.println("----------------------------------");

        // Bilgisayarları teknik görevlilere ekle
        technician.addComputer(windowsComputer1);
        technician.addComputer(windowsComputer2);
        technician.addComputer(windowsComputer3);
        technician.addComputer(windowsComputer4);
        technician.addComputer(windowsComputer5);
        System.out.println("----------------------------------");

        // Bilgisayarları öğrencilere ata
        technician.assignComputer(student1, windowsComputer1);
        technician.assignComputer(student1, windowsComputer2);
        technician.assignComputer(student2, windowsComputer3);
        System.out.println("----------------------------------");

        // Atanan bilgisayarları yazdır
        System.out.println("Student 1");
        student1.printComputers(student1.getAssignedComputers());
        System.out.println("Student 2");
        student2.printComputers(student2.getAssignedComputers());

        System.out.println("----------------------------------");

        // İşleri öğrencilere ata
        System.out.println("Student 1");
        student1.addAssignedJob(1, "python main.py", windowsComputer1);
        student1.addAssignedJob(2, "python run.py", windowsComputer2);
        student1.addAssignedJob(3, "python run_new.py", windowsComputer2);
        System.out.println("Student 2");
        student2.addAssignedJob(4, "python deneme.py", windowsComputer3);

        System.out.println("----------------------------------");

        // Öğrencilerin işlerini yazdır
        student1.printAssignedJob();
        student2.printAssignedJob();

        System.out.println("----------------------------------");
        System.out.println("All Computers :");
        technician.printComputers(technician.getComputers());

        System.out.println("***************************************");

        // Öğrencileri ve bilgisayarları kaldır
        technician.removeUser(student1);
        technician.removeUser(student2);
        technician.removeComputer(windowsComputer1);
        technician.removeComputer(windowsComputer2);
        technician.removeComputer(windowsComputer3);
        technician.removeComputer(windowsComputer4);
        technician.removeComputer(windowsComputer5);

        technician.removeAssignedComputer(student1, windowsComputer1);
        technician.removeAssignedComputer(student1, windowsComputer2);
        technician.removeAssignedComputer(student2, windowsComputer3);
    }
}
