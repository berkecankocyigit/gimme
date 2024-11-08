package Computers;

public interface IComputerFactory {
    Computer createComputer(ComputerType computerType,int id, String model, String ram, String storage);
}
