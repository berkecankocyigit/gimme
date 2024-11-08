package Computers;

public class WindowsComputerFactory implements IComputerFactory {
    public WindowsComputer createComputer(ComputerType computerType,int id, String model, String ram, String storage) {
        return new WindowsComputer(id, model, ram, storage);
    }
}
