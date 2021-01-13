package JUC.reentrantLock;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用ReentrantLock实现线程安全的list
 */
public class ReentrantLockList {

    private ArrayList<String> list;
    private ReentrantLock lock;

    public ReentrantLockList() {
        list = new ArrayList<>();
        lock = new ReentrantLock();
    }

    public void remove(String element){
        lock.lock();
        try {
            list.remove(element);
        }finally {
            lock.unlock();
        }
    }

    public String get(int index){
        lock.lock();
        try {
            return list.get(index);
        }finally {
            lock.unlock();
        }
    }

    public void add(String str){
        lock.lock();
        try {
            list.add(str);
        }finally {
            lock.unlock();
        }
    }

}
