package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest1 {


    /**
     * 反射中常用到的几个类
     * Class：反射的核心类，可以获取类的属性、方法等信息
     * Field：类的成员变量
     * Method：类的方法
     * Constructor：表示类的构造方法
     */
    private void execute() throws ClassNotFoundException {
        Class clazz = Class.forName("reflect.Student");
        //获取某个Class所有的方法信息
        Method[] methods = clazz.getMethods();
        for(Method method:methods){
            System.out.println(method.toString());
        }

        //获取某个Class所有的成员属性信息
        Field[] fields = clazz.getFields();
        for(Field field:fields){
            System.out.println(field.toString());
        }

        Constructor[] constructors = clazz.getConstructors();
        for(Constructor constructor:constructors){
            System.out.println(constructor.toString());
        }
    }


    /**
     * 三种方式得到一个Class对象
     * 1.调用Class.forName方法（推荐）
     * 2.调用对象的getClass() student.getClass()方法
     * 3.Student.class
     */
    private void createNewInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz  = Class.forName("reflect.Student");
        Student student = (Student) clazz.getDeclaredConstructor().newInstance();
        student.setName("XiaoMing");
        Class clazz2 = student.getClass();
        Class clazz3 = Student.class;
        System.out.println(student.getName());
    }


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ReflectTest1 reflect = new ReflectTest1();
        //reflect.execute();
        reflect.createNewInstance();
    }
}
