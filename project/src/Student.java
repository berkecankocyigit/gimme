public class Student extends User {
    public Student(String id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public void sendJobRequest() {
        System.out.println("Sending job request");
    }
}
