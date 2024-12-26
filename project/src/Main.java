import src.Computers.Computer;
import src.Computers.WindowsComputerFactory;
import src.Job.JobSchedular;
import src.Users.Admin;
import src.Users.Student;

import io.javalin.Javalin;


public class Main {
    public static void main(String[] args) {
        InitiateTheProgram();

        Javalin app = Javalin.create(config -> {
            // Set additional configurations if needed
            config.plugins.enableCors(cors -> cors.add(it -> it.anyHost())); // Enable CORS for all hosts
        });

        // Start the server on port 7000
        app.start(7000);

        // Define API routes
        app.get("/", ctx -> ctx.json("Welcome to Javalin API!"));
        app.get("/hello", ctx -> ctx.json("Hello, World!"));

        // Example of a route with a path parameter
        app.get("/user/{id}", ctx -> {
            String userId = ctx.pathParam("id");
            ctx.json("User ID: " + userId);
        });

        // Example of a POST route
        app.post("/create", ctx -> {
            String body = ctx.body();
            ctx.json("Received: " + body);
        });

        // Example of a 404 handler
        app.error(404, ctx -> ctx.json("Resource not found"));

    }

    public static void InitiateTheProgram(){
        // Factory ve Scheduler oluştur
        WindowsComputerFactory windowsComputerFactory = new WindowsComputerFactory();
        JobSchedular jobSchedular = new JobSchedular();
        Admin admin = new Admin(0, "admin", "admin@ozyegin.edu.tr", "admin");



        // Öğrencileri oluştur
        Student student1 = new Student(1, "student1", "student1@ozu.edu.tr", "s1_password");
        Student student2 = new Student(2, "student2", "student2@ozu.edu.tr", "s2_password");

        // Windows bilgisayarları oluştur
        Computer windowsComputer1 = windowsComputerFactory.createComputer(1, "Dell XPS", "16GB", "512GB SSD");
        Computer windowsComputer2 = windowsComputerFactory.createComputer(2, "HP Pavilion", "8GB", "256GB SSD");

        student1.addAssignedComputer(windowsComputer1);

        try {
            // 2 saniye (2000 milisaniye) boyunca uyut
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Thread uyku sırasında bir başka thread tarafından
            // "interrupt" edilirse bu istisna fırlar
            e.printStackTrace();
        }
        student1.addAssignedJob(1, "pwd", "", windowsComputer1);
        student1.addAssignedJob(2, "python3", "main.py", windowsComputer1);


    }
}
