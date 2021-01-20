package JVM;

/**
 *  -Xms用来设置堆空间（新生代 + 老年代）的初始内存大小
 *     -X是jvm的运行参数
 *      ms是memory start
 *  -Xmx用来设置堆空间的最大的内存大小
 *
 *  默认堆空间的大小：
 *   初始内存大小：物理电脑内存大小/64
 *   最大内存大小：物理电脑内存大小/4
 *
 *   手动设置 -Xms 600m -Xmx 600m
 *   开发中建议将初始堆内存和最大堆内存设置为相同的值
 *
 *   -NewRatio 新生代和老年代的比例，默认为2
 *   在HotSpot中，Eden空间和另外两个Survivor空间缺省所占比例是8：1：1
 *   几乎所有的Java对象都是在Eden区中被new出来的，对大多数的Java对象的销毁都在新生代中进行的
 *
 *   设置方法区大小
 *   jdk7及以前
 *   -XX:PermSize=100m
 *   jdk8及以后
 *   -XX:MetaspaceSize=100m
 *
 */
public class HeapSpaceInitial {

    public static void main(String[] args) {
        //返回Java虚拟机种的堆内存的大小
        long initialMemory = Runtime.getRuntime().totalMemory()/1024/1024;
        //返回Java虚拟机试图使用的最大堆内存量
        long maxMemory = Runtime.getRuntime().maxMemory()/1024/1024;
        System.out.println(initialMemory + "M");
        System.out.println(maxMemory + "M");
        //System.out.println("系统内存大小为：" + initialMemory*64.0/1024 + "G");
    }
}
