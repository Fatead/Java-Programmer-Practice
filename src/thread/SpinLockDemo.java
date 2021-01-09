package thread;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {


    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "加锁");
        while(!atomicReference.compareAndSet(null,thread)){

        }
    }


    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + "解锁");
    }


    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();

        },"AA").start();

        Thread.sleep(1000);

        new Thread(()->{
            spinLockDemo.myLock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        },"BB").start();

    }

}