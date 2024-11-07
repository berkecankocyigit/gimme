public class Admin extends User {
    String status;
    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.status = "Admin";
        System.out.println(name+" admin created");
    }

    public String getStatus() { return status; }

    public void manageUsers() {
        System.out.println("Admin manages users");
    }
}
