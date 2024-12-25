package Computers;

public class WindowsComputerFactory implements IComputerFactory {
    @Override
    public WindowsComputer createComputer(int id, String model, String ram, String storage) {
        return new WindowsComputer(id, model, ram, storage);
    }
}
