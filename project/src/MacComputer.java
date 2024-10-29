public class MacComputer extends Computer {
    public MacComputer(String model, String ram, String storage) {
        super(model, ram, storage);
    }

    @Override
    public void getSpecifications() {
        System.out.println("Mac Computer Model: " + model);
        System.out.println("RAM: " + ram);
        System.out.println("Storage: " + storage);
    }
}