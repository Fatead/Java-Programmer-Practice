package JUC.waitNotify;

import java.util.concurrent.TimeUnit;


/**
 * 使用Object类中的wait和notify方法实现线程的等待和通知
 * --- wait---notify---synchronized三件套
 * notify动作需要在wait动作后面
 * 先wait后notify,notifyAll方法，等待中的线程才会被唤醒，否则无法唤醒
 * Object类中的wait、notify、notifyAll用于线程等待和唤醒的方法，都必须在synchronized内部执行（必须要用到关键字synchronized）
 */
public class waitNotifyDemo {

    private static Object objectLock = new Object();

    public static void main(String[] args) {

        new Thread(()->{

            synchronized (objectLock){
                System.out.println(Thread.currentThread().getName() + " come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "被唤醒");
            }
        },"A").start();

        new Thread(()->{

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (objectLock){
                objectLock.notify();
            }
            System.out.println(Thread.currentThread().getName() + "通知");
        },"B").start();

    }

}
