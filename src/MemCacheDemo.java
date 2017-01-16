import com.danga.MemCached.SockIOPool;

/**
 * Created by qy on 2017/1/16.
 * 使用现成的memcache的客户端
 */
public class MemCacheDemo {

    static {
//        服务器列表
        String[] servers = {"127.0.0.1:11211"};
//        设置器权重
        Integer[] weight = {3};
//        创建Sock连接池实例对象

        SockIOPool pool = SockIOPool.getInstance();
//        设置服务器信息
        pool.setServers(servers);
        pool.setWeights(weight);
//        设置初始连接数，最小和最大连接数以及最大处理时间
        pool.setInitConn(5);
        pool.setMaxConn(200);
        pool.setMinConn(1);
//        最大处理时间为6小时（单位为毫秒）
        pool.setMaxIdle(1000*60*60*6);
//        设置主线程的睡眠时间
        pool.setMaintSleep(30);

    }

}
