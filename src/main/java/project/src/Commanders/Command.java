package Commanders;

import Database.DatabaseManager;

import java.util.Iterator;


public interface Command {
    DatabaseManager databaseManager = new DatabaseManager();
    void execute();
}
