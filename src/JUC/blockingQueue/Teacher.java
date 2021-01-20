package JUC.blockingQueue;

import java.util.concurrent.DelayQueue;

public class Teacher implements Runnable {

    private DelayQueue<Student> delayQueue;

    public Teacher(DelayQueue<Student> delayQueue) {
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println("Test start");
            while(!Thread.interrupted()){
                delayQueue.take().run();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
