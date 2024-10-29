public class ComputerFactory {
    public static Computer createComputer(String type, String model, String ram, String storage) {
        if ("Windows".equalsIgnoreCase(type)) {
            return new WindowsComputer(model, ram, storage);
        } else if ("Mac".equalsIgnoreCase(type)) {
            return new MacComputer(model, ram, storage);
        }
        return null;
    }
}
