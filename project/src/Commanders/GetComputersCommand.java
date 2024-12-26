package src.Commanders;

import java.util.Iterator;

public class GetComputersCommand implements Command_Iterator {

    public Iterator execute(){
        return databaseManager.getComputers();
    }
}
