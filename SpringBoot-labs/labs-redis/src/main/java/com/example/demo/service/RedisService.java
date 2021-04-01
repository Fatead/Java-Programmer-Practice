package com.example.demo.service;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.TimeUnit;

public interface RedisService<THashKey,TValue> {

    /**
     * 设置哈希列表
     *
     * @param key     redis key not  null
     * @param hashKey hash key not  null
     * @param value   value
     */
    void hashPut(@NotNull(message = "key not null") String key, THashKey hashKey, TValue value);

    /**
     * 通过key查找哈希列表
     *
     * @param key redis key
     * @return {@code Map<THashKey, TValue>} 列表
     */
    Map<THashKey, TValue> hashFindAll(@NotNull(message = "key not null") String key);

    /**
     * 通过 key 和 哈希key 获取值
     *
     * @param key     redis key
     * @param hashKey 哈希key
     * @return {@code TValue} 值
     */
    TValue hashGet(@NotNull(message = "key not null") String key, THashKey hashKey);

    /**
     * 删除哈希列表中某一个哈希key
     *
     * @param key     redis key
     * @param hashKey 哈希key
     * @return {@code Long} 从哈希中删除的字段数，不包括指定但不存在的字段。
     */
    Long hashDelete(@NotNull(message = "key not null") String key, THashKey hashKey);

    Long hashIncrement(@NotNull(message = "key not null") String key, THashKey hashKey, long increment);

    /**
     * 将所有指定的值插入存储在的列表的尾部key。
     * <p>
     * 如果key不存在，则在执行推送操作之前将其创建为空列表。如果key保存的值不是列表，则返回错误。
     * </p>
     *
     * @param key   redis key
     * @param value 待插入的值
     * @return 操作后列表的长度
     */
    Long listPush(@NotNull(message = "key not null") String key, TValue value);

    /**
     * 将所有指定的值插入存储在的列表的头部key。
     * <p>
     * 如果key不存在，则在执行推送操作之前将其创建为空列表。如果key保存的值不是列表，则返回错误。
     * </p>
     *
     * @param key   redis key
     * @param value 待插入的值
     * @return 操作后列表的长度
     */
    Long listUnshift(@NotNull(message = "key not null") String key, TValue value);

    /**
     * 返回key的所有元素列表
     *
     * @param key redis key
     * @return {@code List<TValue>} 所有元素列表
     */
    List<TValue> listFindAll(@NotNull(message = "key not null") String key);

    /**
     * 删除并返回存储在列表中的第一个元素key。
     *
     * @param key redis key
     * @return {@code TValue} 第一个元素的值，当key不存在时返回null
     */
    TValue listLeftPop(@NotNull(message = "key not null") String key);

    /**
     * 设置 {@link TValue} 类型的值
     *
     * @param key   redis key
     * @param value 值
     */
    void setValue(@NotNull(message = "key not null") String key, TValue value);

    /**
     * 设置 {@link TValue} 类型的值
     *
     * @param key     redis key
     * @param value   值
     * @param timeout key过期时间,单位：毫秒
     */
    void setValue(@NotNull(message = "key not null") String key, TValue value, long timeout);

    /**
     * 设置 {@link TValue} 类型的值
     *
     * @param key      redis key
     * @param value    值
     * @param timeout  key过期时间
     * @param timeUnit key过期时间单位
     */
    void setValue(@NotNull(message = "key not null") String key, TValue value, long timeout,
                  TimeUnit timeUnit);

    /**
     * 设置 {@link String} 类型的值
     *
     * @param key   redis key
     * @param value 值
     */
    void setStringValue(@NotNull(message = "key not null") String key, String value);

    /**
     * 设置 {@link String} 类型的值
     *
     * @param key     redis key
     * @param value   值
     * @param timeout key过期时间,单位：毫秒
     */
    void setStringValue(@NotNull(message = "key not null") String key, String value, long timeout);

    /**
     * 设置 {@link String} 类型的值
     *
     * @param key      redis key
     * @param value    值
     * @param timeout  key过期时间
     * @param timeUnit key过期时间单位
     */
    void setStringValue(@NotNull(message = "key not null") String key, String value, long timeout,
                        TimeUnit timeUnit);

    /**
     * 获取 {@link TValue} 类型的值
     *
     * @param key redis key
     * @return {@link TValue} 值
     */
    TValue getValue(@NotNull(message = "key not null") String key);

    /**
     * 获取 {@link String} 类型的值
     *
     * @param key redis key
     * @return {@link String} 值
     */
    String getStringValue(@NotNull(message = "key not null") String key);

    /**
     * 删除Key
     *
     * @param key redis key
     * @return {@link boolean} 是否成功
     */
    boolean delete(@NotNull(message = "key not null") String key);

    /**
     * 删除Keys
     *
     * @param keys redis keys
     * @return {@link long} 已删除的键数
     */
    long delete(Collection<String> keys);

    /**
     * 设置Key过期时间
     *
     * @param key      redis key
     * @param timeout  过期时间
     * @param timeUnit 过期时间单位
     * @return {@link boolean} 是否成功
     */
    boolean expire(@NotNull(message = "key not null") String key, long timeout, TimeUnit timeUnit);

    /**
     * 设置Key过期时间
     *
     * @param key      redis key
     * @param expireAt 具体过期日期
     * @return {@link boolean} 是否成功
     */
    boolean expireAt(@NotNull(message = "key not null") String key, Date expireAt);

    /**
     * Key是否存在
     *
     * @param key redis key
     * @return {@link boolean} 是否存在
     */
    boolean hasKey(@NotNull(message = "key not null") String key);

    /**
     * 设置Key为永不过期
     *
     * @param key redis key
     * @return {@link boolean} 是否成功
     */
    boolean persist(@NotNull(message = "key not null") String key);

    /**
     * 返回Key的剩余生存时间,单位：秒
     *
     * @param key redis key
     * @return {@link boolean} 剩余生存时间，以秒为单位，错误返回为负值
     */
    long getExpire(@NotNull(message = "key not null") String key);

    /**
     * 返回Key的剩余生存时间
     *
     * @param key      redis key
     * @param timeUnit 时间单位
     * @return {@link boolean} 剩余生存时间，错误返回为负值
     */
    long getExpire(@NotNull(message = "key not null") String key, final TimeUnit timeUnit);

    /**
     * 获取统一格式的key
     * <p>
     * 每个字符串之间使用:分隔，在Redis管理器中查看时是以目录层级显示，增强可读性。
     * </p>
     *
     * @param keys key列表
     * @return 格式后key
     */
    String getKey(String... keys);

    RedisConnectionFactory getRedisConnectionFactory();

    RedisTemplate<String, TValue> getRedisTemplate();

    Set<String> keys(String prefix);

    long deleteByFullKeys(Collection<String> keys);
}
