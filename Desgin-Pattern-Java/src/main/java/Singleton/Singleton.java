package Singleton;

/**
 * 懒汉式的实现方式，利用双重加锁机制实现线程安全
 */
public class Singleton {

    private static Singleton instance = null;

    /**
     * 将构造方法私有化，外部不能访问
     */
    private Singleton(){

    }

    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
