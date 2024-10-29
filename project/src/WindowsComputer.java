public class WindowsComputer extends Computer {
    public WindowsComputer(String model, String ram, String storage) {
        super(model, ram, storage);
    }

    @Override
    public void getSpecifications() {
        System.out.println("Windows Computer Model: " + model);
        System.out.println("RAM: " + ram);
        System.out.println("Storage: " + storage);
    }
}