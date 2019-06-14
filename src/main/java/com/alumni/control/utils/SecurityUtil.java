package com.alumni.control.utils;

import com.alumni.control.enums.BFErrorCodeEnum;
import com.alumni.control.exception.BizServiceException;
import com.alumni.control.exception.CommonErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * description : 加解密-工具类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 15:25
 */
public class SecurityUtil {
    private static final Logger log = LoggerFactory.getLogger(SecurityUtil.class);

    private SecurityUtil() {
    }

    public static String md5DesEncrypt(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md5.digest();
            StringBuilder hexString = new StringBuilder();

            for(int i = 0; i < digest.length; ++i) {
                String strTemp = Integer.toHexString(digest[i] & 255 | -256).substring(6);
                hexString.append(strTemp);
            }

            return hexString.toString();
        } catch (Exception var6) {
            log.error("call MD5加密异常", var6);
            return str;
        }
    }

    public static String desDecrypt(String source, String desKey, String ivStr) {
        byte[] destBytes = new byte[0];

        try {
            byte[] sourceBytes = new byte[source.length() / 2];

            for(int i = 0; i < sourceBytes.length; ++i) {
                sourceBytes[i] = (byte)Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
            }

            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
            cipher.init(2, new SecretKeySpec(desKey.getBytes(), "DESede"), iv);
            destBytes = cipher.doFinal(sourceBytes);
        } catch (Exception var7) {
            log.error("DES解密发生错误：", var7);
        }

        return new String(destBytes, StandardCharsets.UTF_8);
    }

    public static String md5DesEncrypt(String str, String salt) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.concat(salt).getBytes(StandardCharsets.UTF_8));
            byte[] digest = md5.digest();
            StringBuilder hexString = new StringBuilder();

            for(int i = 0; i < digest.length; ++i) {
                String strTemp = Integer.toHexString(digest[i] & 255 | -256).substring(6);
                hexString.append(strTemp);
            }

            return hexString.toString();
        } catch (Exception var7) {
            log.error("call MD5加密异常", var7);
            return str;
        }
    }

    public static String desEncrypt(String source, String desKey) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, keyFactory.generateSecret(new DESKeySpec(desKey.getBytes(StandardCharsets.UTF_8))));
            byte[] destBytes = cipher.doFinal(source.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexRetSB = new StringBuilder();
            byte[] var6 = destBytes;
            int var7 = destBytes.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                byte b = var6[var8];
                String hexString = Integer.toHexString(255 & b);
                hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
            }

            return hexRetSB.toString();
        } catch (Exception var11) {
            log.error("DES加密发生错误：", var11);
            throw new BizServiceException(BFErrorCodeEnum.ERROR_CODE_100005);
        }
    }

    public static String desDecrypt(String source, String desKey) {
        byte[] sourceBytes = new byte[source.length() / 2];

        for(int i = 0; i < sourceBytes.length; ++i) {
            sourceBytes[i] = (byte)Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
        }

        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, keyFactory.generateSecret(new DESKeySpec(desKey.getBytes(StandardCharsets.UTF_8))));
            byte[] destBytes = cipher.doFinal(sourceBytes);
            return new String(destBytes, StandardCharsets.UTF_8);
        } catch (Exception var6) {
            log.error("DES解密发生错误：", var6);
            throw new BizServiceException(BFErrorCodeEnum.ERROR_CODE_100006);
        }
    }

    public static String base64EncodeStr(byte[] bytes) {
        try {
            return Base64.encodeBase64String(bytes);
        } catch (Exception var2) {
            log.error("Base64加密异常：", var2);
            return "";
        }
    }

    public static byte[] base64Encode(byte[] bytes) {
        byte[] encodeBase64 = null;

        try {
            encodeBase64 = Base64.encodeBase64(bytes);
        } catch (Exception var3) {
            log.error("base64加密是错", var3);
        }

        return encodeBase64;
    }

    public static String base64Encode(String src) {
        try {
            return Base64.encodeBase64String(src.getBytes(StandardCharsets.UTF_8));
        } catch (Exception var2) {
            log.error("Base64加密异常：", var2);
            return "";
        }
    }

    public static String base64Decode(String src) {
        return new String((new Base64()).decode(src), StandardCharsets.UTF_8);
    }

    public static String aesEncrypt(String data, String key, String iv) {
        if (!StringUtils.isBlank(key) && key.length() == 16 && iv.length() == 16) {
            return aesEncrypt(data, key, iv.getBytes());
        } else {
            throw new BizServiceException(BFErrorCodeEnum.ERROR_CODE_000002, "AES参数错误，iv,key不能为空且等于16位数");
        }
    }

    public static String aesEncrypt(String data, String key) {
        if (!StringUtils.isBlank(key) && key.length() == 16) {
            return aesEncrypt(data, key, key.getBytes());
        } else {
            throw new BizServiceException(BFErrorCodeEnum.ERROR_CODE_000002, "AES参数错误，iv,key不能为空且等于16位数");
        }
    }

    public static String aesDecrypt(String data, String key, String iv) {
        if (!StringUtils.isBlank(key) && key.length() == 16 && iv.length() == 16) {
            return aesDecrypt(data, key, iv.getBytes());
        } else {
            throw new BizServiceException(BFErrorCodeEnum.ERROR_CODE_000002, "AES参数错误，iv,key不能为空且等于16位数");
        }
    }

    public static String aesDecrypt(String data, String key) {
        if (!StringUtils.isBlank(key) && key.length() == 16) {
            return aesDecrypt(data, key, key.getBytes());
        } else {
            throw new BizServiceException(BFErrorCodeEnum.ERROR_CODE_000002, "AES参数错误，key不能为空且等于16位数");
        }
    }

    private static String aesDecrypt(String data, String key, byte[] iv) {
        try {
            byte[] raw = key.getBytes(StandardCharsets.US_ASCII);
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameter = new IvParameterSpec(iv);
            cipher.init(2, keySpec, ivParameter);
            byte[] encrypted1 = Base64.decodeBase64(data);
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original);
        } catch (Exception var9) {
            log.error("AES解密异常", var9);
            throw new BizServiceException(CommonErrorCode.UNEXPECTED_ERROR, "AES解密发生错误");
        }
    }

    private static String aesEncrypt(String data, String key, byte[] iv) {
        try {
            byte[] raw = key.getBytes();
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameter = new IvParameterSpec(iv);
            cipher.init(1, keySpec, ivParameter);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            return base64EncodeStr(encrypted);
        } catch (Exception var8) {
            log.error("AES加密异常", var8);
            throw new BizServiceException(CommonErrorCode.UNEXPECTED_ERROR, "AES加密发生错误");
        }
    }
}
