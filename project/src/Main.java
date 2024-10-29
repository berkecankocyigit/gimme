public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin("1", "adimn", "admin.admin@ozu.edu.tr", "admin123");

        Student student1 = new Student("2", "student1", "student@ozu.edu.tr", "student123");
        Technician technician = new Technician("3", "technician1", "technician@ozu.edu.tr", "technician123");
        Computer windowsComputer_1 = ComputerFactory.createComputer("Windows", "Dell XPS", "16GB", "512GB SSD");
        Computer windowsComputer_2 = ComputerFactory.createComputer("Mac", "Mac Air", "8GB", "256GB SSD");
        technician.assignComputer(student1, windowsComputer_1);
        technician.assignComputer(student1, windowsComputer_2);
        student1.getAssignedComputers();
        System.out.println("Remove Windows : ");
        technician.removeAssignedComputer(student1, windowsComputer_1);
        student1.getAssignedComputers();
        student1.sendJobRequest();
    }
}