package src.Job;

import src.Computers.Computer;

public interface JobSchedularObserver {
    void startRun(Computer computer);
}
