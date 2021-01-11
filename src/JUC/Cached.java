package JUC;

import java.util.concurrent.*;

public class Cached {

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
        Cached cached = new Cached();
        cached.execute();
    }

}
