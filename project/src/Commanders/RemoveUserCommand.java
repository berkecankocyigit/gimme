package Commanders;

import Users.User;

import java.util.Iterator;

public class RemoveUserCommand implements Command {
    User user;
    public RemoveUserCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {
        databaseManager.removeUser(user);
    }
}
