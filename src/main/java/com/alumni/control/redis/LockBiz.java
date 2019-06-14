package com.alumni.control.redis;

/**
 * description : 锁服务
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2018/11/20 20:32
 */
public interface LockBiz {
    String lock(String var1);

    void unLock(String var1);

    Boolean isLock(String var1);

    Boolean getLock(String var1);

    void getUnLock(String var1, Boolean var2);
}
