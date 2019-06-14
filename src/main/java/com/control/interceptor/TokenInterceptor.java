package com.techGoal.interceptor;


import com.techGoal.constant.MDCPropertyConsts;
import com.techGoal.constant.RedisDict;
import com.techGoal.dict.CharacterDict;
import com.techGoal.dict.CommonDict;
import com.techGoal.dict.NumberLongDict;
import com.techGoal.pojo.dto.CommonParamDto;
import com.techGoal.redis.RedisManager;
import com.techGoal.utils.JsonUtil;
import com.techGoal.utils.SecurityUtil;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

/**
 * spring 拦截器重写，用于生成日志ID信息
 * <p>
 * 1、拦截器重写
 * </p>
 *
 * @author Murray
 * @version : 1.0
 * @date : 2019/03/20
 */
@Slf4j
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisManager redisManager;

    /**
     * This implementation always returns {@code true}.
     *
     * @param request  请求参数信息
     * @param response response对象
     * @param handler  对象信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        before(request, response);
//        if (checkToken(request, response)) {
//            return false;
//        }
        return super.preHandle(request, response, handler);
    }
    /**
     * token认证
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private boolean checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = getByName(request, CommonDict.TOKEN);
        int httpCode = 401;
        if (StringUtils.isBlank(token)) {
            response.sendError(httpCode, "error message");
            log.info("token不存在，请求已拦截");
            return true;
        }
        try {
            String[] split = token.split("\\.");
            //reidKey组装
            String redisKey = RedisDict.TOKEN + JsonUtil.toObject(SecurityUtil.base64Decode(split[1]), Map.class).get("sub");
            //获取redis缓存中的信息
            CommonParamDto commonParamDto = redisManager.queryObjectByKey(redisKey, CommonParamDto.class);
            if (commonParamDto == null) {
                log.warn("用户登录已超时或请求token不正确，请求token：{},redisKey:{}", token, redisKey);
                response.sendError(httpCode, "登录超时或异常请求");
                return true;
            }
            //token验证
            Jwts.parser().setSigningKey(commonParamDto.getTokenKey()).parseClaimsJws(token).getBody();
            request.setAttribute("commonParam", commonParamDto);
            //新的缓存插入redis中
            redisManager.insertObject(commonParamDto, redisKey, NumberLongDict.TWO_HOUR_SECOND);
        } catch (Exception e) {
            response.sendError(httpCode, "error message");
            log.error("token不正确，请求已拦截", e);
            return true;
        }
        return false;
    }

    /**
     * 设置日志id，请求时间
     *
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    private void before(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //请求时间
        Long startTime = System.currentTimeMillis();
        MDC.put(CommonDict.REQ_TIME_KEY, startTime.toString());
        //设置请求和响应编码
        request.setCharacterEncoding(CharacterDict.UTF_8);
        response.setCharacterEncoding(CharacterDict.UTF_8);
        //设置日志ID
        MDC.put(MDCPropertyConsts.TRACE_LOG_ID, UUID.randomUUID().toString());
        String requestPath = request.getRequestURI();
        log.info("用户请求后台，地址：{}", requestPath);
    }


    /**
     * 请求执行完成之后调用
     *
     * @param request  请求对象
     * @param response 返回对象
     * @param handler  处理对象
     * @param ex       异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Long intervalTime = (System.currentTimeMillis() - Long.parseLong(MDC.get(CommonDict.REQ_TIME_KEY)));
        if (ex != null) {
            log.error("请求系统异常，异常内容：", ex);
        }
        log.info("本次请求总耗时：{}", intervalTime);
        MDC.clear();
    }

    private String getByName(HttpServletRequest request, String cookieName) {
        Cookie cookie = WebUtils.getCookie(request, cookieName);
        return cookie == null ? null : cookie.getValue();
    }
}
