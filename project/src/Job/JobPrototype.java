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

    public JobPrototype(int id, String command) {
        this.id = id;
        this.command = command;
    }

    @Override
    public String toString() {
        return "Job{id=" + id + ", description='" + command + "'}";
    }
}
