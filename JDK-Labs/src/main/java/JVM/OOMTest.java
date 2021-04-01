package JVM;

/**
 * 超出方法区内存空间报错的样例
 */
public class OOMTest {

    public static void main(String[] args) {
        int j = 0;
        try {
            OOMTest oomTest = new OOMTest();
            for (int i = 0; i <10000 ; i++) {
                
            }
        }finally {
            System.out.println(j);
        }

    }

}
