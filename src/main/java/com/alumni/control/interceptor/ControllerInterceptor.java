package com.alumni.control.interceptor;

import com.alumni.control.dict.NumberDict;
import com.alumni.control.dict.SymbolDict;
import com.alumni.control.utils.TechGoalObjects;
import com.alumni.control.utils.page.BasePage;
import com.alumni.control.utils.page.PageParamConvert;
import com.alumni.control.utils.web.WebEnhance;
import com.alumni.control.utils.web.WebExceptionUtils;
import com.alumni.control.utils.web.WebResultModeEnum;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>
 * 1.
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/03/20
 */
@Slf4j
@Aspect
@Component
public class ControllerInterceptor {

    /**
     * 环绕增强 对facade层功能增强
     * 1.校验请求参数
     * 2.打印请求日志，响应日志，异常日志
     * 3.try-catch
     *
     * @param joinPoint
     * @return
     */
    @Around(value = "@annotation(com.alumni.control.utils.web.WebEnhance)")
    public Object doBefore(ProceedingJoinPoint joinPoint) {
        Object result = new Object();
        String classMethodName = "";
        WebResultModeEnum mode = WebResultModeEnum.AJAX;
        boolean reqLogFlag;
        boolean respLogFlag = false;
        WebEnhance annotation;
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            classMethodName = getClassMethodName(method);
            annotation = method.getAnnotation(WebEnhance.class);
            reqLogFlag = annotation.reqLogFlag();
            respLogFlag = annotation.respLogFlag();
            if (reqLogFlag) {
                String endStr = "请求参数:";
                for (Object item : joinPoint.getArgs()) {
                    endStr = endStr.concat("{},");
                }
                log.info(getLogStr(classMethodName, endStr), joinPoint.getArgs());
            }
            mode = annotation.mode();
            if (mode.equals(WebResultModeEnum.PAGE_QUERY)) {
                BasePage basePage = (BasePage) joinPoint.getArgs()[0];
                Integer currentPage = PageParamConvert.getCurrentPage(basePage.getCurrentPage());
                Integer pageSize = PageParamConvert.getPageSize(basePage.getPageSize());
                PageHelper.offsetPage(currentPage * pageSize, pageSize);
            }
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            switch (mode) {
                case AJAX:
                    result = WebExceptionUtils.getResponse(throwable);
                    break;
                case PAGE_QUERY:
                    result = PageParamConvert.getErrorPage();
                    break;
                default:
                    log.error("类型错误");
            }
            log.error(getLogStr(classMethodName, "调用异常"), throwable);
        } finally {
            if (mode.equals(WebResultModeEnum.PAGE_QUERY)) {
                PageHelper.clearPage();
            }
        }
        if (respLogFlag) {
            log.info(getLogStr(classMethodName, "响应参数:{}"), result);
        }
        return result;
    }

    /**
     * 获取抛出异常的接口和方法名
     *
     * @param method
     * @return
     */
    private String getClassMethodName(Method method) {
        String outInfo = "";
        String strMethod = method.toString();
        String[] split = strMethod.substring(NumberDict.ZERO, strMethod.lastIndexOf(SymbolDict.BRACKET)).split(SymbolDict.DOT);
        if (TechGoalObjects.isNotEmpty(split) && split.length > NumberDict.TWO) {
            outInfo = split[split.length - NumberDict.TWO].concat(",方法:").concat(split[split.length - NumberDict.ONE]).concat(",");
        }
        return outInfo;
    }

    /**
     * 获取日志字符串
     *
     * @param classMethodName
     * @param endStr
     * @return
     */
    private String getLogStr(String classMethodName, String endStr) {
        return classMethodName.concat(endStr);
    }

}