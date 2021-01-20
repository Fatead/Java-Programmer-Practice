package JUC.blockingQueue;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;

/**
 * 20个学生参加考试
 *  考试时间为120分钟，30分钟后才可交卷，初始化考生完成试卷时间最小应为30分钟
 *  对于能够在120分钟内交卷的考生，如何实现这些考生交卷
 *  对于120分钟内没有完成考试的考生，在120分钟考试时间到后需要让他们强制交卷
 *  在所有的考生都交完卷后，需要将控制线程关闭
 */
public class Exam {

    public static void main(String[] args) throws InterruptedException {
        int studentNum = 20;
        CountDownLatch countDownLatch = new CountDownLatch(studentNum+1);
        DelayQueue<Student> delayQueue = new DelayQueue<>();
        Random random = new Random();
        for (int i = 0; i <studentNum ; i++) {
            delayQueue.put(new Student("id:" + (i + 1),30 + random.nextInt(120),countDownLatch));
        }
        Thread teacherThread = new Thread(new Teacher(delayQueue));
        delayQueue.put(new EndExam(delayQueue, countDownLatch,teacherThread,120));
        teacherThread.start();
        countDownLatch.await();
        System.out.println(" 考试时间到，全部交卷！");
    }
}
