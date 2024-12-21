package Job;

public enum JobState {
    Padding("padding"),
    Running("running"),
    Success("success"),
    Error("error");

    private String state;

    JobState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
