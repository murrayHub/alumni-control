package com.techGoal.service.impl;

import com.google.common.base.Strings;
import com.techGoal.enums.IDTypeEnum;
import com.techGoal.redis.RedisManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：
 * <p>
 * 1、 生成19位用户流水号
 * </p>
 *
 * @author : Murray
 * @date : 2017/11/29
 */
@Slf4j
public abstract class BaseIDBiz {

    /**
     * Redis操作实现
     */
    @Autowired
    private RedisManager redisBiz;

    /**
     * 生成19位的id：3位头+1位id类型+6位当前日期(yyMMdd)+7位流水号+1位校验位+1位幸运数字
     *
     * @param header        3位头
     * @param idTypeEnum    1位类型
     * @param redisKeyName  redis key名字
     * @param redisMaxValue 最大redis value的值
     * @param luckNo        最后一位数字
     * @return 19位id
     */
    protected String generate19By(String header, IDTypeEnum idTypeEnum, String redisKeyName, long redisMaxValue,
                                  String luckNo) {

        //redis计数取值
        long redisSeq = redisBiz.incr(redisKeyName);
        if (redisSeq > redisMaxValue) {
            redisBiz.deleteObject(redisKeyName);
        }
        String redisSeqStr = redisSeq + "";

        String seq = Strings.padStart(redisSeqStr, 7, '0');

        //拼装客户编号，验证位用X代替
        StringBuilder idSb = new StringBuilder().append(header).append(idTypeEnum.getCode());

        String dateStr = new SimpleDateFormat("yyMMdd").format(new Date());

        idSb.append(dateStr).append(seq).append('X').append(luckNo);

        String idStr = idSb.toString();
        //获取验证位
        idStr = idStr.replace("X", get1918Key(idStr));
        log.info("创建{}:{}", idTypeEnum.getDesc(), idStr);
        return idStr;
    }

    /**
     * 19位长度，第18位为校验位
     *
     * @param idStr 19位id
     * @return 返回校验位
     */
    private String get1918Key(String idStr) {
        int[] i = transStringToInt(idStr);
        final int idLength = 19;
        if (i.length != idLength) {
            throw new IllegalArgumentException("获取校验位失败，id原串长度不是19位,而是：" + i.length + "位");
        }
        int val = (i[0] * 83 + i[1] * 89 + i[2] * 7 +
                i[3] * 13 + i[4] * 17 + i[5] * 19 +
                i[6] * 23 + i[7] * 29 + i[8] * 31 +
                i[9] * 37 + i[10] * 41 + i[11] * 43 +
                i[12] * 47 + i[13] * 53 + i[14] * 59 +
                i[15] * 23 + i[16] * 57 + i[18] * 89) % 10;

        return val + "";
    }

    /**
     * 字符串转int数组
     *
     * @param idStr id
     * @return 返回int数组
     */
    private int[] transStringToInt(String idStr) {
        int[] n = new int[idStr.length()];
        for (int i = 0; i < idStr.length(); i++) {
            if (!"X".equals(idStr.substring(i, i + 1))) {
                n[i] = Integer.parseInt(idStr.substring(i, i + 1));
            } else {
                n[i] = 0;
            }

        }
        return n;
    }
}
