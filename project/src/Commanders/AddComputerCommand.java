package src.Commanders;

import src.Computers.Computer;

public class AddComputerCommand implements Command {
    private Computer computer;

    public AddComputerCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        databaseManager.addComputer(computer);
    }
}
