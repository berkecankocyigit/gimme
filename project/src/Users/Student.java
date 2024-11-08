package Users;
import Computers.Computer;

import java.util.HashSet;
import java.util.Set;

public class Student extends User {
    private String status;
    private Set<Computer> assignedComputers;

    public Student(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.status = "Student";
        this.assignedComputers = new HashSet<>();
        System.out.println(name + " student created");
    }

    @Override
    public String getStatus() {
        return status;
    }

    public Set<Computer> getAssignedComputers() {
        return assignedComputers;
    }

    public void printAssignedComputers() {
        for (Computer computer : assignedComputers) {
            computer.getSpecifications();
        }
    }

    public void addAssignedComputer(Computer computer) {
        assignedComputers.add(computer);
    }

    public void removeAssignedComputer(Computer computer) {
        assignedComputers.remove(computer);
    }


}
