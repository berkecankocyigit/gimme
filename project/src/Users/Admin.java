package Users;
import Computers.Computer;

public class Admin extends User {
    String status;
    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.status = "Users.Admin";
        System.out.println(name+" admin created");
    }

    public String getStatus() { return status; }

    public void manageUsers() {
        System.out.println("Users.Admin manages users");
    }
}
