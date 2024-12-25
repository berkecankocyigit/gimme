package Job;

import Computers.Computer;

public interface JobSchedularObserver {
    void startRun(Computer computer);
}
