package JUC.prodConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁ReentrantLock绑定了多个条件condition,可以精确唤醒，
 * 而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程
 */

public class SyncAndReentrantLockDemo {

    static class ShareResource{

        private int number = 1;
        private Lock lock = new ReentrantLock();
        private Condition c1 = lock.newCondition();
        private Condition c2 = lock.newCondition();
        private Condition c3 = lock.newCondition();

        public void print5(){
            lock.lock();
            try {
                //1.判断
                while (number!=1){
                    c1.await();
                }
                //2.干活
                for (int i = 0; i <5 ; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
                //修改标志位
                //3. 通知
                number = 2;
                c2.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public void print10(){
            lock.lock();
            try {
                while(number!=2){
                    c2.await();
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
                number = 3;
                c3.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public void print15(){
            lock.lock();
            try {
                while(number!=3){
                    c3.await();
                }
                for (int i = 0; i < 15; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
                number = 1;
                c1.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }


    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                shareResource.print5();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                shareResource.print10();
            }
        },"B").start();


        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                shareResource.print15();
            }
        },"C").start();
    }

}
