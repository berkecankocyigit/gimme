package Users;
import Computers.Computer;
import Computers.ComputerIterator;
import Job.Job;

import java.util.HashSet;
import java.util.Iterator;
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

    public Iterator getAssignedComputers() {
        return new ComputerIterator(assignedComputers);
    }

    public void printAssignedJob() {
        for (Computer computer : assignedComputers) {
            System.out.print("The jobs in ");
            computer.getSpecifications();
            for (Job job : computer.getAssignedJob()){
                System.out.println("Job Id: " + job.getId() + " Command : " + job.getCommand());
            }
        }
    }

    public void addAssignedComputer(Computer computer) {
        assignedComputers.add(computer);
    }

    public void removeAssignedComputer(Computer computer) {
        assignedComputers.remove(computer);
    }

    public void addAssignedJob(Job job, Computer computer) {
        if (assignedComputers.contains(computer)) {
            System.out.println("The job is assined succesfully !!!");
            computer.addAssignedJob(job);
        } else {
            System.out.println("The computer is not assined");
        }
    }

    public void removeAssignedJob(Job job, Computer computer) {
        if (assignedComputers.contains(computer)) {
            System.out.println("The job is remove succesfully !!!");
            computer.removeAssignedJob(job);
        } else {
            System.out.println("The computer is not assined");
        }
    }



}
