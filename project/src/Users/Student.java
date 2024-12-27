package src.Users;

import lombok.Getter;
import lombok.Setter;
import src.Computers.Computer;
import src.Computers.ComputerIterator;
import src.Job.Job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
@Getter
@Setter
public class Student extends User {
    private String type;
    private Set<Computer> assignedComputers;

    public Student(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.type = "Student";
        this.assignedComputers = new HashSet<>();
        System.out.println(name + " student created");
    }

    public String getType() {
        return type;
    }
    public Iterator getAssignedComputerIterator() {
        return new ComputerIterator(assignedComputers);
    }

    public void printAssignedJob() {
        for (Computer computer : assignedComputers) {
            System.out.print("The jobs in ");
            computer.getSpecifications();
            computer.printAssignedJob();
        }
    }
    public ArrayList<String> getComputerIds() {
        ArrayList<String> computerIds = new ArrayList<>();
        for (Computer computer : assignedComputers) {
            computerIds.add(String.valueOf(computer.getId()));
        }
        return computerIds;
    }

    public void addAssignedComputer(Computer computer) {
        assignedComputers.add(computer);
    }
    public void removeAssignedComputer(Computer computer) {
        assignedComputers.remove(computer);
    }

    public void addAssignedJob(int id,String path ,String command, String args, Computer computer) {
        if (assignedComputers.contains(computer)) {
            Job job = new Job(id,path ,command, args,this, computer);
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
