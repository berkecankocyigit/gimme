package src.Job;

import src.Computers.Computer;
import src.Users.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Job {
    private int id;
    private String path;
    private String command;
    private String args;
    private JobState status = src.Job.JobState.PENDING;
    private User userAssigned;
    private Computer computerAssigned;


    public Job(int id,String path,String command, String args, User userAssigned, Computer computerAssigned) {
        this.id = id;
        this.command = command;
        this.args = args;
        this.userAssigned = userAssigned;
        this.computerAssigned = computerAssigned;

    }

    public void getSpecifications(){
        System.out.println("Job - id : " + this.id + " command : " + this.command + " Status : " + this.status.getState());
    }

    public String toString() {
        return "Job - id : " + this.id + " command : " + this.command + " Status : " + this.status.getState();
    }

}
