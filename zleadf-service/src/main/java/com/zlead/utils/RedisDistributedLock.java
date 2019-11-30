package com.zlead.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.UUID;

/***
 * 2019.4.23
 * by Q
 * redis分布式锁,植入lua
 * */
@Component
public class RedisDistributedLock {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;
    private static  int ticketCount = 450;
    public static JedisPool pool;
    static String lockKey = getRequestId();
    static {
        if(pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxTotal(1000);
            config.setMaxWaitMillis(1000);
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(50);
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；单位毫秒
            //小于零:阻塞不确定的时间,  默认-1
            config.setMaxWaitMillis(1000 * 100);
            //在borrow(引入)一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            //return 一个jedis实例给pool时，是否检查连接可用性（ping()）
            config.setTestOnReturn(true);
            //connectionTimeout 连接超时（默认2000ms）
            //soTimeout 响应超时（默认2000ms）
            pool = new JedisPool(config, "192.168.3.214", 6379,  2000);
        }
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static String getRequestId(){
        String uuid = UUID.randomUUID().toString().replace("-", "");

        return uuid;
    }

/*    @Test
    public void test() throws InterruptedException {
        long start = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(500);
        Executor executor = Executors.newFixedThreadPool(50);
        for(int i=0 ; i<500 ; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        distributeLock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        long costTime = System.currentTimeMillis() - start;
        pool.close();
        System.out.println("it totally took：" + costTime + " ms");
    }*/

    public void distributeLock() throws InterruptedException {
        Jedis jedis = null;
        String requestId = null;
        int timeoutCount = 0;
        requestId = getRequestId();
        while (true){
            try{
                jedis = getJedis();
                break;
            } catch (Exception e){
                if (e instanceof JedisConnectionException || e instanceof SocketTimeoutException) {
                    //记录下获取多少次才获得jedis连接，并发很多的时候可能池里的连接不够而导致获取连接失败，所以这里循环获取
                    timeoutCount++;
                    //System.out.println("user:"+requestId+" getJedis timeoutCount={"+timeoutCount+"}");
                }
            }
        }

        if(tryGetDistributedLock(jedis, lockKey, requestId, 3000)) {
            if (ticketCount > 0) {
                System.out.println(requestId + " have got a ticket：" + ticketCount);
                ticketCount--;
            } else {
                System.out.println(requestId + "the ticket have been sold out.");
            }
            releaseDistributedLock(jedis, lockKey, requestId);
            jedis.close();
        } else {
            System.out.println("user"+ requestId +" have been sold out!Give up getting lock");
        }

    }

    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) throws InterruptedException {

        int timeoutCount = 0;
        long currentTime = System.currentTimeMillis();
        while(true){

            try{
                String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
                if (LOCK_SUCCESS.equals(result)) {
                    return true;
                }
                if(ticketCount <=0){
                    System.out.println("user"+ requestId +" have been sold out!Give up getting lock");
                    return false;
                }
                /*//等待了60S以上，直接不再获取锁
                if(System.currentTimeMillis() > (currentTime+ 60*1000)){
                    System.out.println("user"+ requestId +"等待了60S以上了，放弃！！！");
                    return false;
                }*/
            } catch (Exception e){
                /*if (e instanceof JedisConnectionException || e instanceof SocketTimeoutException) {
                    timeoutCount++;
                    //System.out.println("user:"+requestId+" jedis.set() timeoutCount={"+timeoutCount+"}");
                    if (timeoutCount > 3)
                    {
                        System.out.println("connect error！");
                        break;
                    }
                }*/
            }
        }
    }

    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        //判断requestId相等时为了确定解锁和获取锁的用户是同一个用户
        //lockKey是针对此业务的锁ID 执行成功返回1.
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }



}
