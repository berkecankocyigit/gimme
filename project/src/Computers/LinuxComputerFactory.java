package src.Computers;

public class LinuxComputerFactory implements IComputerFactory {
    @Override
    public LinuxComputer createComputer(int id, String model, String ram, String storage) {
        return new LinuxComputer(id, model, ram, storage);
    }
}
