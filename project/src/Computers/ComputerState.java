package Computers;

public enum ComputerState {
    AVAILABLE(true),
    BUSY(false);

    private boolean isAvailable;

    ComputerState(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }



}