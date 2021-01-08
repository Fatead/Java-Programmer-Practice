package reflect;

public class Student {
    private String name;
    private int age;
    private int ID;

    public String major;

    public Student(String name, int age, int ID) {
        this.name = name;
        this.age = age;
        this.ID = ID;
    }

    public Student(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString(){
        return "name:" + this.name + "\r\n" +
                "age:" + this.age + "\r\n" +
                "ID:" + this.ID;
    }
}
