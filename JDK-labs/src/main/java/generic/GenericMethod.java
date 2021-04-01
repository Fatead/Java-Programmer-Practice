package generic;

import reflect.Student;

public class GenericMethod {


    /**
     * @param array
     * @param <E>
     *     泛型方法
     */
    public static <E> void printArray(E[] array){
        for(E element:array){
            System.out.println(element.toString());
        }
    }

    public static void main(String[] args) {
        String[] strings = {"waasd","asfa","sada"};
        printArray(strings);
        Student student1 = new Student("Xiao",21,34);
        Student student2 = new Student("asfa",20,35);
        Student[] students = {student1,student2};
        printArray(students);
    }

}
