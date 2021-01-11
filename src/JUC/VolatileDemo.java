package JUC;

/**
 * 验证volatile的内存可见性
 */
public class VolatileDemo {

    static class MyData{

        volatile int number = 0;

        public void addTo60(){
            this.number = 60;
        }

        public void addPlusPlus(){
            number++;
        }

    }

    public static void main(String[] args) {
        MyData myData = new MyData();
        for(int i = 0;i<20;i++){
            new Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    myData.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }
        //等待上面计算完全
        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "final value: " + myData.number);
    }

    /**
     * 验证volatile的内存可见性
     */
    private static void volatileMemory() {
        MyData myData = new MyData();    //资源类
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " come in");
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + " number:" + myData.number);
        },"AAA").start();

        while(myData.number == 0){
            //main线程就循环
        }
        System.out.println("...");
    }

}