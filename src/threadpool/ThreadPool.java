package threadpool;

/**
 * @author xiaoyang.wen
 * @date 2019/6/17 15:09
 */
public interface ThreadPool<Job extends Runnable> {
    /**
     * 执行一个job,这个job需要实现runnable
     * @param job 任务
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作线程
     * @param num 线程数
     */
    void addWorkers(int num);

    /**
     * 减少工作线程
     * @param num
     */
    void removeWorker(int num);

    /**
     * 获取正在执行的任务数量
     * @return
     */
    int getJobSize();
}
