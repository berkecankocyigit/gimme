package src.Job;

public enum JopType {
    Job("job"),
    Request("request");

    private final String state;

    JopType(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
