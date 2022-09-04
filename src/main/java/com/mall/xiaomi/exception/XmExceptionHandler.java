package com.mall.xiaomi.exception;

import com.mall.xiaomi.util.ResultMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: wdd
 * @Date: 2020-03-19 16:45
 * @Description:
 */
@ControllerAdvice
@ResponseBody
public class XmExceptionHandler {


    @ExceptionHandler(XmException.class)
    public ResultMessage handleException(XmException e) {
        ExceptionEnum em = e.getExceptionEnum();
        return ResultMessage.fail(em.getCode() + "", em.getMsg());
    }
}
