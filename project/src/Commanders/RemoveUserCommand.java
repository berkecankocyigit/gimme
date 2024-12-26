package src.Commanders;

import src.Users.User;

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
