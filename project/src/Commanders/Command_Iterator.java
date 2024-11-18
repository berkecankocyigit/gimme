package Commanders;

import Database.DatabaseManager;

import java.util.Iterator;

public interface Command_Iterator {
    DatabaseManager databaseManager = new DatabaseManager();
    Iterator execute();
}
