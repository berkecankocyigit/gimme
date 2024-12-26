package src.Commanders;

import src.Computers.Computer;
import src.Users.Student;

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
