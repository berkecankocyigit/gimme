public class windowsComputer1 {
    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5001);
        client.start();
    }
}
