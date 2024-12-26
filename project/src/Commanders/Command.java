package src.Commanders;

import src.Database.DatabaseManager;


public interface Command {
    DatabaseManager databaseManager = new DatabaseManager();
    void execute();
}
