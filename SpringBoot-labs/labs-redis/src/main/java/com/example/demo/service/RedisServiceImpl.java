package com.example.demo.service;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis通用服务的实现类
 * THashKey 哈希键
 * TValue 值
 */
@Service
public class RedisServiceImpl<THashKey,TValue> implements RedisService<THashKey,TValue> {

    private RedisTemplate<String,TValue> redisTemplate;
    //redis中的hash数据结构的操作类
    private HashOperations<String,THashKey,TValue> hashOperations;
    //redis中list数据结构的操作类
    private ListOperations<String,TValue> listOperations;
    //string操作类
    private ValueOperations<String,TValue> valueOperations;

    private ValueOperations<String,String> valueStringOperations;


    public RedisServiceImpl(StringRedisTemplate stringRedisTemplate, RedisTemplate<String,TValue> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.listOperations = redisTemplate.opsForList();
        this.valueOperations = redisTemplate.opsForValue();
        this.valueStringOperations = stringRedisTemplate.opsForValue();
    }


    /**
     * 向redis中的hash加入值
     * @param key     redis key not  null
     * @param hashKey hash key not  null
     * @param value   value
     */
    @Override
    public void hashPut(String key, THashKey hashKey, TValue value) {
        hashOperations.put(key,hashKey,value);
    }

    /**
     * 根据key查找hash表
     * @param key redis key
     * @return
     */
    @Override
    public Map<THashKey, TValue> hashFindAll(String key) {
        return hashOperations.entries(key);
    }

    /**
     * 根据key 和 hashKey获取值
     * @param key     redis key
     * @param hashKey 哈希key
     * @return
     */
    @Override
    public TValue hashGet(String key, THashKey hashKey) {
        return hashOperations.get(key,hashKey);
    }

    /**
     * 根据key和hashKey删除值
     * @param key     redis key
     * @param hashKey 哈希key
     * @return
     */
    @Override
    public Long hashDelete(String key, THashKey hashKey) {
        return hashOperations.delete(key,hashKey);
    }

    /**
     * 根据key和hashKey删除值
     * @param key
     * @param hashKey
     * @param increment
     * @return
     */
    @Override
    public Long hashIncrement(String key, THashKey hashKey, long increment) {
        return hashOperations.increment(key,hashKey,increment);
    }

    /**
     * 将所有指定的值插入在指定key对应list的尾部
     * 如果key不存在，则为key创建新的list,并将值push进去
     * 如果key报错的值不是list,则返回错误
     * @param key   redis key
     * @param value 待插入的值
     * @return 操作后list的长度
     */
    @Override
    public Long listPush(String key, TValue value) {
        return null;
    }

    /**
     * 将所有指定的值插入到key对应list的头部
     * @param key   redis key
     * @param value 待插入的值
     * @return 操作后列表的长度
     */
    @Override
    public Long listUnshift(String key, TValue value) {
        return listOperations.leftPush(key,value);
    }

    /**
     * 返回key对应list的所有元素列表
     * @param key redis key
     * @return
     */
    @Override
    public List<TValue> listFindAll(@NotNull String key) {
        Boolean hashKey = redisTemplate.hasKey(key);
        if(hashKey == null||!hashKey){
            return null;
        }
        Long size = listOperations.size(key);
        return listOperations.range(key,0,size == null ? 0:size);
    }

    /**
     * 删除并返回list中的第一个元素
     * @param key redis key
     * @return
     */
    @Override
    public TValue listLeftPop(String key) {
        return listOperations.leftPop(key);
    }

    @Override
    public void setValue(String key, TValue value) {
        valueOperations.set(key,value);
    }

    /**
     * @param key     redis key
     * @param value   值
     * @param timeout key过期时间,单位：毫秒
     */
    @Override
    public void setValue(String key, TValue value, long timeout) {
        valueOperations.set(key,value,timeout,TimeUnit.MILLISECONDS);
    }

    /**
     * @param key      redis key
     * @param value    值
     * @param timeout  key过期时间
     * @param timeUnit key过期时间单位
     */
    @Override
    public void setValue(String key, TValue value, long timeout, TimeUnit timeUnit) {
        valueOperations.set(key,value,timeout,timeUnit);
    }

    /**
     * 设置String类型的值
     * @param key   redis key
     * @param value 值
     */
    @Override
    public void setStringValue(String key, String value) {
        valueStringOperations.set(key,value);
    }

    @Override
    public void setStringValue(String key, String value, long timeout) {
        valueStringOperations.set(key,value,timeout,TimeUnit.MILLISECONDS);
    }

    @Override
    public void setStringValue(String key, String value, long timeout, TimeUnit timeUnit) {
        valueStringOperations.set(key,value,timeout,timeUnit);
    }

    @Override
    public TValue getValue(String key) {
        return valueOperations.get(key);
    }

    @Override
    public String getStringValue(String key) {
        return valueStringOperations.get(key);
    }

    @Override
    public boolean delete(String key) {
        Boolean result = redisTemplate.delete(key);
        return result!=null&&result;
    }

    @Override
    public long delete(Collection<String> keys) {
        Long result = redisTemplate.delete(keys);
        return result == null ? 0:result;
    }

    @Override
    public boolean expire(String key, long timeout, TimeUnit timeUnit) {
        Boolean expire = redisTemplate.expire(key,timeout,timeUnit);
        return expire !=null && expire;
    }

    /**
     * @param key      redis key
     * @param expireAt 具体过期日期
     * @return
     */
    @Override
    public boolean expireAt(String key, Date expireAt) {
        Boolean expire = redisTemplate.expireAt(key,expireAt);
        return expire!=null&&expire;
    }

    /**
     * 判断key是否存在
     * @param key redis key
     * @return
     */
    @Override
    public boolean hasKey(String key) {
        Boolean hashKey = redisTemplate.hasKey(key);
        return hashKey!=null && hashKey;
    }

    /**
     * 设置key永不过期
     * @param key redis key
     * @return
     */
    @Override
    public boolean persist(String key) {
        Boolean persist = redisTemplate.persist(key);
        return persist !=null && persist;
    }

    /**
     * 获取key的剩余生存时间，单位：秒
     * @param key redis key
     * @return
     */
    @Override
    public long getExpire(String key) {
        Long getExpire = redisTemplate.getExpire(key);
        return getExpire == null?-1:getExpire;
    }

    @Override
    public long getExpire(String key, TimeUnit timeUnit) {
        Long getExpire = redisTemplate.getExpire(key, timeUnit);
        return getExpire == null ? -1 : getExpire;
    }

    /**
     * 获取统一格式的key,每个key之间使用：进行分割
     * @param keys key列表
     * @return
     */
    @Override
    public String getKey(String... keys) {
        return String.join(":",keys);
    }

    @Override
    public RedisConnectionFactory getRedisConnectionFactory() {
        return redisTemplate.getConnectionFactory();
    }

    @Override
    public RedisTemplate<String, TValue> getRedisTemplate() {
        return this.redisTemplate;
    }

    /**
     * 根据正则表达式匹配keys
     * @param prefix
     * @return
     */
    @Override
    public Set<String> keys(String prefix) {
        return redisTemplate.keys(prefix);
    }

    /**
     * 批量删除容器中的key
     * @param keys
     * @return
     */
    @Override
    public long deleteByFullKeys(Collection<String> keys) {
        Long result = redisTemplate.delete(keys);
        return result == null ? 0 : result;
    }
}
