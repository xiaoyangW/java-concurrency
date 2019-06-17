package connection;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author xiaoyang.wen
 * @date 2019/6/14 16:49
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();


    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    /**
     * 发布连接
     * @param connection 连接
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 获取连接
     * @param mills 超时时间
     * @return connection 连接
     * @throws InterruptedException
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                //没有连接实例一直等待
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = System.currentTimeMillis() - future;
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
