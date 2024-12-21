package Computers;

public class MacComputer extends Computer {
    public MacComputer(int id, String model, String ram, String storage) {
        super(id, model, ram, storage);
    }

    @Override
    public void getSpecifications() {
        System.out.println("Mac Computer Model: " + model + " | RAM: " + ram + " | Storage: " + storage );
    }
}