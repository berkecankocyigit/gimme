package main.java.RemoteComputer.src;

public class windowsComputer1 {
    public static void main(String[] args) {
        Client client = new Client("192.168.43.42", 5001);
        client.start();
    }
}
