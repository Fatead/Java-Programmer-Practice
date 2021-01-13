package JUC.prodConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用阻塞队列实现生产者消费者
 */
public class ProdConsumerBlockQueue {

    static class SharedResource{

        //默认开启，进行生产和消费
        private volatile boolean FLAG = true;
        private AtomicInteger atomicInteger = new AtomicInteger();
        BlockingQueue<String> blockingQueue = null;

        SharedResource(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        void produce() throws InterruptedException {
            String data = null;
            boolean returnValue;
            while(FLAG){
                atomicInteger.incrementAndGet();
                data = atomicInteger.incrementAndGet() + "";
                returnValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
                if(returnValue){
                    System.out.println(Thread.currentThread().getName() + "插入队列" + data + "成功");
                }else {
                    System.out.println(Thread.currentThread().getName() + "插入队列" + data + "失败");
                }
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println(Thread.currentThread().getName() + "大老板叫停了，flag = false，生产动作结束");
        }

        void consume() throws InterruptedException{
            String result = null;
            while(FLAG){
                result = blockingQueue.poll(2L,TimeUnit.SECONDS);
                if(null == result||result.equalsIgnoreCase("")){
                    FLAG = false;
                    System.out.println(Thread.currentThread().getName() + "超过两秒钟没有取到蛋糕，消费退出");
                    System.out.println();
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "消费队列" + result + "成功");
            }
        }


        void stop() throws Exception{
            this.FLAG = false;
        }

    }

    public static void main(String[] args) throws Exception {
        SharedResource sharedResource = new SharedResource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            try {
                sharedResource.produce();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(()->{
            try {
                sharedResource.consume();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"Consumer").start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println();
        sharedResource.stop();
    }

}
