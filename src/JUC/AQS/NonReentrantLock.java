package JUC.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自己基于AQS实现一个不可重入的独占锁
 * state为0表示目前锁没有被线程持有，state为1表示锁已经被某一个线程持有
 */
public class NonReentrantLock implements Lock,java.io.Serializable{

    /**
     * 锁的内部辅助类
     */
    private static class Sync extends AbstractQueuedSynchronizer{

        /**
         * 锁是否已经被持有，AQS中的state等于1表示锁已经被另一个线程所持有
         */
        protected boolean isHeldExclusively(){
            return getState() == 1;
        }


        /**
         * 如果state为0，则尝试获取锁，并将state修改为1
         */
        public boolean tryAcquire(int acquires){
            assert acquires == 1;
            //当前state为0则将其设置为1
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }


        /**
         * 尝试释放锁，并将state设置为0
         */
        protected boolean tryRelease(int releases){
            assert releases == 1;
            if(getState() == 0)
                throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        Condition newCondition(){
            return new ConditionObject();
        }

    }

    //创建一个Sync来做具体的工作
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.tryAcquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long l, TimeUnit timeUnit) throws InterruptedException {
        return sync.tryAcquireNanos(1,timeUnit.toNanos(l));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked(){
        return sync.isHeldExclusively();
    }

}
