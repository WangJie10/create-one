package com.mall.xiaomi.vo;

import com.mall.xiaomi.pojo.Order;
import lombok.Data;

/**
 * @Auther: wdd
 * @Date: 2020-03-27 16:29
 * @Description:
 */
@Data
public class OrderVo extends Order {

    private String productName;

    private String productPicture;

    /**
     * 是否已评价
     */
    private boolean hasComment;

    /**
     * 评价等级
     */
    private Integer rate;

    /**
     * 评价内容
     */
    private String comment;

}
