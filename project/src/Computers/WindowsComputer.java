package src.Computers;


public class WindowsComputer extends Computer {
    public WindowsComputer(int id, String model, String ram, String storage) {
        super(id, model, ram, storage);
    }

    @Override
    public void getSpecifications() {
        System.out.println("ID: " + id + " - Windows Computer Model: " + model + " | RAM: " + ram + " | Storage: " + storage + " | Availability: " + this.state);
    }

}