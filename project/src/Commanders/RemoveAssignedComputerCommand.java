package Commanders;

import Computers.Computer;
import Users.Student;

import java.util.Iterator;

public class RemoveAssignedComputerCommand implements Command {
    Student student;
    Computer computer;
    public RemoveAssignedComputerCommand(Student student, Computer computer) {
        this.student = student;
        this.computer = computer;
    }

    @Override
    public void execute() {
        databaseManager.removeAssignedComputer(computer, student);
    }
}
