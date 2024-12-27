package src.SocketCommunicator;

import src.Computers.Computer;
import src.Job.Job;
import src.Job.JobPrototypeRemote;
import src.Job.JobState;
import lombok.Setter;

public class ServerAPI {
    @Setter
    private Computer computer;
    private Server server;

    public ServerAPI(Computer computer) {
        this.computer = computer;
        this.server = new Server(computer);
    }

    public void sendJob(Job job) {
        JobPrototypeRemote tmpJob = new JobPrototypeRemote(job.getId(),job.getPath() ,job.getCommand(), job.getArgs());

        Thread listenerThread = new Thread(() -> {
            this.computer.shiftState();
            job.setStatus(JobState.RUNNING);
            JobState runResult = server.sendJob(tmpJob);
            job.setStatus(runResult);
            this.computer.shiftState();
        });
        listenerThread.start();

    }
}