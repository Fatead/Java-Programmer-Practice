package JUC.waitNotify;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lock--await---signal三件套实现线程的等待与唤醒
 */
public class ConditionDemo {

    private Object lockObject = new Object();

    public static void main(String[] args) {
        Lock lock  = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(()->{
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + " come in");
                condition.await();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"A").start();

        new Thread(()->{
            lock.lock();
            try{
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " 唤醒");
                condition.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"B").start();

    }
}
