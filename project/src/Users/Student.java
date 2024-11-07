package Users;
import Computers.Computer;

import java.util.HashSet;
import java.util.Set;

public class Student extends User {
    String status;

    private Set<Computer> assignedComputers = new HashSet<>();
    public Student(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.status = "Users.Student";
        System.out.println(name + " student created");
    }

    public String getStatus() { return status; }
    public void sendJobRequest() {
        System.out.println("Sending job request: " + this.getName());
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
        assignedComputers.addAssignedStudent(this);
    }

    public void removeAssignedComputer(Computer assignedComputer) {
        this.assignedComputers.remove(assignedComputer);
        assignedComputer.removeAssignedStudent(this);
    }

}
