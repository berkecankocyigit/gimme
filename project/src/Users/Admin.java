package src.Users;

public class Admin extends ManagerAbstract {
    String type;
    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.type = "Admin";
        System.out.println(name+" admin created");
    }

    public String getType() {
        return type;
    }

}
