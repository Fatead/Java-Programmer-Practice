package JUC.threadPool;

import java.util.concurrent.*;

public class MyThreadPoolDemo {



    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try{
            for (int i = 0; i <12  ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

}
