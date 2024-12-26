import src.Client;

public class windowsComputer1 {
    public static void main(String[] args) {
        Client client = new Client("100.104.137.30", 5001);
        client.start();
    }
}
