package src.Job;

import src.Computers.Computer;
import src.Computers.ComputerState;
import src.SocketCommunicator.ServerAPI;
import lombok.Setter;

import java.util.Iterator;

@Setter
public class JobSchedular implements JobSchedularObserver {
    private ServerAPI server;
    @Override
    public void startRun(Computer computer) {
        if (computer.getState() == ComputerState.AVAILABLE) {
            this.server = computer.getServerAPI();
            runJob(computer);
        }
    }

    public void runJob(Computer computer) {
        synchronized (this) {
            Iterator<Job> jobs = computer.getAssignedJob();
            while (jobs.hasNext()) {
                Job job = jobs.next();
                if (job.getStatus() == JobState.Padding) {
                    this.server.sendJob(job);
                    break;
                }
            }
        }
    }
}

