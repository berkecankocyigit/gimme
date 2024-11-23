package Job;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Job {
    private int id;
    private String command;
    private JobState status;


    public Job(int id, String command) {
        this.id = id;
        this.command = command;
        this.status = JobState.Padding;
    }
}
