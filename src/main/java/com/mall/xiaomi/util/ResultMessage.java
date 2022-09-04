package com.mall.xiaomi.util;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Auther: wdd
 * @Date: 2020-03-19 16:16
 * @Description:
 */
@Component
@Data
public class ResultMessage {

    private String code;
    private String msg;
    private Object data;

    public static ResultMessage success(String code, String msg, Object data) {
        return new ResultMessage(code,msg,data);
    }

    public  static ResultMessage success(String code, String msg) {
        return new ResultMessage(code,msg,null);
    }

    public  static ResultMessage success(String code, Object data) {
        return new ResultMessage(code,null,data);
    }

    public  static ResultMessage fail(String code, String msg) {
        return new ResultMessage(code,msg,null);
    }

    public ResultMessage() {
    }

    public ResultMessage(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
