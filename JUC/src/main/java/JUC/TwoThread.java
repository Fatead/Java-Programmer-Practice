package JUC;

/**
 * 使用synchronized代码块实现加锁
 */
public class TwoThread {

    public static int count = 0;

    public static void main(String[] args) {
        for(int i = 0;i<2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(10);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    for(int i=0;i<100;i++){
                        synchronized (TwoThread.class){
                            count++;
                        }
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
