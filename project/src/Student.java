import java.util.List;
import java.util.ArrayList;

public class Student extends User {
    private List<Computer> assignedComputers;
    public Student(String id, String name, String email, String password) {
        super(id, name, email, password);
        this.assignedComputers = new ArrayList<>();
    }

    public void sendJobRequest() {
        System.out.println("Sending job request");
    }
    public void getAssignedComputers() {
        for (Computer computer : assignedComputers) {
            computer.getSpecifications();
            System.out.println("-------------");
        }
    }
    public void setAssignedComputers(Computer assignedComputers) {
        this.assignedComputers.add(assignedComputers);
    }
}
