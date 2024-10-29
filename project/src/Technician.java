public class Technician extends User {
    public Technician(String id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public void assignComputer(Student student, Computer computer) {
        student.setAssignedComputers(computer);
    }
}