package JUC.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Single {

    public class Task extends Thread{

        public void run(){
            System.out.println("任务执行中...");
        }
    }

    private void execute(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Task task = new Task();
        executorService.submit(task);
        executorService.shutdown();
    }

    public static void main(String[] args) {
        Single single = new Single();
        single.execute();
    }

}
