package Users;

import Computers.Computer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

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

    public int getId(){return id;}
    public String getName(){return name;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}

    public abstract String getStatus();

}
