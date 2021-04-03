package io.nio.server;

/**
 * Queue使用一个flip标志位来记录当前写的位置是否溢出（重置为0）
 * 也就是说，允许用户超出容量的写入（但是不能超过读入位），从而实现对于空间的充分利用
 * 分别实现了两种读和两种写：
 * 1.写入或者读出一个元素
 * 2.写入或者读出一个数组
 */
public class QueueIntFlip {

    public int[] elements = null;

    public int capacity = 0;
    public int writePos = 0;
    public int readPos = 0;
    public boolean flipped = false;

    public QueueIntFlip(int capacity){
        this.capacity = capacity;
        this.elements = new int[capacity];
    }

    public void reset(){
        this.writePos = 0;
        this.readPos = 0;
        this.flipped = false;
    }

    /**
     * 获得当前可读的剩余空间大小
     * @return
     */
    public int available(){
        if(!flipped){
            return writePos - readPos;
        }
        return capacity - readPos + writePos;
    }

    /**
     * 获得剩余可写的容量大小
     * @return
     */
    public int remainingCapacity(){
        if(!flipped){
            return capacity - writePos;
        }
        return readPos - writePos;
    }

    /**
     * 写入单个元素
     * @param element
     * @return
     */
    public boolean put(int element){
        if(!flipped){
            //如果当前写入位置已经到整个QueueIntFlip的整体容量大小的话，将标志位设置为已经翻转的
            if(writePos == capacity){
                writePos = 0;
                flipped = true;
                //如果翻转后当前写入位置没有超过读入位置，可以成功写入，否则失败
                if(writePos<readPos){
                    elements[writePos++] = element;
                    return true;
                }else {
                    return false;
                }
            }else {
                //如果没有翻转过并且当前写入位置没有到容量大小则直接写入
                elements[writePos++] = element;
                return true;
            }
        }else {
            //如果翻转后当前写入位置没有超过读入位置，可以成功写入，否则失败
            if(writePos<readPos){
                elements[writePos++] = element;
                return true;
            }else {
                return false;
            }
        }
    }

    /**
     * 写入整个数组大小的元素
     * @param newElements 要写入的数组
     * @param length 写入数组的长度
     * @return 新元素数组中写入成功元素的位置
     */
    public int put(int[] newElements,int length){
        //从newElement中读出元素的指针
        int newElementsReadPos = 0;
        if(!flipped){
            //不需要翻转就可以直接写入内容
            if(length <= capacity - writePos){
                for (;newElementsReadPos<length;newElementsReadPos++){
                    this.elements[writePos++] = newElements[newElementsReadPos];
                }
                return newElementsReadPos;
            }else {
                //需要写入的元素需要被分割为不同的部分，分别写入到顶部和底部
                //将元素写入顶部
                for(;writePos<capacity;writePos++,newElementsReadPos++){
                    elements[writePos] = newElements[newElementsReadPos];
                }
                //将元素写入底部，首先重置writePos指针，并且设置翻转标志位
                writePos = 0;
                flipped = true;
                int endPos = Math.min(readPos,length - newElementsReadPos);
                for(;writePos<endPos;writePos++,newElementsReadPos++){
                    elements[writePos] = newElements[newElementsReadPos];
                }
                return newElementsReadPos;
            }
        }else {
            int endPos = Math.min(readPos,writePos + length);
            for(;writePos<endPos;writePos++,newElementsReadPos++){
                elements[writePos] = newElements[newElementsReadPos];
            }
            return newElementsReadPos;
        }
    }

    /**
     * 读出单个元素
     * @return
     */
    public int take(){
        if(!flipped){
            if(readPos<writePos){
                return elements[readPos++];
            }else {
                return -1;
            }
        }else {
            if(readPos == capacity){
                readPos = 0;
                flipped = false;
                if(readPos < writePos){
                    return elements[readPos++];
                }else {
                    return -1;
                }
            }else {
                return elements[readPos++];
            }
        }
    }

    /**
     * 读出一个数组长度的元素
     * @param into
     * @param length
     * @return
     */
    public int take(int[] into, int length){
        int intoWritePos = 0;
        if(!flipped){
            int endPos = Math.min(writePos,readPos + length);
            for (;readPos<endPos;readPos++,intoWritePos++){
                into[intoWritePos] = elements[readPos];
            }
            return intoWritePos;
        }else {
            for (;this.readPos<capacity;readPos++,intoWritePos++){
                into[intoWritePos] = elements[readPos];
            }
            this.readPos = 0;
            flipped = false;
            int endPos = Math.min(writePos,length - intoWritePos);
            for (;this.readPos<endPos;readPos++,intoWritePos++){
                into[intoWritePos] = elements[readPos];
            }
            return intoWritePos;
        }
    }

}
