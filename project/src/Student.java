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
        System.out.println("Getting assigned computers of " + this.getName());
        for (Computer computer : assignedComputers) {
            computer.getSpecifications();
            System.out.println("-------------");
        }
    }
    public void addAssignedComputers(Computer assignedComputers) {
        this.assignedComputers.add(assignedComputers);
    }

    public void removeAssignedComputer(Computer assignedComputer) {
        this.assignedComputers.remove(assignedComputer);
    }

}
