package Listener;

import Computers.Computer;
import Job.Job;
import Job.JobSchedular;

public class Listener {
    private JobSchedular observer = new JobSchedular();
    private Computer computerProxy;

    public Listener(Computer computerProxy) {
        this.computerProxy = computerProxy;
    }

    public void runCode(Job job) {
        Thread listenerThread = new Thread(() -> {
            try {
                Thread.sleep(2000); // 2 saniye bekle
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyObservers();
        });
        listenerThread.start(); // Listener'ı thread'de çalıştır
    }

    public void notifyObservers() {
        this.observer.endRun(this.computerProxy);
    }
}
