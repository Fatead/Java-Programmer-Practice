package JUC.waitNotify;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 使用LockSupport可以实现先唤醒线程后阻塞线程
 * 因为unpark获得了一个凭证，之后再调用park方法，就可以消费凭证，故不会阻塞
 * 凭证的数量最多为1，连续调用两次unpark和调用一次unpark一样，只会增加一个凭证
 */
public class LockSupportDemo {


    public static void main(String[] args) throws InterruptedException {
        Thread a =  new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " come in");
            LockSupport.park();  //被阻塞，等待通知（唤醒）
            System.out.println(Thread.currentThread().getName() + " 被唤醒");
        },"A");
        a.start();
        TimeUnit.SECONDS.sleep(3);
        Thread b =  new Thread(()->{
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName() + " 发出通知");
        },"B");
        b.start();
    }

}
