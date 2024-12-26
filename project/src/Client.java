package src;

import src.Job.JobPrototypeLocal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    private final int port;
    private final String host;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        try {

            this.socket = new Socket(this.host, this.port);
            System.out.println("Connected to server at " + this.host + ":" + this.port);
            this.output = new ObjectOutputStream(this.socket.getOutputStream());
            this.input = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Bad Connection");
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        new Thread(() -> {
            while (true) { // Continuous listening loop
                try {
                    Object receivedObject = input.readObject();
                    if (receivedObject instanceof JobPrototypeLocal job) {
                        System.out.println("Received src.Job:");
                        System.out.println(job); // Call printAll to display job details
                    } else {
                        System.out.println("Unexpected object received: " + receivedObject);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Connection closed or error occurred.");
                    break; // Exit the loop if connection is lost or an error occurs
                }
            }
        }).start();
    }

    public void sendMessage(JobPrototypeLocal job) {
        try {
            output.writeObject(job);
            output.flush();
            System.out.println("Sent job: " + job.getId());
        } catch (IOException e) {
            System.out.println("Failed to send job.");
            e.printStackTrace();
        }
    }
}