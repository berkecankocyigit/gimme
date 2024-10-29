public abstract class Computer {
    protected String model;
    protected String ram;
    protected String storage;

    public Computer(String model, String ram, String storage) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
    }

    public abstract void getSpecifications();
}