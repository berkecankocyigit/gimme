package Job;


import java.io.Serial;
import java.io.Serializable;

public class JobPrototype implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String command;

    public JobPrototype(int id, String command) {
        this.id = id;
        this.command = command;
    }

    public int getId() {
        return this.id;
    }
    public String getCommand() {
        return this.command;
    }

    @Override
    public String toString() {
        return "Job{id=" + id + ", description='" + command + "'}";
    }
}
