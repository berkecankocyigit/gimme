package src.Job;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class JobPrototypeRemote implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String command;
    private String args;
    private String path;

    public JobPrototypeRemote(int id,String path, String command, String args) {
        this.id = id;
        this.command = command;
        this.args = args;
        this.path = path;
    }

    @Override
    public String toString() {
        return "Job{id=" + id + ", description='" + command + " " + args + "'}";
    }
}
