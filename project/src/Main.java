import src.Computers.Computer;
import src.Job.JobSchedular;
import src.Users.Admin;
import src.Users.Student;
import io.javalin.Javalin;

import java.util.ArrayList;

public class Main {
    static ArrayList<src.Users.User> users = new ArrayList<>();
    static ArrayList<Computer> computers = new ArrayList<>();
    static JobSchedular jobSchedular = new JobSchedular();
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
        //check the user on the
        app.post("/login", ctx -> {
            String mail = ctx.queryParam("mail");
            String password = ctx.queryParam("password");
            System.out.println("Mail: "+mail);
            System.out.println("Password: "+password);

            for (src.Users.User user : users) {
                System.out.println(user.getEmail());
                System.out.println(user.getPassword());
                if (user.getEmail().equals(mail) && user.getPassword().equals(password)) {
                    // User found
                    //create json from user with getters without using gson or jackson
                    //String type = "user" if user.getType()=="Student" else"admin"
                    String type = user.getType();

                    if (type.equals("Student")){
                        type = "user";
                    }else{
                        type = "admin";
                    }
                    String json = "{";
                    json += "\"id\":" + user.getId() + ",";
                    json += "\"name\":\"" + user.getName() + "\",";
                    json += "\"email\":\"" + user.getEmail() + "\",";
                    json += "\"type\":\"" + type+ "\",";
                    json += "\"assignedComputers\":[";
                    if(type=="user"){
                        for (String computer : ((Student) user).getComputerIds()) {
                            //just computer name
                        json += "\""+computer+"\"";
                        if (!((Student) user).getComputerIds().get(((Student) user).getComputerIds().size()-1).equals(computer)){
                            json += ",";
                        }
                        }
                    }
                    json += "]";

                    json += "}";

                    ctx.json(json);

                    return;
                }
            }
            ctx.json("User not found");
                });

        // Example of a route with a path parameter
        app.get("/getStats", ctx -> {
           //promise is :  totalUsers: number; activeComputers: number; runningJobs: number
            int totalUsers = users.size();
            int activeComputers = 0;
            int runningJobs = 0;
            for (src.Users.User user : users) {
                if (user.getType().equals("Student")) {
                    //Set<Computer> assignedComputers = ((Student) user).getAssignedComputers();

                    for(Computer computer : ((Student) user).getAssignedComputers()){
                        if(computer.getState().equals(src.Computers.ComputerState.AVAILABLE) || computer.getState().equals(src.Computers.ComputerState.BUSY)){
                            activeComputers++;
                            for (src.Job.Job job : computer.getAssignedJobs()) {
                                if (job.getStatus().equals(src.Job.JobState.RUNNING)||job.getStatus().equals(src.Job.JobState.PENDING)){
                                    runningJobs++;
                                }
                            }
                        }
                    }

                }
            }
            String json = "{";
            json += "\"totalUsers\":" + totalUsers + ",";
            json += "\"activeComputers\":" + activeComputers + ",";
            json += "\"runningJobs\":" + runningJobs;
            json += "}";
            ctx.json(json);
        }   );

        app.get("/getJobs", ctx -> {
            //Single job is : id: number; codePath: string; command: string; arguments: string[]; computerId: string; status: string; user: string; task: string
            String json = "[";
            for (src.Users.User user : users) {
                if (user.getType().equals("Student")) {
                    for(Computer computer : ((Student) user).getAssignedComputers()){
                        for (src.Job.Job job : computer.getAssignedJobs()) {
                            json += "{";
                            json += "\"id\":" + job.getId() + ",";
                            json += "\"codePath\":\"" + job.getCodePath() + "\",";
                            json += "\"command\":\"" + job.getCommand() + "\",";
                            json += "\"arguments\":[";
                            for (String argument : job.getArguments()) {
                                json += "\""+argument+"\"";
                                if (!job.getArguments().get(job.getArguments().size()-1).equals(argument)){
                                    json += ",";
                                }
                            }
                            json += "],";
                            json += "\"computerId\":\"" + job.getComputer().getId() + "\",";
                            json += "\"status\":\"" + job.getStatus() + "\",";
                            json += "\"user\":\"" + user.getName() + "\",";
                            json += "\"task\":\"" + job.getTask() + "\"";
                            json += "}";
                            if (!((Student) user).getAssignedComputers().get(((Student) user).getAssignedComputers().size()-1).getAssignedJobs().get(((Student) user).getAssignedComputers().get(((Student) user).getAssignedComputers().size()-1).getAssignedJobs().size()-1).equals(job)){
                                json += ",";
                            }
                        }
                    }
                }
            }


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
        src.Computers.LinuxComputerFactory linuxComputerFactory = new src.Computers.LinuxComputerFactory();
        Admin admin = new Admin(0, "admin", "admin@ozu", "admin");



        // Öğrencileri oluştur
        Student student1 = new Student(1, "student1", "s1@ozu", "pass");
        Student student2 = new Student(2, "student2", "s2@ozu", "pass");

        // Windows bilgisayarları oluştur
        Computer windowsComputer1 = linuxComputerFactory.createComputer(1, "Dell XPS", "16GB", "512GB SSD");
        Computer windowsComputer2 = linuxComputerFactory.createComputer(2, "HP Pavilion", "8GB", "256GB SSD");
        computers.add(windowsComputer1);
        computers.add(windowsComputer2);
        student1.addAssignedComputer(windowsComputer1);
        student1.addAssignedComputer(windowsComputer2);
/*
        try {
            // 2 saniye (2000 milisaniye) boyunca uyut
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Thread uyku sırasında bir başka thread tarafından
            // "interrupt" edilirse bu istisna fırlar
            e.printStackTrace();
        }
        student1.addAssignedJob(1,"/home/cagatay/Desktop/CS534/Proje1" ,"python3", "train.py", windowsComputer1);
        student1.addAssignedJob(2,"/home/cagatay/Desktop/CS534/Proje1" ,"python3", "main.py", windowsComputer1);
        }*/
        users.add(student1);
        users.add(student2);
        users.add(admin);

        student1.addAssignedJob(1,"", "pwd", "", windowsComputer1);
        student1.addAssignedJob(2, "","python3", "main.py", windowsComputer1);


    }
}
