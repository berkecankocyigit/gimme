import Computers.Computer;
import Computers.ComputerState;
import Computers.WindowsComputerFactory;
import Job.Job;
import Job.JobSchedular;
import Users.Student;

public class Main {
    public static void main(String[] args) {
        // Factory ve Scheduler oluştur
        WindowsComputerFactory windowsComputerFactory = new WindowsComputerFactory();
        JobSchedular jobSchedular = new JobSchedular();

        // Öğrencileri oluştur
        Student student1 = new Student(1, "student1", "student1@ozu.edu.tr", "s1_password");
        Student student2 = new Student(2, "student2", "student2@ozu.edu.tr", "s2_password");

        // Windows bilgisayarları oluştur
        Computer windowsComputer1 = windowsComputerFactory.createComputer(1, "Dell XPS", "16GB", "512GB SSD");
        Computer windowsComputer2 = windowsComputerFactory.createComputer(2, "HP Pavilion", "8GB", "256GB SSD");

        // JobSchedular'ı bilgisayarlara gözlemci olarak ekle
        windowsComputer1.addObserver(jobSchedular);
        windowsComputer2.addObserver(jobSchedular);

        // Bilgisayarları öğrencilere ata
        student1.addAssignedComputer(windowsComputer1);
        student2.addAssignedComputer(windowsComputer2);

        // İşleri öğrencilere ata
        student1.addAssignedJob(1, "python main.py", windowsComputer1);
        student1.addAssignedJob(2, "python analyze.py", windowsComputer1);
        student2.addAssignedJob(3, "python run.py", windowsComputer2);
        student2.addAssignedJob(4, "python clean.py", windowsComputer2);

        // İş durumlarını yazdır
        System.out.println("Başlangıç İş Durumları:");
        student1.printAssignedJob();
        student2.printAssignedJob();

        try {
            Thread.sleep(10000); // 10 saniye bekle
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // İşlerin son durumlarını yazdır
        System.out.println("\nGüncel İş Durumları:");
        student1.printAssignedJob();
        student2.printAssignedJob();

    }
}
