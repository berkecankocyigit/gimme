import java.util.HashSet;
import java.util.Set;

public abstract class Computer {
    protected int id;
    protected String model;
    protected String ram;
    protected String storage;
    protected Boolean state = true;
    private Set<Student> assignedStudent = new HashSet<>();

    public Computer(int id, String model, String ram, String storage) {
        this.id = id;
        this.model = model;
        this.ram = ram;
        this.storage = storage;
    }

    public abstract void getSpecifications();
    public int getId() {return id;}
    public String getModel() {return model;}
    public String getRam() {return ram;}
    public String getStorage() {return storage;}

    public void getAssignedStudents() {
        System.out.println("Getting assigned computers of " + this.model);
        for (Student student : assignedStudent) {
            System.out.println(student.getName());
            System.out.println("-------------");
        }
    }
    public void addAssignedStudent(Student assignedStudent) {
        this.assignedStudent.add(assignedStudent);
    }

    public void removeAssignedStudent(Student assignedStudent) {
        this.assignedStudent.remove(assignedStudent);
    }

    public Boolean hasJob() {
        return state;
    }

}