package Computers;

public interface IComputerFactory {
    Computer createComputer(int id, String model, String ram, String storage);
}
