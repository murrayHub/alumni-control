package com.alumni.control.constant;

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
     * 粉丝列表key
     */
    public static final String FOLLOWERS_KEY = "FOLLOWERS_KEY";
    /**
     * 偶像列表key
     */
    public static final String IDOLS_KEY = "IDOLS_KEY";
    /**
     * 圈内人员列表key
     */
    public static final String INSIDERS_KEY = "INSIDERS_KEY";
    /**
     * 用户号
     */
    public static final String USER_INFO_KEY = KEY.concat("USER_ID_CREATE_KEY");
    /**
     * 学历认证编号
     */
    public static final String USER_DEGREE_IDENTIFY_KEY = KEY.concat("USER_DEGREE_IDENTIFY_KEY");
    /**
     * 工作经历编号
     */
    public static final String USER_JOB_IDENTIFY_KEY = KEY.concat("USER_JOB_IDENTIFY_KEY");
    /**
     * 社会兼职经历编号
     */
    public static final String USER_PART_TIME_JOB_IDENTIFY_KEY = KEY.concat("USER_PART_TIME_JOB_IDENTIFY_KEY");
    /**
     * 圈编号
     */
    public static final String CIRCLE_NO_KEY = KEY.concat("CIRCLE_NO_CREATE_KEY");
    /**
     * 朋友圈动态编号
     */
    public static final String MOMENTS_NO_KEY = KEY.concat("MOMENTS_NO_CREATE_KEY");
    /**
     * 朋友圈动态评论编号
     */
    public static final String MOMENTS_COMMENT_NO_KEY = KEY.concat("MOMENTS_COMMENT_NO_CREATE_KEY");
    /**
     * 朋友圈动态点赞编号
     */
    public static final String MOMENTS_PRAISE_NO_KEY = KEY.concat("MOMENTS_PRAISE_NO_CREATE_KEY");

    /**
     * 入圈申请编号
     */
    public static final String CIRCLE_APPLY_NO_KEY = KEY.concat("CIRCLE_APPLY_NO_CREATE_KEY");

    private RedisDict() {

    }
}
