package com.techGoal.constant;

/**
 * 缓存字典
 * <p>
 * 1.
 * </p>
 *
 * @author : wukong
 * @version : 1.0.0
 * @date : 2018/1/10
 */
public final class RedisDict {
    /**
     * 缓存键值
     */
    private static final String KEY = "techgoal:";
    /**
     * token
     */
    public static final String TOKEN = KEY + "TOKEN:";
    /**
     * 订单号
     */
    public static final String ORDER_KEY = KEY.concat("ORDER_ID_CREATE_KEY");

    private RedisDict() {

    }
}
