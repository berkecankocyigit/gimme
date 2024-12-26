package src;

import src.Job.JobPrototypeRemote;

import java.util.Random;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    private int port;
    private String host;
    private int n;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            this.socket = new Socket(this.host, this.port);
            System.out.println("Connected to server at " + this.host + ":" + this.port);

        } catch (IOException e) {
            System.out.println("Bad Connection");
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        while (true) { // Continuous listening loop
            try {
                this.output = new ObjectOutputStream(this.socket.getOutputStream());
                this.input = new ObjectInputStream(this.socket.getInputStream());
                Object receivedObject = input.readObject();
                if (receivedObject instanceof JobPrototypeRemote) {
                    JobPrototypeRemote job = (JobPrototypeRemote) receivedObject;
                    System.out.println("asd");
                    CommandRunner commandRunner = new CommandRunner(job.getPath(), job.getCommand(), job.getArgs());//job.getCommand());
                    int result = commandRunner.run();

                    if (result == 0){
                        System.out.println("Job " + job.getId()+" completed!");
                        output.writeObject("success");
                    } else {
                        System.out.println("Job " + job.getId()+" get Error!");
                        output.writeObject("error");
                    }

                    output.flush();

                } else {
                    System.out.println("Unexpected object received: " + receivedObject);
                }

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Connection closed or error occurred.");
                System.out.println(e);
                System.out.println(e.getMessage());
                break; // Exit the loop if connection is lost or an error occurs
            }
        }
    }
}
