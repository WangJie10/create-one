package com.mall.xiaomi.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取区域出参
 *
 * @author lihaoyang
 * @since 2021/3/28
 */
@Data
public class AreaResp implements Serializable {

    /**
     * 区号
     */
    private String cityCode;

    /**
     * 邮政编码
     */
    private String adCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 坐标中心
     */
    private String center;

    /**
     * 设置显示下级行政区级数
     */
    private String subDistrict;

    /**
     * 等级 省市县
     */
    private String level;
}
