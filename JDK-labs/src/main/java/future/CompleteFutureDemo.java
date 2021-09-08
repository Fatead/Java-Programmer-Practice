package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompleteFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = new CompletableFuture<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            try {
                Thread.sleep(3000);
                completableFuture1.complete("异步执行结果");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<String> completableFuture2 = completableFuture1.whenComplete((s, throwable) -> System.out.println("当异步任务执行完毕时打印异步任务的执行结果: " + s));
        executorService.shutdown();
    }


}
