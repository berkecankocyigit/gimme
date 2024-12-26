package src.Users;

import src.Computers.Computer;
import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;

@Getter
@Setter
public abstract class User {
    private int id;
    private String name;
    private String email;
    private String password;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void printComputers(Iterator computers) {
        while (computers.hasNext()) {
            Computer computer = (Computer) computers.next();
            computer.getSpecifications();
        }
    }
    public abstract String getStatus();

}
