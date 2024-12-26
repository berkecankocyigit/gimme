package src.Job;


import java.io.Serial;
import java.io.Serializable;

public class JobPrototypeRemote implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String command;
    private  String args;

    public JobPrototypeRemote(int id, String command, String args) {
        this.id = id;
        this.command = command;
        this.args = args;
    }

    public int getId() {
        return this.id;
    }
    public String getCommand() {
        return this.command;
    }

    public String getArgs() {return this.args;}

    @Override
    public String toString() {
        return "Job{id=" + id + ", description='" + command + " " + args + "'}";
    }
}
