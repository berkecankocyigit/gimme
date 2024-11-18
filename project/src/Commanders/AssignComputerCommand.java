package Commanders;

import Computers.Computer;
import Users.Student;

import java.util.Iterator;

public class AssignComputerCommand implements Command {
    Student student;
    Computer computer;

    public AssignComputerCommand(Student student, Computer computer) {
        this.student = student;
        this.computer = computer;
    }

    @Override
    public void execute() {
        databaseManager.assignComputer(computer, student);
    }
}
