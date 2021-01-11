package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("A"));
        System.out.println(blockingQueue.offer("A"));
        System.out.println(blockingQueue.offer("A"));
        System.out.println(blockingQueue.offer("A"));
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
    }

}
