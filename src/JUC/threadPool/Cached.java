package JUC.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池execute()和submit()的区别
 * 1.有无返回值，execute只能提交Runnable类型的任务，无返回值。而submit既可以提交Runnable类型的任务，也可以提交
 * callable类型的任务，会有一个任务类型为Future的返回值，当任务类型为Runnable时，返回值为null。
 * 2.execute在执行任务时，如果遇到异常会直接抛出，而submit不会直接抛出异常，只有在使用Future的get方法获取返回值时，
 * 才会抛出异常。
 */
public class Cached {

    public static void main(String[] args) {
        //一个线程池有N个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //模拟10个用户来办理业务，每个用户就是一个外部的请求线程
        try{
            for (int i = 0; i <10 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
