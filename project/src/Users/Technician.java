package src.Users;

public class Technician extends ManagerAbstract {
    String type;

    public Technician(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.type ="Technician";
        System.out.println(name + " technician created");
    }

    public String getType() { return type; }
}
