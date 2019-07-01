package lock;

import java.util.concurrent.locks.Lock;

import static java.lang.Thread.sleep;

/**
 * @author xiaoyang.wen
 * @date 2019/7/1 16:38
 */
public class LockTest {
    static final Lock lock = new TwinsLock();
    public static void main(String[] args) throws InterruptedException {

        class Worker extends Thread {
            @Override
            public void run() {
                while (true){
                    lock.lock();
                    try {
                        sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            sleep(1000);
        }

    }

}
