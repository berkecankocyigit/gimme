package src.Users;

import src.Commanders.*;
import src.Computers.Computer;

import java.util.Iterator;

public abstract class ManagerAbstract extends User {

    SimpleRemoteControl remoteControl = new SimpleRemoteControl();

    public ManagerAbstract(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public void addUser( User user){
        AddUserCommand command = new AddUserCommand(user);
        remoteControl.setSlot(command);
        remoteControl.doIt();
    }

    public void addComputer( Computer computer){
        Command command = new AddComputerCommand(computer);
        remoteControl.setSlot(command);
        remoteControl.doIt();

    }

    public void assignComputer( Student student, Computer computer) {
        AssignComputerCommand command = new AssignComputerCommand(student, computer);
        remoteControl.setSlot(command);
        remoteControl.doIt();
    }

    public void removeUser( User user) {
        RemoveUserCommand command = new RemoveUserCommand(user);
        remoteControl.setSlot(command);
        remoteControl.doIt();
    }

    public void removeComputer( Computer computer) {
        RemoveComputerCommand command = new RemoveComputerCommand(computer);
        remoteControl.setSlot(command);
        remoteControl.doIt();
    }

    public void removeAssignedComputer( Student student, Computer computer) {
        RemoveAssignedComputerCommand command = new RemoveAssignedComputerCommand(student, computer);
        remoteControl.setSlot(command);
        remoteControl.doIt();
    }

    public Iterator getComputers() {
        GetComputersCommand command = new GetComputersCommand();
        remoteControl.setIterator(command);
        return remoteControl.get_Computer();
    }
}
