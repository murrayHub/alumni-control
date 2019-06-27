package com.alumni.control.interceptor;


import com.alumni.control.constant.MDCPropertyConsts;
import com.alumni.control.constant.RedisDict;
import com.alumni.control.dict.CharacterDict;
import com.alumni.control.dict.CommonDict;
import com.alumni.control.dict.NumberLongDict;
import com.alumni.control.pojo.dto.CommonParamDto;
import com.alumni.control.redis.RedisManager;
import com.alumni.control.utils.JsonUtil;
import com.alumni.control.utils.SecurityUtil;
import com.alumni.control.utils.TechGoalObjects;
import com.alumni.control.utils.web.AjaxResult;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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
        if (checkToken(request, response)) {
            return false;
        }
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
        String token = request.getHeader("Authorization");
        int httpCode = 401;
        try {
            if (TechGoalObjects.isEmpty(token) || "null".equals(token)) {
                log.info("token不存在，请求已拦截");
                AjaxResult<String> ajaxResult = new AjaxResult<>();
                ajaxResult.setCode(999);
                ajaxResult.setMessage("登录Session超时");
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                log.warn("用户登录超时，返回Ajax错误信息");
                response.getOutputStream().write(JsonUtil.toJSONString(ajaxResult).getBytes(StandardCharsets.UTF_8));
                return true;
            }
            String[] split = token.split("\\.");
            //reidKey组装
            String redisKey = RedisDict.TOKEN + JsonUtil.toObject(SecurityUtil.base64Decode(split[1]), Map.class).get("sub");
            //获取redis缓存中的信息
            CommonParamDto commonParamDto = redisManager.queryObjectByKey(redisKey, CommonParamDto.class);
            if (commonParamDto == null) {
                log.warn("用户登录已超时或请求token不正确，请求token：{},redisKey:{}", token, redisKey);
                AjaxResult<String> ajaxResult = new AjaxResult<>();
                ajaxResult.setCode(999);
                ajaxResult.setMessage("登录Session超时");
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                log.warn("用户登录超时，返回Ajax错误信息");
                response.getOutputStream().write(JsonUtil.toJSONString(ajaxResult).getBytes(StandardCharsets.UTF_8));
                return true;
            }
            //token验证
            Jwts.parser().setSigningKey(commonParamDto.getTokenKey()).parseClaimsJws(token).getBody();
            request.setAttribute("commonParam", commonParamDto);
            //新的缓存插入redis中
            redisManager.insertObject(commonParamDto, redisKey, NumberLongDict.TWO_HOUR);
        } catch (Exception e) {
            log.error("token不正确，请求已拦截", e);
            AjaxResult<String> ajaxResult = new AjaxResult<>();
            ajaxResult.setCode(999);
            ajaxResult.setMessage("登录Session超时");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            log.warn("用户登录超时，返回Ajax错误信息");
            response.getOutputStream().write(JsonUtil.toJSONString(ajaxResult).getBytes(StandardCharsets.UTF_8));
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
