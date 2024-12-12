package Job;

import Computers.Computer;
import lombok.Setter;

import java.util.Iterator;

@Setter
public class JobSchedular {
    private Computer computer;

    public void runJob(){
        Iterator jobs = computer.getAssignedJob();
        while(jobs.hasNext()){
            Job job = (Job) jobs.next();
            if(computer.checkAvailability()){
                job.runJob();
                computer.shiftState();
            }
        }
    }

}
