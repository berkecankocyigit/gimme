package Commanders;

import Computers.Computer;

import java.util.Iterator;

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
