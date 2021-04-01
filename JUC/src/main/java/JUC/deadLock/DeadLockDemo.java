package JUC.deadLock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁是两个或两个以上的进程在执行过程中，因争夺资源造成的一种相互等待的现象
 * 死锁的四个条件
 * 1.请求并保持
 * 2.不可剥夺
 * 3.互斥，一个资源只能给一个进程使用
 * 4.循环等待
 *
 * 死锁的排查
 * 1.jps命令定位进程号
 * 2.jstack找到死锁查看
 *
 */

public class DeadLockDemo {

    static class HoldLockThread implements Runnable{

        private String lockA;
        private String lockB;

        public HoldLockThread(String lockA, String lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override
        public void run() {
            synchronized (lockA){
                System.out.println(Thread.currentThread().getName() + "自己持有" + lockA + "尝试获得" + lockB);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB){
                    System.out.println(Thread.currentThread().getName() + "自己持有" + lockB + "尝试获得" + lockA);
                }
            }
        }

    }

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"ThreadA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadB").start();
    }

}