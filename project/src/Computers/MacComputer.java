package Computers;

public class MacComputer extends Computer {
    public MacComputer(int id, String model, String ram, String storage) {
        super(id, model, ram, storage);
    }

    @Override
    public void getSpecifications() {
        System.out.println("Mac Computers.Computer Model: " + model);
        System.out.println("RAM: " + ram);
        System.out.println("Storage: " + storage);
    }
}