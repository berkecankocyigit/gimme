package src.Job;


import java.io.Serial;
import java.io.Serializable;

public class JobPrototypeRemote implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String command;
    private String path;
    private  String args;

    public JobPrototypeRemote(int id,String path, String command, String args) {
        this.id = id;
        this.command = command;
        this.args = args;
        this.path = path;
    }

    public int getId() {
        return this.id;
    }
    public String getCommand() {
        return this.command;
    }

    public String getArgs() {return this.args;}
    public String getPath() {return this.path;}

    @Override
    public String toString() {
        return "Job{id=" + id + ", description='"+path+" "+ command + " " + args + "'}";
    }
}
