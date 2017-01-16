import com.danga.MemCached.SockIOPool;
import com.danga.MemCached.MemCachedClient;

import java.util.Date;

/**
 * Created by qy on 2017/1/16.
 * 使用现成的memcache的客户端实现客户端管理类
 */
public class MemCacheDemo {
    protected static MemCachedClient client = new MemCachedClient();
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
//        设置TCP的参数，连接超时
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setSocketConnectTO(0);

//        初始化连接池
        pool.initialize();
    }
    protected MemCacheDemo(){
    }
//    获取唯一实例
    public static MemCacheDemo getIntstance(){
        return new MemCacheDemo();
    }

//    增删改查的方法
    public boolean add(String key, Object value){
        return client.add(key, value);
    }
//    重载：加入过期时间
    public boolean add(String key, Object value, Date expiry){
        return  client.add(key, value, expiry);
    }
    public boolean replace(String key,Object value){
        return client.replace(key, value);
    }
    public boolean replace(String key,Object value,Date expiry){
        return client.replace(key, value, expiry);
    }
    public Object get(String key){
        return client.get(key);
    }

//    测试
    public static void main(String[] args){
        MemCacheDemo demo = MemCacheDemo.getIntstance();
        if(demo.add("TestKey","Quyuan")){
            System.out.print("Value is :"+ demo.get("TestKey").toString());
        };

    }
}
