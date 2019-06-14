package com.alumni.control.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description : 密码加密工具类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 15:43
 */
public class PwdEncryptUtil {
    private static final Logger log = LoggerFactory.getLogger(PwdEncryptUtil.class);

    private PwdEncryptUtil() {
    }

    public static String aesEncrypt(String pwd) {
        String encryptStr = null;

        try {
            if (pwd != null) {
                String desEncrypt = SecurityUtil.aesEncrypt(pwd, "3000100010000002");
                encryptStr = SecurityUtil.md5DesEncrypt(desEncrypt);
            }
        } catch (Exception var3) {
            log.error("call 密码加密异常", var3);
        }

        return encryptStr;
    }

    public static String aesLoginEncrypt(String pwd) {
        String encryptStr = null;

        try {
            if (pwd != null) {
                String desEncrypt = SecurityUtil.aesEncrypt(pwd, "3000100010000001");
                encryptStr = SecurityUtil.md5DesEncrypt(desEncrypt);
            }
        } catch (Exception var3) {
            log.error("call 密码加密异常", var3);
        }

        return encryptStr;
    }

    public static String encrypt(String pwd) {
        String encryptStr = null;

        try {
            if (pwd != null) {
                String desEncrypt = SecurityUtil.desEncrypt(pwd, "global.techgoal.com");
                encryptStr = SecurityUtil.md5DesEncrypt(desEncrypt);
            }
        } catch (Exception var3) {
            log.error("call 登录密码加密异常", var3);
        }

        return encryptStr;
    }

    public static String paymentEncrypt(String pwd) {
        String encryptStr = null;

        try {
            if (pwd != null) {
                String pwdMD5 = SecurityUtil.md5DesEncrypt(pwd);
                encryptStr = SecurityUtil.desEncrypt(pwdMD5, "global.payment.baofoo.com");
            }
        } catch (Exception var3) {
            log.error("call 支付密码加密异常", var3);
        }

        return encryptStr;
    }

    public static String secrueqaEncrypt(String answer) {
        String encryptStr = null;

        try {
            if (answer != null) {
                encryptStr = SecurityUtil.desEncrypt(answer, "global.secrueqa.baofoo.com");
            }
        } catch (Exception var3) {
            log.error("call 安全问题答案加密异常", var3);
        }

        return encryptStr;
    }

    public static String bankNoEncrypt(String bankNo) {
        String encryptStr = null;

        try {
            if (bankNo != null) {
                encryptStr = SecurityUtil.desEncrypt(bankNo, "global.bank.card.baofoo.com");
            }
        } catch (Exception var3) {
            log.error("call 卡号加密异常", var3);
        }

        return encryptStr;
    }

    public static String bankCardEncrypt(String bankCardNo) {
        String encryptStr = null;

        try {
            if (bankCardNo != null) {
                encryptStr = SecurityUtil.desDecrypt(bankCardNo, "global.bank.card.baofoo.com");
            }
        } catch (Exception var3) {
            log.error("call 卡号解密异常", var3);
        }

        return encryptStr;
    }
}
