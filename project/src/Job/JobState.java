package src.Job;

public enum JobState {
    PENDING("padding"),
    RUNNING("running"),
    SUCCESS("success"),
    ERROR("error");

    private String state;

    JobState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
