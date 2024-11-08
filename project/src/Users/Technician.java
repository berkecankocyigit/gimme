package Users;

public class Technician extends ManagerAbstract {
    String status;

    public Technician(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.status="Users.Technician";
        System.out.println(name + " technician created");
    }

    public String getStatus() { return status; }
}
