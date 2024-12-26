package LocalComputer.src;


import java.io.Serial;
import java.io.Serializable;

public class JobPrototypeLocal implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String requestType;
    private String command;
    private  String args;
    private int computerID;

    public JobPrototypeLocal(int id, String requestType, String command, String args, int computerID) {
        this.id = id;
        this.requestType = requestType;
        this.command = command;
        this.args = args;
        this.computerID = computerID;
    }

    public int getId() {return this.id;}
    public String getRequestType(){return this.requestType;}
    public String getCommand() {return this.command;}
    public String getArgs() {return this.args;}
    public int getComputerID() {return this.computerID;}

    @Override
    public String toString() {
        return "Job{id=" + id + "type=" + requestType + ", description='" + command + " " + args + ", computerID='" + computerID + "'}";
    }
}
