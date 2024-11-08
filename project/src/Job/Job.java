package Job;

public class Job {
    private int id;
    private String command;

    public Job(int id, String command) {
        this.id = id;
        this.command = command;
    }
    public int getId() {
        return id;
    }
    public String getCommand() {
        return command;
    }
}
