

interface ActionOfProgrammer {
    void writeSomeCode();
    void goToDiner();
    void takeSalary();
}
class ProgrammerImpl implements ActionOfProgrammer {

    private int salary = 0;

    @Override
    public void writeSomeCode() {
        System.out.println("write some code");
    }

    @Override
    public void goToDiner() {
        System.out.println("going to diner ...");

    }

    @Override
    public void takeSalary() {
        salary += 25000;
    }

    public int getSalary() {
        return salary;
    }
}

public class TestClass {

    //    @TestAnnotation(name="testField", value = "asdasdasdsad")
    private String fieldTest = "test";
    private String name = "Maxim";
    private String test = "herny";

    public static void main(String[] args) {

        ProgrammerImpl programmer = new ProgrammerImpl();
        programmer.writeSomeCode();
        programmer.goToDiner();
        programmer.takeSalary();
//        System.out.println("программист 1 получил: "+ programmer.getSalary());
        programmer.writeSomeCode();
        programmer.goToDiner();
        programmer.takeSalary();
        System.out.println("программист 1 получил: "+ programmer.getSalary());


    }
}
