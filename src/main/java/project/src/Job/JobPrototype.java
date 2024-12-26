package Job;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class JobPrototype implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String command;
    private String args;

    public JobPrototype(int id, String command, String args) {
        this.id = id;
        this.command = command;
        this.args = args;
    }

    @Override
    public String toString() {
        return "Job{id=" + id + ", description='" + command + " " + args + "'}";
    }
}
