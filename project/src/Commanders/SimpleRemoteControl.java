package Commanders;

import java.util.Iterator;

public class SimpleRemoteControl {
    Command slot;
    Command_Iterator iterator;
    public SimpleRemoteControl() {}

    public void setSlot(Command command) {
        this.slot = command;
    }

    public void setIterator(Command_Iterator command) {
        this.iterator = command;
    }

    public void doIt(){
        this.slot.execute();
    }

    public Iterator get_Computer(){
        return this.iterator.execute();
    }


}
