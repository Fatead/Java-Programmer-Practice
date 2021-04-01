package JUC;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableDemo {

    static class MyThread implements Runnable{

        @Override
        public void run() {

        }
    }


    static class MyThread2 implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            return null;
        }
    }


    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        Thread t1 = new Thread(futureTask,"AA");
        t1.start();
    }

}
