package JUC.prodConsumer;

/**
 * 一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1，总共5轮
 * 1.线程   操作（方法）   资源类
 * 2.判断    干活         通知
 * 3.防止虚假唤醒机制
 */
public class prodConsumerTradition {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();


        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }

}