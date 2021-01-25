package JUC.threadPool;

import java.util.concurrent.*;

/**
 * 线程池的生命周期，总共有五种状态
 * 1.Running:能接受新提交的任务，并且也能处理阻塞队列中的任务
 * 2.ShutDown:关闭状态，不再接受新提交的任务，但却可以继续处理阻塞队列中已保存的任务，在线程池处于Running状态时，调用Shutdown()方法会使线程池
 * 进入到该状态
 * 3.Stop:不能接受新任务，也不能处理队列中的任务，会中断正在处理任务的线程。当线程处于Running或ShutDown状态时，调用ShutDownNow()方法会使
 * 线程池进入到该状态。
 * 4:Tidying:如果所有任务都已经终止了，WorkerCount(有效线程数)为0，线程池进入该状态后会调用terminated()方法进入Terminated状态
 * 5.Terminated:在terminated()方法执行完后进入该状态
 */
public class CachedDemo {

    private int taskNum = 3;

    private static class MyCallable implements Callable {

        String str;

        MyCallable(String str){
            this.str = str;
        }

        @Override
        public Object call() throws Exception {
            return this.str;
        }
    }


    public void execute() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0;i<taskNum;i++){
            Callable task = new MyCallable(i + " ");
            Future future =  executorService.submit(task);
            System.out.println(future.get().toString());
        }
        executorService.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CachedDemo cachedDemo = new CachedDemo();
        cachedDemo.execute();
    }

}
