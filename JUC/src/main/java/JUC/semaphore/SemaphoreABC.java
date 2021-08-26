package JUC.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 使用信号量实现A\B\C三个线程交替打印
 */
public class SemaphoreABC {

    private Semaphore A = new Semaphore(1);
    private Semaphore B = new Semaphore(0);
    private Semaphore C = new Semaphore(0);

    public class ThreadA extends Thread{

        public void run(){
            while (true){
                try {
                    A.acquire();
                    System.out.println("A");
                    B.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public class ThreadB extends Thread{

        public void run(){
            while (true){
                try {
                    B.acquire();
                    System.out.println("B");
                    C.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public class ThreadC extends Thread{

        public void run(){
            while (true){
                try {
                    C.acquire();
                    System.out.println("C");
                    A.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
        SemaphoreABC abcSemaphore = new SemaphoreABC();
        abcSemaphore.run();
    }

}
