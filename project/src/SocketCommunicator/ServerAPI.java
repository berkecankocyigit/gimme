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
        this.server.start();
    }

    public void communicate(Job job) {
        JobPrototypeRemote tmpJob = new JobPrototypeRemote(job.getId(), job.getCommand(), job.getArgs());

        Thread listenerThread = new Thread(() -> {
            this.computer.shiftState();
            job.setStatus(JobState.Running);
            server.sendMessage(tmpJob);
/*            JobState runResult = server.sendMessage(tmpJob);
            job.setStatus(runResult);
            this.computer.shiftState();*/
        });
        listenerThread.start();

    }
}
