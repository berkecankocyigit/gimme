import src.Computers.Computer;
import src.Job.JobSchedular;
import src.Job.JobState;
import src.Users.Admin;
import src.Users.Student;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Main {
    static ArrayList<src.Users.User> users = new ArrayList<>();
    static ArrayList<Computer> computers = new ArrayList<>();
    static JobSchedular jobSchedular = new JobSchedular();
    public static void main(String[] args) {
        InitiateTheProgram();

        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> cors.add(it -> it.anyHost())); // Enable CORS for all hosts
        });

        app.start(7000);

        app.get("/", ctx -> {
            System.out.println("Received GET request at / - Returning welcome message.");
            ctx.json("Welcome to Javalin API!");
        });

        app.post("/login", ctx -> {
            System.out.println("Received POST request at /login");
            String mail = ctx.queryParam("mail");
            String password = ctx.queryParam("password");
            System.out.println("Login attempt: mail=" + mail + ", password=" + password);

            for (src.Users.User user : users) {
                System.out.println("Checking user: " + user.getEmail());
                if (user.getEmail().equals(mail) && user.getPassword().equals(password)) {
                    System.out.println("User found: " + user.getName());
                    String type = user.getType();
                    if (type.equals("Student")) {
                        type = "user";
                    } else {
                        type = "admin";
                    }

                    String json = "{\"user\":{";
                    json += "\"id\":" + user.getId() + ",";
                    json += "\"name\":\"" + user.getName() + "\",";
                    json += "\"email\":\"" + user.getEmail() + "\",";
                    json += "\"type\":\"" + type + "\",";
                    json += "\"assignedComputersIds\":[";

                    if (type.equals("user")) {
                        for (String computer : ((Student) user).getComputerIds()) {
                            json +=   computer ;
                            if (!((Student) user).getComputerIds().get(((Student) user).getComputerIds().size() - 1).equals(computer)) {
                                json += ",";
                            }
                        }
                    }

                    json += "]}";
                    json += ",\"token\":\"" + generateToken() + "\"}";
                    System.out.println("Returning user: " + json);
                    ctx.json(json);
                    return;
                }
            }
            System.out.println("Login failed: User not found");
            ctx.json("User not found");
        });

        app.get("/getStats", ctx -> {
            System.out.println("Received GET request at /getStats");
            int totalUsers = users.size();
            int activeComputers = 0;
            int runningJobs = 0;

            System.out.println("Calculating stats:");
            for (src.Users.User user : users) {
                System.out.println("Checking user: " + user.getName());
                if (user.getType().equals("Student")) {
                    for (Computer computer : ((Student) user).getAssignedComputers()) {
                        System.out.println("Checking computer: " + computer.getModel());
                        if (computer.getState().equals(src.Computers.ComputerState.AVAILABLE) || computer.getState().equals(src.Computers.ComputerState.BUSY)) {
                            activeComputers++;
                            for (src.Job.Job job : computer.getAssignedJobs()) {
                                System.out.println("Checking job: " + job.getId() + ", Status: " + job.getStatus());
                                if (job.getStatus().equals(src.Job.JobState.RUNNING) || job.getStatus().equals(src.Job.JobState.PENDING)) {
                                    runningJobs++;
                                }
                            }
                        }
                    }
                }
            }

            String json = "{\"stats\":{";
            json += "\"totalUsers\":" + totalUsers + ",";
            json += "\"activeComputers\":" + activeComputers + ",";
            json += "\"runningJobs\":" + runningJobs;
            json += "},\"token\":\"" + generateToken() + "\"}";
            System.out.println("Returning stats: " + json);
            ctx.json(json);
        });

        app.get("/getJobs", ctx -> {
            System.out.println("Received GET request at /getJobs");
            StringBuilder json = new StringBuilder("{ \"jobs\":[");

            for (src.Users.User user : users) {
                if (user.getType().equals("Student")) {
                    for (Computer computer : ((Student) user).getAssignedComputers()) {
                        for (src.Job.Job job : computer.getAssignedJobs()) {
                            System.out.println("Adding job to response: Job ID=" + job.getId());
                            json.append("{");
                            json.append("\"id\":").append(job.getId()).append(",");
                            json.append("\"codePath\":\"").append(job.getPath()).append("\",");
                                    json.append("\"command\":\"").append(job.getCommand()).append("\",");
                                            json.append("\"arguments\":\"").append(job.getArgs()).append("\",");
                                                    json.append("\"computerId\":\"").append(job.getComputerAssigned().getId()).append("\",");
                                                            json.append("\"status\":\"").append(job.getStatus()).append("\",");
                                                                    json.append("\"user\":\"").append(user.getName()).append("\"");
                            json.append("},");
                        }
                    }
                }
            }

            if (json.charAt(json.length() - 1) == ',') {
                json.deleteCharAt(json.length() - 1);
            }

            json.append("],\"token\":\"" + generateToken() + "\"}");
            System.out.println("Returning jobs: " + json);
            ctx.json(json.toString());
        });

        app.post("/getUserJobs", ctx -> {
            System.out.println("Received POST request at /getUserJobs");
            int userId = Integer.parseInt(ctx.queryParam("userId"));
            System.out.println("Fetching jobs for user ID: " + userId);

            StringBuilder json = new StringBuilder("{ \"jobs\":[");

            for (src.Users.User user : users) {
                if (user.getId() == userId) {
                    System.out.println("Found user: " + user.getName());
                    if (user.getType().equals("Student")) {
                        for (Computer computer : ((Student) user).getAssignedComputers()) {
                            for (src.Job.Job job : computer.getAssignedJobs()) {
                                System.out.println("Adding job: Job ID=" + job.getId());
                                json.append("{");
                                json.append("\"id\":").append(job.getId()).append(",");
                                json.append("\"codePath\":\"").append(job.getPath()).append("\",");
                                        json.append("\"command\":\"").append(job.getCommand()).append("\",");
                                                json.append("\"arguments\":\"").append(job.getArgs()).append("\",");
                                                        json.append("\"computerId\":\"").append(job.getComputerAssigned().getId()).append("\",");
                                                                json.append("\"status\":\"").append(job.getStatus()).append("\",");
                                                                        json.append("\"user\":\"").append(user.getName()).append("\"");
                                json.append("},");
                            }
                        }
                    }
                }
            }

            if (json.charAt(json.length() - 1) == ',') {
                json.deleteCharAt(json.length() - 1);
            }

            json.append("],\"token\":\"" + generateToken() + "\"}");
            System.out.println("Returning jobs: " + json);
            ctx.json(json.toString());
        });

        app.get("/getUsers", ctx -> {
            System.out.println("Received GET request at /getUsers");
            StringBuilder json = new StringBuilder("{ \"users\":[");

            for (src.Users.User user : users) {
                System.out.println("Adding user to response: " + user.getName());
                json.append("{");
                json.append("\"id\":").append(user.getId()).append(",");
                json.append("\"name\":\"").append(user.getName()).append("\",");
                        json.append("\"email\":\"").append(user.getEmail()).append("\",");
                                json.append("\"type\":\"").append(user.getType()).append("\"");
                if (user.getType().equals("Student")) {
                    json.append(",\"assignedComputerIds\":[");
                    for (String computer : ((Student) user).getComputerIds()) {
                        json.append(computer );
                        if (!((Student) user).getComputerIds().get(((Student) user).getComputerIds().size() - 1).equals(computer)) {
                            json.append(",");
                        }
                    }
                    json.append("]");
                }
                json.append("},");
            }

            if (json.charAt(json.length() - 1) == ',') {
                json.deleteCharAt(json.length() - 1);
            }

            json.append("],\"token\":\"" + generateToken() + "\"}");
            System.out.println("Returning users: " + json);
            ctx.json(json.toString());
        });

        app.post("/getUserComputers", ctx -> {
            System.out.println("Received POST request at /getUserComputers");
            int userId = Integer.parseInt(ctx.queryParam("userId"));
            System.out.println("Fetching computers for user ID: " + userId);

            StringBuilder json = new StringBuilder("{ \"computers\":[");

            for (src.Users.User user : users) {
                if (user.getId() == userId) {
                    System.out.println("Found user: " + user.getName());
                    if (user.getType().equals("Student")) {
                        for (Computer computer : ((Student) user).getAssignedComputers()) {
                            System.out.println("Adding computer: " + computer.getModel());
                            json.append("{");
                            json.append("\"id\":").append(computer.getId()).append(",");
                            json.append("\"type\":\"").append(computer.getClass().getSimpleName()).append("\",");
                                    json.append("\"name\":\"").append(computer.getModel()).append("\",");
                                            json.append("\"status\":\"").append(computer.getState()).append("\",");
                                                    json.append("\"currentJobs\":").append(computer.getAssignedJobs().size());
                            json.append("},");
                        }
                    }
                }
            }

            if (json.charAt(json.length() - 1) == ',') {
                json.deleteCharAt(json.length() - 1);
            }

            json.append("],\"token\":\"" + generateToken() + "\"}");
            System.out.println("Returning User computers: " + json);
            ctx.json(json.toString());
        });

        app.get("/getComputers", ctx -> {
            System.out.println("Received GET request at /getComputers");
            StringBuilder json = new StringBuilder("{ \"computers\":[");

            for (Computer computer : computers) {
                System.out.println("Adding computer to response: " + computer.getModel());
                json.append("{");
                json.append("\"id\":").append(computer.getId()).append(",");
                json.append("\"type\":\"").append(computer.getClass().getSimpleName()).append("\",");
                        json.append("\"name\":\"").append(computer.getModel()).append("\",");
                                json.append("\"status\":\"").append(computer.getState()).append("\",");
                                        json.append("\"currentJobs\":").append(computer.getAssignedJobs().size());
                json.append("},");
            }

            if (json.charAt(json.length() - 1) == ',') {
                json.deleteCharAt(json.length() - 1);
            }

            json.append("],\"token\":\"" + generateToken() + "\"}");
            System.out.println("Returning computers: " + json);
            ctx.json(json.toString());
        });

        app.post("/createJob", ctx -> {
            System.out.println("Received POST request at /createJob");
            int userId = Integer.parseInt(ctx.queryParam("userId"));
            int computerId = Integer.parseInt(ctx.queryParam("computerId"));
            String path = ctx.queryParam("path");
            String command = ctx.queryParam("command");
            String arguments = ctx.queryParam("arguments");

            System.out.println("Creating job for User ID=" + userId + ", Computer ID=" + computerId);

            src.Users.User user = null;
            Computer computer = null;

            for (src.Users.User u : users) {
                System.out.println("Checking user: " + u.getName());
                if (u.getId() == userId) {
                    user = u;
                    break;
                }
            }

            for (Computer c : computers) {
                System.out.println("Checking computer: " + c.getModel());
                if (c.getId() == computerId) {
                    computer = c;
                    break;
                }
            }

            if (user == null || computer == null) {
                System.out.println("User or computer not found.");
                ctx.json("User or computer not found");
                return;
            }

            int id = 0;
            for (Computer comp : jobSchedular.getActiveComputers()) {
                for (src.Job.Job job : comp.getAssignedJobs()) {
                    if (job.getId() > id) {
                        id = job.getId();
                    }
                }
            }
            id++;

            src.Job.Job job = new src.Job.Job(id, path, command, arguments, user, computer);
            computer.addAssignedJob(job);
            System.out.println("Job created: ID=" + job.getId());
            ctx.json("{\"status\":true}");
        });

        app.post("/addUser", ctx -> {
            System.out.println("Received POST request at /addUser");
            String name = ctx.queryParam("name");
            String email = ctx.queryParam("email");
            String password = ctx.queryParam("password");

            System.out.println("Adding user: Name=" + name + ", Email=" + email);

            int id = 0;
            for (src.Users.User user : users) {
                if (user.getId() > id) {
                    id = user.getId();
                }
            }
            id++;

            src.Users.User user = new src.Users.Student(id, name, email, password);
            users.add(user);
            System.out.println("User added: ID=" + user.getId());
            ctx.json("{\"status\":true}");
        });

        app.post("/removeUser", ctx -> {
            System.out.println("Received POST request at /removeUser");
            int userId = Integer.parseInt(ctx.queryParam("userId"));

            System.out.println("Removing user with ID=" + userId);

            src.Users.User user = null;
            for (src.Users.User u : users) {
                if (u.getId() == userId) {
                    user = u;
                    break;
                }
            }

            if (user == null) {
                System.out.println("User not found.");
                ctx.json("{\"status\":false}");
                return;
            }

            users.remove(user);
            System.out.println("User removed: ID=" + userId);
            ctx.json("{\"status\":true}");
        });

        app.post("/editUser", ctx -> {
            System.out.println("Received POST request at /editUser");
            // Get the user ID from the request
            int userId = Integer.parseInt(ctx.queryParam("userId"));
            // Get the user name from the request
            String name = ctx.queryParam("name");
            // Get the users assigned computer from the request
            String computerIdString = ctx.queryParam("computerIds");
            System.out.println("Computer Ids: " + computerIdString);
            ArrayList<Integer> assignedComputerIds = new ArrayList<>();
            if (computerIdString != null) {
                String[] computerIds = computerIdString.split(",");
                System.out.println("Computers: " + computerIds);
                for (String computerId : computerIds) {
                    System.out.println("Adding computer: " + computerId);
                    assignedComputerIds.add(Integer.parseInt(computerId));
                }
            }


            src.Users.User user = null;
            for (src.Users.User u : users) {
                if (u.getId() == userId) {
                    System.out.println("Editing user: " + u.getName());
                    user = u;
                    break;
                }
            }

            // Check if the user exists
            if (user == null) {
                System.out.println("User not found.");
                ctx.json("{\"status\":false}");
                return;
            }

            // Edit the user if the values are not null
            if (name != null) user.setName(name);
            if(assignedComputerIds != null){
                System.out.println("Assigned computers: " + assignedComputerIds);
                //Set<Computer> emptyAssign = new Set<>();
                ((Student) user).setAssignedComputers(new HashSet<>());
                for (int computerId : assignedComputerIds) {
                    for (Computer computer : computers) {
                        if (computer.getId() == computerId) {
                            System.out.println("Adding computer: " + computer.getModel());
                            ((Student) user).addAssignedComputer(computer);
                        }
                    }
                }
            }
            ctx.json("{\"status\":true}");
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
        */

        //student1.addAssignedJob(1,"/home/cagatay/Desktop/CS534/Proje1" ,"python3", "train.py", windowsComputer1);
        //student1.addAssignedJob(2,"/home/cagatay/Desktop/CS534/Proje1" ,"python3", "main.py", windowsComputer1);

        users.add(student1);
        users.add(student2);
        users.add(admin);

        //get jobn from computer
        //windowsComputer1.getAssignedJobs().iterator().next().setStatus(JobState.SUCCESS);

    }
    private static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
