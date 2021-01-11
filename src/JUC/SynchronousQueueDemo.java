package JUC;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        for (int i = 0; i < 1; i++) {
             new Thread(()->{
                 try {
                     blockingQueue.put("1");
                     System.out.println("put");
                     blockingQueue.put("2");
                     System.out.println("put");
                     blockingQueue.put("3");
                     System.out.println("put");
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             },String.valueOf(i)).start();
        }


        for (int i = 0; i < 1; i++) {
             new Thread(()->{
                 try {
                     Thread.sleep(1000);
                     blockingQueue.take();
                     Thread.sleep(1000);
                     blockingQueue.take();
                     Thread.sleep(1000);
                     blockingQueue.take();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             },String.valueOf(i)).start();
        }
    }

}
