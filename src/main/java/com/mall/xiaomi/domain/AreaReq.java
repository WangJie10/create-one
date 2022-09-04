package com.mall.xiaomi.domain;

import lombok.Data;

/**
 * 获取区域入参
 *
 * @author lihaoyang
 * @since 2021/3/28
 */
@Data
public class AreaReq {

    /**
     * 区号
     */
    private String extensions="base";

    /**
     * 邮政编码
     */
    private String keywords="100000";

    /**
     * 设置显示下级行政区级数
     */
    private String subdistrict="1";
}
