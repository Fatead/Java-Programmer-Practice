package Singleton;

/**
 * 单例模式饿汉式的实现方式
 */
public class SingletonHungry {

    private static SingletonHungry  instance = new SingletonHungry();

    private SingletonHungry(){

    }

    public static SingletonHungry getInstance(){
        return instance;
    }

}
