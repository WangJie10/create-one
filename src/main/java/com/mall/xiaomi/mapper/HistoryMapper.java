package com.mall.xiaomi.mapper;

import com.mall.xiaomi.pojo.History;
import com.mall.xiaomi.pojo.Product;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HistoryMapper extends Mapper<History> {

    @Select("select product.* from product, history where history.user_id = #{userId} and history.product_id = product.product_id order by history.create_time desc")
    List<Product> getHistory(String userId);

}