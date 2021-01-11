package JUC;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用AtomicInteger实现加锁操作，其内部使用CAS算法实现
 */
public class ThreadCAS {


    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        for(int i = 0;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    for(int i = 0;i<100;i++){
                        count.incrementAndGet();
                    }
                }
            }).start();
        }
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
