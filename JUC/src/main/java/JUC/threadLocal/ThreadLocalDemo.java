package JUC.threadLocal;

public class ThreadLocalDemo {

    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    public static void print(String str){
        System.out.println(str + ":" + localVariable.get());
        localVariable.remove();
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("ThreadA local variable");
                print("ThreadA");
                System.out.println("threadA after remove:" + localVariable.get());
            }

        },"ThreadA");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("ThreadB local variable");
                print("ThreadB");
            }

        },"ThreadB");

        thread1.start();
        thread2.start();

    }



}
