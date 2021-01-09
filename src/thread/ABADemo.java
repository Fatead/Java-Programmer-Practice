package thread;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        //ABA问题的产生

        new Thread(()->{
            System.out.println(atomicReference.compareAndSet(100, 101));
            System.out.println(atomicReference.compareAndSet(101, 100));
        },"t1").start();

        new Thread(()->{
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019));
        },"t2").start();


        //ABA问题的解决
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 第一次版本号" + stamp);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "第二次版本号 " + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "第三次版本号 " + atomicStampedReference.getStamp());
        },"t3").start();


        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 第二次版本号" + stamp);
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t4").start();
    }

}
