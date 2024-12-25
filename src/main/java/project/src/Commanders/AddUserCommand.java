package Commanders;

import Users.User;

import java.util.Iterator;

public class AddUserCommand implements Command {
    User user;
    public AddUserCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        databaseManager.addUser(user);
    }
}
