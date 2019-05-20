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
    private static final String KEY = "TECHGOAL:";
    /**
     * token
     */
    public static final String TOKEN = KEY + "TOKEN:";
    /**
     * 用户号
     */
    public static final String USER_INFO_KEY = KEY.concat("USER_ID_CREATE_KEY");
    /**
     * 圈编号
     */
    public static final String CIRCLE_NO_KEY = KEY.concat("CIRCLE_NO_CREATE_KEY");
    /**
     * 朋友圈动态编号
     */
    public static final String MOMENTS_NO_KEY = KEY.concat("MOMENTS_NO_CREATE_KEY");

    /**
     * 入圈申请编号
     */
    public static final String CIRCLE_APPLY_NO_KEY = KEY.concat("CIRCLE_APPLY_NO_CREATE_KEY");

    private RedisDict() {

    }
}
