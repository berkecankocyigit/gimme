package Computers;

public class ComputerFactory implements IComputerFactory {

    public ComputerFactory() {
        System.out.println("ComputerFactory is created!");
    }

    @Override
    public Computer createComputer(ComputerType computerType, int id, String model, String ram, String storage) {
        switch (computerType) {
            case WindowsComputer:
                return new WindowsComputer(id, model, ram, storage);
            case MacComputer:
                return new MacComputer(id, model, ram, storage);
            default:
                return null;
        }
    }
}
