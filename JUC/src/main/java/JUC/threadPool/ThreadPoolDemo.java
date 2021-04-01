package JUC.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {



    public static void main(String[] args) {
        //一个线程池有5个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //模拟10个用户来办理业务，每个用户就是一个外部的请求线程
        try{
            for (int i = 0; i <10 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

}
