package Computers;
import Listener.Listener;
import Users.Student;
import Job.Job;
import Job.JobIterator;
import Job.Observer;
import Job.JobState;

import java.util.ArrayList;
import java.util.List;
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
    private List<Observer> observers = new ArrayList<>();
    private Listener listener = new Listener( this);

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
        this.notifyObservers();
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

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void shiftState() {
        if (state == ComputerState.AVAILABLE) {
            state = ComputerState.BUSY;
        } else {
            state = ComputerState.AVAILABLE;
        }
        notifyObservers(); // Durum değişikliğinde observer'ları bilgilendir
    }


    public void update() {
        finishJob();
    }

    public void finishJob() {
        Iterator<Job> jobs = this.getAssignedJob();
        while (jobs.hasNext()) {
            Job job = jobs.next();
            if (job.getStatus() == JobState.Running) {
                job.completeJob();
                break;
            }
        }
    }
}
