package Job;

import Computers.Computer;
import Computers.ComputerState;
import lombok.Setter;

import java.util.Iterator;

@Setter
public class JobSchedular implements Observer {
    @Override
    public void update(Computer computer) {
        if (computer.getState() == ComputerState.AVAILABLE) {
            runJob(computer);
        }
    }

    public void runJob(Computer computer) {
        Iterator<Job> jobs = computer.getAssignedJob();
        while (jobs.hasNext()) {
            Job job = jobs.next();
            if (job.getStatus() == JobState.Padding) {
                job.runJob(computer.getListener());
                computer.shiftState();
                break;
            }
        }
    }
}

