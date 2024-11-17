package Computers;

import java.util.Iterator;
import java.util.Set;

public class ComputerIterator implements Iterator {
    private Computer[] computers;
    private int index = 0;

    public ComputerIterator(Set<Computer> computers) {
        this.computers = computers.toArray(new Computer[0]);
    }

    @Override
    public boolean hasNext() {
        if (index < computers.length) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object next() {
        Computer computer = computers[index];
        index++;
        return computer;
    }
}
