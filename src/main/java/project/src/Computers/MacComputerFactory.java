package Computers;

public class MacComputerFactory implements IComputerFactory {
    @Override
    public MacComputer createComputer(int id, String model, String ram, String storage) {
        return new MacComputer(id, model, ram, storage);
    }
}
