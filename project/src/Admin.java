public class Admin extends User {
    public Admin(String id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public void manageUsers() {
        System.out.println("Admin manages users");
    }
}
