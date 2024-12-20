package Listener;

import Computers.Computer;
import Computers.Observer;
import Job.Job;

public class Listener {
    private Computer observer;

    public Listener(Computer computer) {
        this.observer = computer;
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
        this.observer.update();
    }
}
