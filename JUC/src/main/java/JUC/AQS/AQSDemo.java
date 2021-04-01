package JUC.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 抽象队列同步器
 * 抢到资源的线程直接使用资源处理业务逻辑，抢不到资源的涉及一种排队等候机制，抢占资源失败
 * 的线程继续去等待，但等待线程仍然保留获取锁的可能且获取锁的流程仍在继续
 *
 * 如果共享资源被占用，就需要一定的阻塞唤醒机制来保证锁的分配，这个机制主要用的是CLH队列的变体来实现的，
 * 将暂时获取不到锁的线程加入到队列中，这个队列就是AQS的抽象表现。
 * 它将请求资源的线程封装成队列的节点（Node）,通过CAS、自旋以及LockSupport.part()等方式维护state变量的状态，使并发达到同步的控制效果
 * 有阻塞就需要排队，实现排队必然需要队列
 * state变量+ CLH变种的双端队列
 */
public class AQSDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        //第一个顾客来到银行办理业务
        new Thread(()->{
            lock.lock();
            try {
                System.out.println("A thread come in...");
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"A").start();

        //第二个顾客来到银行办理业务，但是因为受理业务的窗口只有一个（只能有一个线程持有锁），所以B需要等待，进入候客区
        new Thread(()->{
            lock.lock();
            try {
                System.out.println("B thread come in...");
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"B").start();

        //第三个顾客来到银行办理业务，但是因为受理业务的窗口只有一个（只能有一个线程持有锁），所以C需要等待，进入候客区
        new Thread(()->{
            lock.lock();
            try {
                System.out.println("C thread come in...");
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"C").start();

    }



}
