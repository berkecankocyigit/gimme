public class RemoteComputersMain {
    public static void main(String[] args) {

        Thread windowsComputer1 = new Thread(() -> {
            Client client = new Client("127.0.0.1", 5001);
            client.start();
        });
        Thread windowsComputer2 = new Thread(() -> {
            Client client = new Client("127.0.0.1", 5002);
            client.start();
        });

        windowsComputer1.start();
        windowsComputer2.start();

    }
}
