package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Fixed {


    /**
     * 实现Callable接口实现有返回值的多线程
     * 利用对象Future的get方法获取到Callable任务返回的Object
     */
    private static class MyCallable implements Callable{

        String str;

        MyCallable(String str){
            this.str = str;
        }

        @Override
        public Object call() throws Exception {
            return this.str;
        }
    }

    private static int taskSize = 10;

    private void execute() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(taskSize);
        List<Future> list = new ArrayList<>();
        for(int i = 0;i<taskSize;i++){
            Callable callable = new MyCallable(i + " ");
            Future f = executorService.submit(callable);
            list.add(f);
        }
        executorService.shutdown();
        for(Future f:list){
            System.out.println("res:" + f.get().toString());
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Fixed fixed = new Fixed();
        fixed.execute();
    }

}
