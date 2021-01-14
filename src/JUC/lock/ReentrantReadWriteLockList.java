package JUC.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 利用ReentrantReadWriteLock实现的线程安全的list
 * ReentrantReadWriteLock使用AQS状态值的高16位表示获取到的读锁的个数，低16位表示获取写锁线程的可重入次数
 * 使用CAS对其操作实现了读写分离，适合于读多写少的情况
 */
public class ReentrantReadWriteLockList {

    private List<String> list = new ArrayList<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    /**
     * remove函数对于list进行修改，使用写锁
     */
    public void remove(String element){
        writeLock.lock();
        try {
            list.remove(element);
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * get使用读锁
     */
    public String get(int index){
        readLock.lock();
        try {
            return list.get(index);
        }finally {
            readLock.unlock();
        }
    }

    /**
     * 使用写锁
     */
    public void add(String string){
        writeLock.lock();
        try {
            list.add(string);
        }finally {
            writeLock.unlock();
        }
    }

}
