package com.alumni.control.redis;

import java.util.List;

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
 * @date : 2018/11/20 20:34
 */
public interface RedisManager {

    /**
     * 查询redis 数据库
     *
     * @param keyEnum 查询关键字
     * @param clazz   指定返回List内存放的对象类型
     * @param <T>     返回对象类型,集合泛型
     * @return List<T>      返回对象集合
     */
    <T> List<T> queryListByKey(final String keyEnum, final Class<T> clazz);

    /**
     * 插入redis 数据库
     *
     * @param obj     保存对象
     * @param keyEnum 关键字
     * @return 对象类型, 泛型
     */
    boolean insertObject(final Object obj, final String keyEnum);

    /**
     * 插入redis 数据库,设置有效期
     *
     * @param obj     保存对象
     * @param keyEnum 关键字
     * @param timeout 有效期（毫秒）
     * @return 对象类型, 泛型
     */
    boolean insertObject(final Object obj, final String keyEnum, final long timeout);

    /**
     * 更新 redis
     *
     * @param obj      操作对象
     * @param keyEnums keys数组
     * @return boolean      更新状态
     */
    boolean modify(final Object obj, final String... keyEnums);

    /**
     * 查询redis 数据库
     *
     * @param keyEnum 查询关键字
     * @return String
     */
    String queryObjectByKey(final String keyEnum);

    /**
     * 查询redis 数据库
     *
     * @param keyEnum 查询关键字
     * @return <T> T
     */
    <T> T queryObjectByKey(final String keyEnum, final Class<T> clazz);

    /**
     * 删除redis 保存对象
     *
     * @param keyEnum 查询关键字
     * @return 删除结果
     */
    boolean deleteObject(final String keyEnum);

    /**
     * Redis锁
     *
     * @param key     锁的Key
     * @param value   锁的Value
     * @param timeout 超时时间
     * @return 返回是否成功
     */
    boolean lockRedis(final String key, final String value, final long timeout);

    /**
     * 设置rediskey的超时时间
     *
     * @param key     key
     * @param timeOut 超时时间，不能为0，单位：毫秒
     * @return 是否成功
     */
    boolean modifyTimeOut(final String key, Long timeOut);

    /**
     * 统计次数
     *
     * @param keyEnum 查询关键字
     * @return 次数
     */
    Long incr(final String keyEnum);

    /**
     * 获取key剩余失效时间
     *
     * @param keyEnum 查询关键字
     * @return 时间(秒) -2 key不存在  -1 未设置失效时间
     */
    Long ttl(final String keyEnum);

}
