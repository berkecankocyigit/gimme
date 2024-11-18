package Commanders;

import Computers.Computer;
import Computers.ComputerIterator;
import Computers.WindowsComputerFactory;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GetComputersCommand implements Command_Iterator {

    public Iterator execute(){
        return databaseManager.getComputers();
    }
}
