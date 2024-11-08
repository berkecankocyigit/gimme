package Users;

public class Admin extends ManagerAbstract {
    String status;
    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.status = "Admin";
        System.out.println(name+" admin created");
    }

    @Override
    public String getStatus() {
        return status;
    }

}
