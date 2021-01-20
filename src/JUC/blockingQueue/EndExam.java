package JUC.blockingQueue;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;

public class EndExam extends Student {

    private DelayQueue<Student> delayQueue;
    private CountDownLatch countDownLatch;
    private Thread teacherThread;

    public EndExam(DelayQueue<Student> delayQueue, CountDownLatch countDownLatch, Thread teacherThread,long workTime) {
        super("强制收卷", workTime,countDownLatch);
        this.delayQueue = delayQueue;
        this.countDownLatch = countDownLatch;
        this.teacherThread = teacherThread;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        teacherThread.interrupt();
        Student tmpStudent;
        for (Iterator<Student> iterator2 = delayQueue.iterator(); iterator2.hasNext();) {
            tmpStudent = iterator2.next();
            tmpStudent.setForce(true);
            tmpStudent.run();
        }
        countDownLatch.countDown();
    }

}