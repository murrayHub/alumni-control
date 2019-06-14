package com.alumni.control.utils.logbackUtil;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * 描述：TraceLogId转换器
 * <p>
 * 1、获取TraceLogId的值
 * </p>
 *
 * @author : Murray
 * @date : 2018/12/7
 */
public class TraceLogIdConvert extends ClassicConverter {

  @Override
  public String convert(ILoggingEvent event) {
    try {
      return event.getMDCPropertyMap().get(MDCPropertyConsts.TRACE_LOG_ID);
    } catch (Exception e) {
      return "get mdc property error";
    }
  }
}
