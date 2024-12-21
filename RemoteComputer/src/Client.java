import Job.JobPrototype;
import java.util.Random;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    private int port;
    private String host;
    private Random rand;
    private int n;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        this.rand = new Random();
        try {
            this.socket = new Socket(this.host, this.port);
            System.out.println("Connected to server at " + this.host + ":" + this.port);

        } catch (IOException e) {}
    }

    public void start() {
        while (true) { // Continuous listening loop
            try {
                n = rand.nextInt(100);
                this.output = new ObjectOutputStream(this.socket.getOutputStream());
                this.input = new ObjectInputStream(this.socket.getInputStream());

                Object receivedObject = input.readObject();
                if (receivedObject instanceof JobPrototype) {
                    JobPrototype job = (JobPrototype) receivedObject;
                    System.out.println("Received job: " + job);

                    System.out.println("Running job...");
                    Thread.sleep(2000); // Simulate job running


                    if (n < 60){
                        System.out.println("Job "+ job.getId()+" completed!");
                        output.writeObject("success");
                    } else {
                        System.out.println("Job "+ job.getId()+" get Error!");
                        output.writeObject("error");
                    }
                    output.flush();

                } else {
                    System.out.println("Unexpected object received: " + receivedObject);
                }

            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                System.out.println("Connection closed or error occurred.");
                break; // Exit the loop if connection is lost or an error occurs
            }
        }
    }
}
