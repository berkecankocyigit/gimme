import java.util.HashSet;
import java.util.Set;

public abstract class Computer {
    protected String model;
    protected String ram;
    protected String storage;
    private Set<Student> assignedStudent = new HashSet<>();

    public Computer(String model, String ram, String storage) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
    }

    public abstract void getSpecifications();

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

}