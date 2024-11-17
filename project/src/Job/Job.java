package Job;

public class Job {
    private int id;
    private String command;
    private JobState status;


    public Job(int id, String command) {
        this.id = id;
        this.command = command;
        this.status = JobState.Padding;
    }
    public int getId() {
        return id;
    }
    public String getCommand() {
        return command;
    }
}
