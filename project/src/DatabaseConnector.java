public class DatabaseConnector {
    private static final DatabaseConnector ourInstance = new DatabaseConnector();

    public DatabaseConnector() {
        System.out.println("DatabaseConnector is initialized");
    }

    public static DatabaseConnector getInstance() {
        return ourInstance;
    }

    public void connect() {
        System.out.println("Connected to the  database.");
    }
}
