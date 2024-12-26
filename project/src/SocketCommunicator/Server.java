package src.SocketCommunicator;

import src.Computers.Computer;
import src.Job.Job;
import src.Job.JobPrototypeRemote;
import src.Job.JobPrototypeLocal;
import src.Job.JobState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Computer computer;
    private int port;

    public Server(Computer computer) {
        this.computer = computer;
        this.port = this.computer.getPort();

        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Geri dönüş değeri olmayan bir start metodu
    public void start() {
        new Thread(() -> {
            while (true) {
                try {
                    // Client bağlantısı oluşturulmamışsa veya kesildiyse yenisini kabul et
                    if (this.clientSocket == null || this.clientSocket.isClosed()) {
                        this.clientSocket = serverSocket.accept();
                        System.out.println("Client connected: " + clientSocket.getInetAddress());
                        this.output = new ObjectOutputStream(this.clientSocket.getOutputStream());
                        this.input = new ObjectInputStream(this.clientSocket.getInputStream());
                    }

                    // Müşteriden gelen cevabı oku
                    Object response = this.input.readObject();

                    if (response instanceof String) {
                        String answer = (String) response;
                        System.out.println("Client response: " + answer);

                        // Gelen cevaba göre işlem yapabilirsiniz
                        if (answer.equals("success")) {
                            // Örnek işlem
                            System.out.println("Job başarılı olarak işlenmiş.");
                        } else {
                            // Örnek işlem
                            System.out.println("Job hata verdi.");
                        }

                    } else if (response instanceof JobPrototypeLocal) {
                        System.out.println("deneeee");
                        JobPrototypeLocal jobLocal = (JobPrototypeLocal) response;
                        System.out.println("Client sent a local job: " + jobLocal.toString());
                    }

                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Connection closed or error occurred.");
                    e.printStackTrace();
                    try {
                        if (this.clientSocket != null) {
                            this.clientSocket.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void sendMessage(JobPrototypeRemote job) {
        try {
            if (this.clientSocket != null && !this.clientSocket.isClosed()) {
                this.output.writeObject(job);
                this.output.flush();
                System.out.println("Sent job: " + job.getId());
            } else {
                System.out.println("No active client connection. Unable to send job.");
            }
        } catch (IOException e) {
            System.out.println("Failed to send job.");
            e.printStackTrace();
        }
    }
}
