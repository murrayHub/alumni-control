package com.alumni.control.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

/**
 * description : 令牌工具类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/9 22:43
 */
public class JwtUtil {
    private static String salt = "global_token_key";

    private JwtUtil() {
    }

    public static String getToken(Map<String, Object> claims, String tokenKey) {
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS512, tokenKey).compact();
    }

    public static String getToken(String subject, String tokenKey) {
        return Jwts.builder().setSubject(subject).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS512, tokenKey).compact();
    }

    public static String getKey() throws NoSuchAlgorithmException, InvalidKeyException {
        return encodeHmacSHA(salt.getBytes(Charset.defaultCharset()), initHmacKey());
    }

    public static String getKey(String salt) throws NoSuchAlgorithmException, InvalidKeyException {
        return encodeHmacSHA(salt.getBytes(Charset.defaultCharset()), initHmacKey());
    }

    private static byte[] initHmacKey() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance(SignatureAlgorithm.HS256.getJcaName());
        SecretKey secretKey = generator.generateKey();
        return secretKey.getEncoded();
    }

    private static String encodeHmacSHA(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKey secretKey = new SecretKeySpec(key, SignatureAlgorithm.HS256.getJcaName());
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        byte[] digest = mac.doFinal(data);
        return (new HexBinaryAdapter()).marshal(digest);
    }
}
