package Job;

import Computers.Computer;
import Users.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Job {
    private int id;
    private String command;
    private JobState status = JobState.Padding;
    private User userAssigned;
    private Computer computerAssigned;


    public Job(int id, String command, User userAssigned, Computer computerAssigned) {
        this.id = id;
        this.command = command;
        this.userAssigned = userAssigned;
        this.computerAssigned = computerAssigned;

    }

    public void shiftState(){
        if (this.status == JobState.Running) {
            this.status = JobState.Complete;
        }
        if (this.status == JobState.Padding) {
            this.status = JobState.Running;
        }
    }
    public void runJob(){
        this.shiftState();
    }

    public void getSpecifications(){
        System.out.println("Job - id : " + this.id + " command : " + this.command + " Status : " + this.status.getState());
    }

}
