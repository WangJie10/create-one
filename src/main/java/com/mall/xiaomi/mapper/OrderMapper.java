package com.mall.xiaomi.mapper;

import com.mall.xiaomi.pojo.Order;
import com.mall.xiaomi.vo.OrderVo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderMapper extends Mapper<Order> {

    @Select("select `order`.*, product.product_name as productName, product.product_picture as productPicture,product_comment.rate,product_comment.content as comment,IF(product_comment.id IS NOT NULL,1,0) AS hasComment from `order` JOIN product ON `order`.product_id = product.product_id LEFT JOIN product_comment ON product_comment.order_id=order.order_id AND product_comment.product_id=order.product_id WHERE `order`.user_id = #{userId} order by `order`.order_time desc")
    List<OrderVo> getOrderVoByUserId(Integer userId);
}