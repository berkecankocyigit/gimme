import src.Client;
import src.Job.JobPrototypeLocal;
import src.Job.JopType;

public class LocalMain {
    public static void main(String[] args) {
        JobPrototypeLocal jp = new JobPrototypeLocal(0, JopType.Job.getState(), "deneme", "test", 0);
        System.out.println("Computer generted");
        Client client = new Client("192.168.43.42", 5001);
        System.out.println("Client genereted");
        client.start();
        System.out.println("Listen");

        client.sendMessage(jp);
        System.out.println("Send");


    }
}
