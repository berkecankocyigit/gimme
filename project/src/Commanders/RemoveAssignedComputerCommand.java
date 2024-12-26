package src.Commanders;

import src.Computers.Computer;
import src.Users.Student;

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
