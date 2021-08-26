package JUC.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用AtomicInteger实现线程A、B、C交替打印
 */
public class ThreadABC {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public class ThreadA extends Thread{

        public void run(){
            while (true){
                while (atomicInteger.get()%3 !=0){

                }
                System.out.println("A");
                atomicInteger.getAndIncrement();
            }
        }

    }

    public class ThreadB extends Thread{

        public void run(){
            while (true){
                while (atomicInteger.get()%3 !=1){

                }
                System.out.println("B");
                atomicInteger.getAndIncrement();
            }
        }

    }


    public class ThreadC extends Thread{

        public void run(){
            while (true){
                while (atomicInteger.get()%3 !=2){

                }
                System.out.println("C");
                atomicInteger.getAndIncrement();
            }
        }

    }


    public void run(){
        Thread threadA = new ThreadA();
        Thread threadB = new ThreadB();
        Thread threadC = new ThreadC();
        threadA.start();
        threadB.start();
        threadC.start();
    }

    public static void main(String[] args) {
        ThreadABC threadABC = new ThreadABC();
        threadABC.run();
    }

}