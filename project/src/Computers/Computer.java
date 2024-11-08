package Computers;
import Users.Student;
import Job.Job;

import java.util.HashSet;
import java.util.Set;

public abstract class Computer {
    protected int id;
    protected String model;
    protected String ram;
    protected String storage;
    protected Boolean state = true;
    private Set<Student> assignedStudents = new HashSet<>();
    private Set<Job> assignedJobs = new HashSet<>();

    public Computer(int id, String model, String ram, String storage) {
        this.id = id;
        this.model = model;
        this.ram = ram;
        this.storage = storage;
    }

    public abstract void getSpecifications();
    public int getId() {return id;}
    public String getModel() {return model;}
    public String getRam() {return ram;}
    public String getStorage() {return storage;}

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

    public Set<Job> getAssignedJob() {
        return (Set<Job>) this.assignedJobs;
    }
    public void printAssignedJob() {
        for (Job job : assignedJobs) {
            System.out.println("Job Id: " + job.getId() + " Command : " + job.getCommand());
        }
    }

    public Boolean hasJob() {
        return state;
    }

}