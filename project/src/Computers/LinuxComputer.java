package src.Computers;


public class LinuxComputer extends Computer {
    public LinuxComputer(int id, String model, String ram, String storage) {
        super(id, model, ram, storage);
    }

    @Override
    public void getSpecifications() {
        System.out.println("ID: " + id + " - Linux Computer Model: " + model + " | RAM: " + ram + " | Storage: " + storage + " | Availability: " + this.state);
    }

}