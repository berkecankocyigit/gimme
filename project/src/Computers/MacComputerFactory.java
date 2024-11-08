package Computers;

public class MacComputerFactory implements IComputerFactory {
    public MacComputer createComputer(ComputerType computerType,int id, String model, String ram, String storage) {
        return new MacComputer(id, model, ram, storage);
    }
}
