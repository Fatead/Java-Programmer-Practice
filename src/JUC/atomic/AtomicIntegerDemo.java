package JUC.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    static class MyData{

        AtomicInteger number = new AtomicInteger();

        public void addPlusPlus(){
            number.getAndIncrement();
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

}
