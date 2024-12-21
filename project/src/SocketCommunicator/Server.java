package SocketCommunicator;

import Job.JobPrototype;
import Computers.Computer;
import Job.JobState;

import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Computer computer;
    private int port;

    private boolean checker = true;

    public Server(Computer computer) {
        this.computer = computer;
        this.port = this.computer.getPort();

        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public JobState sendJob(JobPrototype job) {
        try {
            if (checker) {
                this.clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                checker = false;
            }

            this.output = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.input = new ObjectInputStream(this.clientSocket.getInputStream());

            // Job objesini gönder
            System.out.println("Sending job: " + job);
            this.output.writeObject(job);

            // İstemciden yanıt bekle
            Object response = this.input.readObject();
            if (response instanceof String) {
                String result = (String) response;
                System.out.println("Client response: Job "+ job.getId() + " " + result);

                if (result.equals("success")) {
                    return JobState.Success;
                } else {
                    return JobState.Error;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
