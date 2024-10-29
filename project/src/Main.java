public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin("1", "adimn", "admin.admin@ozu.edu.tr", "admin123");
        admin.manageUsers();
        Student student1 = new Student("2", "student1", "student@ozu.edu.tr", "student123");
        student1.sendJobRequest();
    }
}