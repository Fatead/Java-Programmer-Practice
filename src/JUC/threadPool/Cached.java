package JUC.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池execute 和submit的区别
 * 1.有无返回值
 * 2.
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
