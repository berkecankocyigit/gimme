package LocalComputer.src;

public enum JopType {
    Job("job"),
    Request("request");

    private String state;

    JopType(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
