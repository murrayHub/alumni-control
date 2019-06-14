package com.alumni.control.redis.Impl;

import com.alumni.control.dict.NumberLongDict;
import com.alumni.control.enums.RedisLockEnum;
import com.alumni.control.redis.LockBiz;
import com.alumni.control.redis.RedisManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description : 锁服务
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2018/11/20 20:33
 */
@Slf4j
@Service
public class LockBizImpl implements LockBiz {
    /**
     * 缓存服务
     */
    @Autowired
    private RedisManager redisBiz;

    /**
     * 接口锁
     * 1、判断是否锁住
     * 2、已锁住就抛出异常提示不能重复调用
     * 3、未锁住就加锁
     *
     * @param key 唯一标识
     * @return 返回锁的key
     */
    @Override
    public String lock(String key) {

        // 判定商户是否锁住
        Boolean lockFlag = Boolean.FALSE;
        //查询锁
        String value = this.redisBiz.queryObjectByKey(key);
        if (RedisLockEnum.TRUE.getCode().equals(value)) {
            lockFlag = Boolean.TRUE;
        }
        //已锁住就抛出异常提示不能重复调用
        log.info("call 锁标识：{},是否锁住：{}", key, lockFlag);
        if (lockFlag) {
            log.error("call 锁标识：{},是否锁住：{}，请勿频繁操作！", key, lockFlag);
            throw new RuntimeException("正在处理，请勿频繁操作！");
        }
        //未锁住就加锁，加锁失败抛出异常
        lockFlag = redisBiz.lockRedis(key, RedisLockEnum.TRUE.getCode(), NumberLongDict.TIME_OUT);
        if (!lockFlag) {
            log.info("call key：{},锁住失败，请稍后重试", key);
            throw new RuntimeException("系统繁忙，请稍后再试");
        }
        log.info("call key：{}锁定成功", key);

        return key;
    }

    /**
     * 锁释放
     *
     * @param key 锁标识
     */
    @Override
    public void unLock(String key) {
        this.redisBiz.deleteObject(key);
        log.info("call key：{},解锁成功", key);
    }

    /**
     * 接口锁
     * 1、判断是否锁住
     * 2、已锁住就抛出异常提示不能重复调用
     *
     * @param key 唯一标识
     * @return true:锁住|false:未锁住
     */
    @Override
    public Boolean isLock(String key) {
        String value = this.redisBiz.queryObjectByKey(key);
        return RedisLockEnum.TRUE.getCode().equals(value) ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean getLock(String key) {
        try {
            this.lock(key);
            return Boolean.TRUE;
        } catch (Exception var3) {
            return Boolean.FALSE;
        }
    }

    @Override
    public void getUnLock(String key, Boolean flag) {
        if (flag) {
            this.unLock(key);
        }

    }
}
