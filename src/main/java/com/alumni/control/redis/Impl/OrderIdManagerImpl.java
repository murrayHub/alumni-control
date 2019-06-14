package com.alumni.control.redis.Impl;

import com.alumni.control.redis.OrderIdManager;
import com.alumni.control.utils.DateUtil;
import com.alumni.control.utils.TechGoalStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description : 创建订单号-实现类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 17:58
 */
@Component
public class OrderIdManagerImpl implements OrderIdManager {
    private static final int LONG_SIZE = 19;
    private static final int REDIS_MAX_VALUE = 999000000;
    @Autowired
    private RedisManagerImpl redisManager;

    public OrderIdManagerImpl() {
    }

    @Override
    public Long orderIdCreate(String redisKey) {
        long redisSeq = this.redisManager.incr(redisKey);
        if (redisSeq > 999000000L) {
            this.redisManager.deleteObject(redisKey);
        }

        String dateTime = DateUtil.getCurrent("yyMMddHHmm");
        String redisSeqStr = redisSeq + "";
        return Long.parseLong(dateTime + TechGoalStringUtil.leftAppendZero(19 - dateTime.length() - redisSeqStr.length(), redisSeqStr));
    }
}
