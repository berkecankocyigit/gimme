public class ComputerFactory {
    public static Computer createComputer(int id, String type, String model, String ram, String storage) {
        if ("Windows".equalsIgnoreCase(type)) {
            return new WindowsComputer(id, model, ram, storage);
        } else if ("Mac".equalsIgnoreCase(type)) {
            return new MacComputer(id, model, ram, storage);
        }
        return null;
    }
}
