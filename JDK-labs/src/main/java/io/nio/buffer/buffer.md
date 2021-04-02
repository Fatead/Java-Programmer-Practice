# Buffer缓冲区
主要用于和NIO Channels进行交互，是一块可以用于读写数据的内存区域，Buffer对象提供的操作可以让我们方便地操作缓冲区
## 操作
Buffer读写数据主要包括下面四步：
1. 写入数据到Buffer
2. 调用buffer.flip()切换到读数据模式，将limit置为当前位置，将position置0
3. 将数据从Buffer中读出
4. 调用buffer.clear()或者buffer.compact()清空缓冲区的数据

## 属性与方法
 buffer中有三个重要属性
 1. capacity：该缓冲区所包含的元素的数量
 2. limit：第一个不应该读取或者写入的元素的索引
 3. position：下一个要读取或者写入的元素的索引
  
  **compact()和clear()**
  
  调用clear()方法，position将被设回0，limit被设置成 capacity的值。换句话说，Buffer 被清空了。Buffer中的数据并未清除，只是这些标记告诉我们可以从哪里开始往Buffer里写数据。
  
  Buffer中仍有未读的数据，且后续还需要这些数据，但是此时想要先先写些数据，那么使用compact()方法。
  
  compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit属性依然像clear()方法一样，设置成capacity。现在Buffer准备好写数据了，但是不会覆盖未读的数据。
 
   **mark()和reset()**
   
   分别用于标记和重置一个未读的位置
   
 ## 类型
 Buffer的类型如下
 + ByteBuffer
 + MappedByteBuffer
 + CharBuffer
 + DoubleBuffer
 + FloatBuffer
 + IntBuffer
 + LongBuffer
 + ShortBuffer
 