package src.Commanders;

import src.Computers.Computer;

public class RemoveComputerCommand implements Command {

    private Computer computer;

    public RemoveComputerCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        databaseManager.removeComputer(computer);
    }
}
