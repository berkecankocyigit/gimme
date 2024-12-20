package Job;

import Computers.Computer;
import Computers.ComputerState;
import lombok.Setter;

import java.util.Iterator;

@Setter
public class JobSchedular implements JobSchedularObserver {
    @Override
    public void startRun(Computer computer) {
        if (computer.getState() == ComputerState.AVAILABLE) {
            runJob(computer);
        }
    }

    @Override
    public void endRun(Computer computer) {
        if (computer.getState() == ComputerState.BUSY) {
            finishJob(computer);
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

    public void finishJob(Computer computer) {
        Iterator<Job> jobs = computer.getAssignedJob();
        while (jobs.hasNext()) {
            Job job = jobs.next();
            if (job.getStatus() == JobState.Running) {
                job.completeJob();
                break;
            }
        }
    }
}

