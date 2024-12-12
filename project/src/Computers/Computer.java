package Computers;
import Users.Student;
import Job.Job;
import Job.JobIterator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Computer {
    protected int id;
    protected String model;
    protected String ram;
    protected String storage;
    protected ComputerState state = ComputerState.AVAILABLE;
    private Set<Student> assignedStudents = new HashSet<>();
    private Set<Job> assignedJobs = new HashSet<>();

    public Computer(int id, String model, String ram, String storage) {
        this.id = id;
        this.model = model;
        this.ram = ram;
        this.storage = storage;
    }

    public abstract void getSpecifications();

    public void getAssignedStudents() {
        System.out.println("Getting assigned computers of " + this.model);
        for (Student student : assignedStudents) {
            System.out.println(student.getName());
            System.out.println("-------------");
        }
    }
    public void addAssignedStudent(Student assignedStudent) {
        this.assignedStudents.add(assignedStudent);
    }

    public void removeAssignedStudent(Student assignedStudent) {
        this.assignedStudents.remove(assignedStudent);
    }

    public void addAssignedJob(Job assignedJob) {
        this.assignedJobs.add(assignedJob);
    }

    public void removeAssignedJob(Job assignedJob) {
        this.assignedJobs.remove(assignedJob);
    }


    public Iterator getAssignedJob() {
        return new JobIterator(this.assignedJobs);
    }

    public void printAssignedJob() {
        for (Job job : assignedJobs) {
            job.getSpecifications();
        }
    }

    public Boolean checkAvailability() {
        return state.isAvailable();
    }


    public void shiftState(){
        if(state.isAvailable()){
            this.state = ComputerState.BUSY;
        } else {
            this.state = ComputerState.AVAILABLE;
        }
    }

}