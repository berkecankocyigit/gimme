package Computers;

public enum ComputerState {
    AVAILABLE(true),
    BUSY(false);

    private final boolean isAvailable;

    ComputerState(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

}