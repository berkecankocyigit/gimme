package Job;

import Computers.Computer;
import lombok.Setter;

import javax.swing.*;
import java.util.Iterator;
import java.util.Objects;

@Setter
public class JobSchedular {
    private Computer computer;

    public void runJob(){
        Iterator jobs = computer.getAssignedJob();
        while(jobs.hasNext()){
            Job job = (Job) jobs.next();
            if (job.getStatus().getState() == "running"){
                job.completeJob();

            } else {
                if (computer.checkAvailability() && job.getStatus().getState() =="padding") {
                        job.runJob();
                        computer.shiftState();
                    }
                }
        }
    }

}
