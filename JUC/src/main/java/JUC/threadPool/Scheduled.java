package JUC.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduled {


    private int taskNum = 3;

    private class MyTask implements Runnable{

        @Override
        public void run() {
            System.out.println("延迟三秒");
        }

    }

    private void execute(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        List<Runnable> runnableList = new ArrayList<>();
        for(int i =0;i<taskNum;i++){
            Runnable task = new MyTask();
            runnableList.add(task);
        }
        for(Runnable runnable:runnableList){
            scheduledExecutorService.schedule(runnable,3, TimeUnit.SECONDS);
        }
        scheduledExecutorService.shutdown();
    }

    public static void main(String[] args) {
        Scheduled scheduled = new Scheduled();
        scheduled.execute();
    }

}
