package JVM;

/**
 * 测试Java中主要的三种类加载器：引导类加载器、扩展类加载器和系统类加载器
 *
 * 引导类加载器（启动类加载器Bootstrap ClassLoader）使用C/C++语言实现，嵌套在JVM内部，它用来加载Java的核心类库，用于提供JVM自身需要的类，
 * 加载扩展类和应用程序类加载器，并指定为他们的父类加载器，但本身并不继承自java.lang.ClassLoader，没有父加载器
 *
 * 扩展类加载器（Extension ClassLoader）:Java语言编写，由sun.misc.Launcher$ExtClassLoader实现，派生于ClassLoader类，其父加载器为启动类加载器
 *
 * 应用程序类加载器（系统类加载器）：Java语言编写，由sun.misc.Launcher$AppClassLoader实现，派生于ClassLoader类，其父类加载器为扩展类加载器，
 * 是程序中默认的的类加载器，一般来说Java应用的类都是由它来完成加载的。通过ClassLoader#getSystemClassLoader()方法可以获得该类加载器
 *
 * 为什么要自定义类加载器：
 * 1. 隔离类加载器
 * 2. 修改类加载的方式
 * 3. 扩展加载源
 * 4. 防止源码泄露
 */
public class ClassLoaderDemo {


    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        //获取其上层：扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);

        //获取当前用户自定义类的类加载器
        //对于用户自定义类来说，默认使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
        System.out.println(classLoader);

        //获取不到引导类加载器，说明String使用引导类加载器进行加载 --->java 的核心类库都是使用引导类加载器进行加载
        ClassLoader stringClassLoader = String.class.getClassLoader();
        System.out.println(stringClassLoader); //null


    }

}
