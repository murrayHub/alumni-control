package com.alumni.control.redis.Impl;

import com.alibaba.fastjson.JSONObject;
import com.alumni.control.redis.RedisManager;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * description : Redis-缓存管理操作类
 * <p>
 * 1、查询redis 数据库
 * 2、插入redis 数据库
 * 3、插入redis 数据库,设置有效期
 * 4、删除redis 保存对象
 * 5、更新 redis
 * 6、查询redis 数据库
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2018/11/20 20:35
 */
@Slf4j
@Service
public class RedisManagerImpl implements RedisManager {

    /**
     * redis 操作
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 查询redis 数据库
     *
     * @param keyEnum 查询关键字
     * @param clazz   指定返回List内存放的对象类型
     * @param <T>     返回对象类型,集合泛型
     * @return List<T>      返回对象集合
     */
    @Override
    public <T> List<T> queryListByKey(final String keyEnum, final Class<T> clazz) {
        log.debug("queryListByKey request：{}", keyEnum);

        String resultStr = queryObjectByKey(keyEnum);
        if (StringUtils.isBlank(resultStr)) {
            return Lists.newArrayList();
        }

        List<T> value = JSONObject.parseArray(resultStr, clazz);

        log.debug("queryListByKey response：{}", value);
        return value;
    }

    /**
     * 插入redis 数据库
     *
     * @param obj     保存对象
     * @param keyEnum 关键字
     * @return 对象类型, 泛型
     */
    @Override
    public boolean insertObject(final Object obj, final String keyEnum) {
        return insertObject(obj, keyEnum, 0L);
    }

    /**
     * 插入redis 数据库,设置有效期
     *
     * @param obj     保存对象
     * @param keyEnum 关键字
     * @param timeout 有效期（毫秒）
     * @return 对象类型, 泛型
     */
    @Override
    public boolean insertObject(final Object obj, final String keyEnum, final long timeout) {

        if (obj == null) {
            return false;
        }

        log.debug("insertObject request：key={}，obj={}", keyEnum, obj);

        final String value;
        if (obj instanceof String) {
            value = obj.toString();
        } else {
            value = JSONObject.toJSONString(obj);
        }
        Boolean result = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(keyEnum);
            byte[] redisValue = redisTemplate.getStringSerializer().serialize(value);
            connection.set(redisKey, redisValue);
            if (timeout > 0) {
                redisTemplate.expire(keyEnum, timeout, TimeUnit.MILLISECONDS);
            }
            return Boolean.TRUE;
        });

        log.debug("insertObject response：{}", result);
        return result;
    }

    /**
     * 更新 redis
     *
     * @param obj      操作对象
     * @param keyEnums keys数组
     * @return boolean      更新状态
     */
    @Override
    public boolean modify(final Object obj, final String... keyEnums) {
        for (String key : keyEnums) {
            deleteObject(key);
            insertObject(obj, key);
        }
        return true;
    }

    /**
     * 查询redis 数据库
     *
     * @param keyEnum 查询关键字
     * @return String
     */
    @Override
    public String queryObjectByKey(final String keyEnum) {
        log.debug("queryObjectByKey request：{}", keyEnum);

        String resultStr = (String) redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(keyEnum);
            if (connection.exists(redisKey)) {
                byte[] value = connection.get(redisKey);
                return redisTemplate.getStringSerializer().deserialize(value);
            }
            return "";
        });

        log.debug("queryObjectByKey response：{}", resultStr);
        return resultStr;
    }

    /**
     * 查询redis 数据库
     *
     * @param keyEnum 查询关键字
     * @return <T> T
     */
    @Override
    public <T> T queryObjectByKey(final String keyEnum, final Class<T> clazz) {
        log.debug("queryObjectByKey request：{}", keyEnum);

        String resultStr = queryObjectByKey(keyEnum);
        if (StringUtils.isBlank(resultStr)) {
            return null;
        }

        T value = JSONObject.parseObject(resultStr, clazz);

        log.debug("queryObjectByKey response：{}", value);
        return value;
    }

    /**
     * 删除redis 保存对象
     *
     * @param keyEnum 查询关键字
     * @return 删除结果
     */
    @Override
    public boolean deleteObject(final String keyEnum) {
        log.debug("deleteObject request:key={}", keyEnum);

        Long result = redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(keyEnum);
            return connection.del(redisKey);
        });

        log.debug("deleteObject response：{}", result);
        return result > 0;
    }

    /**
     * Redis锁
     *
     * @param key   锁的Key
     * @param value 锁的Value
     * @return 返回是否成功
     */
    @Override
    public boolean lockRedis(final String key, final String value, final long timeout) {

        log.debug("lockRedis request:key={},value={}", key, value);
        if (timeout < 0) {
            log.warn("lockRedis Fail lock Time gt 0");
            return false;
        }
        Boolean result = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(key);
            byte[] redisValue = redisTemplate.getStringSerializer().serialize(value);
            Boolean lock = Boolean.FALSE;
            try {
                lock = connection.setNX(redisKey, redisValue);
                log.debug("lockRedis Lock Result:{}", lock);
                Boolean setTimeOutResult = redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
                log.debug("lockRedis setTimeOutResult TimeOut:{} Result:{}", timeout, setTimeOutResult);
                if (lock && setTimeOutResult) {
                    return Boolean.TRUE;
                }
                if (!setTimeOutResult) {
                    redisTemplate.delete(key);
                    return Boolean.FALSE;
                }
            } catch (Exception e) {
                if (lock) {
                    redisTemplate.delete(key);
                }
                log.warn("lockRedis Fail Exception:{}", e);
            }
            return Boolean.FALSE;
        });
        log.debug("lockRedis request Result:{}", result);
        return result;
    }

    /**
     * 设置rediskey的超时时间
     *
     * @param key     key
     * @param timeOut 超时时间，不能为0，单位：毫秒
     */
    @Override
    public boolean modifyTimeOut(final String key, Long timeOut) {

        if (timeOut > 0) {
            return redisTemplate.expire(key, timeOut, TimeUnit.MILLISECONDS);
        }
        return false;
    }

    /**
     * 统计次数
     *
     * @param keyEnum 查询关键字
     */
    @Override
    public Long incr(final String keyEnum) {
        log.debug("getObject request:key={}", keyEnum);

        Long result = redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] redisKey = redisTemplate.getStringSerializer().serialize(keyEnum);
            return connection.incr(redisKey);
        });
        log.debug("getObject response：{}", result);
        return result;
    }

    /**
     * 获取redis key剩余失效时间
     *
     * @return -2 key不存在  -1 未设置失效时间
     */
    @Override
    public Long ttl(final String keyEnum) {
        log.debug("getObject request:key={}", keyEnum);
        Long result = redisTemplate.execute((RedisConnection connection) ->
                connection.ttl(redisTemplate.getStringSerializer().serialize(keyEnum)));
        log.debug("getObject response：{}", result);
        return result;
    }
}

