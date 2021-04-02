package io.nio.buffer;
import java.nio.*;

/**
 * FloatBuffer这个类定义了float缓冲区上的四类操作，
 * 1.读写单个float的get和put方法（有绝对和相对两种实现方式）
 * 2.将此缓冲区中的连续 float 序列传输到数组中的相对批量 get 方法
 * 3.将 float 数组或其他 float 缓冲区中的连续 float 序列传输到此缓冲区的相对批量 put方法
 * 4.float 缓冲区的 compacting、duplicating 和slicing 方法。
 *
 * buffer中有三个重要属性
 * 1.capacity：该缓冲区所包含的元素的数量
 * 2.limit：第一个不应该读取或者写入的元素的索引
 * 3.position：下一个要读取或者写入的元素的索引
 */
public class FloatBufferDemo {

    public static void allocateBuffer(){
        //FloatBuffer可以通过allocation创建，新缓冲区的位置将为0，界限将为其容量
        //其底层实现为float数组，并且数组偏移量为0
        FloatBuffer floatBuffer = FloatBuffer.allocate(10);
        for (int i = 0; i <floatBuffer.capacity() ; i++) {
            float f = (float) Math.sin((((float) i) / 10) * (2 * Math.PI));
            floatBuffer.put(f);
        }
        //flip()方法将Buffer从写模式切换到读模式，把position的值赋给limit，position重置为0
        floatBuffer.flip();
        //读取buffer里面的数据，Buffer开始往外写数据，每写一个position就下移一个位置，一直移动到limit的位置结束
        while (floatBuffer.hasRemaining()){
            System.out.println("写出的内容为："+ floatBuffer.get());
            System.out.println("limit:" + floatBuffer.limit());
            System.out.println("position::" + floatBuffer.position());
            System.out.println("capacity:" + floatBuffer.capacity());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        allocateBuffer();
    }

}
