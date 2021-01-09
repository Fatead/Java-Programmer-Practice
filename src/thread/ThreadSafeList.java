package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全的List
 * 1.vector
 * 2.Collections.synchronizedList
 * 3.copyOnWriteArrayList
 */
public class ThreadSafeList {


    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        Map<Integer,Integer> map = new ConcurrentHashMap<>();
        Lock lock = new ReentrantLock();
        lock.lock();
    }

}
