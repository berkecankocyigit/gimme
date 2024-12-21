package SocketCommunicator;

import Computers.Computer;
import Job.Job;
import Job.JobPrototype;
import Job.JobState;
import lombok.Setter;

public class ServerAPI {
    @Setter
    private Computer computer;
    private Server server;

    public ServerAPI(Computer computer) {
        this.computer = computer;
        this.server = new Server(computer);
    }

    public void communicate(Job job) {
        JobPrototype tmpJob = new JobPrototype(job.getId(), job.getCommand());

        Thread listenerThread = new Thread(() -> {
            this.computer.shiftState();
            job.setStatus(JobState.Running);
            JobState runResult = server.sendJob(tmpJob);
            job.setStatus(runResult);
            this.computer.shiftState();
        });
        listenerThread.start();

    }
}
