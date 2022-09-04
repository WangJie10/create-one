package com.mall.xiaomi.service;

import com.mall.xiaomi.exception.ExceptionEnum;
import com.mall.xiaomi.exception.XmException;
import com.mall.xiaomi.mapper.HistoryMapper;
import com.mall.xiaomi.pojo.History;
import com.mall.xiaomi.pojo.Product;
import com.mall.xiaomi.pojo.ProductComment;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: wdd
 * @Date: 2020-03-19 13:16
 * @Description:
 */
@Service
public class HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    public List<Product> getHistory(String userId) {
        List<Product> list = new ArrayList<>();
        try {
            list = historyMapper.getHistory(userId);
            if (ArrayUtils.isEmpty(list.toArray())) {
                return list;
            }
        } catch (XmException e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_COLLECT_ERROR);
        }
        return list;
    }

    public void addHistroy(Integer userId, Integer productId) {
        Example example = new Example(History.class);
        example.createCriteria().andEqualTo("userId",userId).andEqualTo("productId",productId);
        History history = historyMapper.selectOneByExample(example);
        if (history!=null){
            history.setCreateTime(LocalDateTime.now());
            historyMapper.updateByPrimaryKey(history);
        }else {
            history=new History();
            history.setUserId(userId);
            history.setProductId(productId);
            history.setCreateTime(LocalDateTime.now());
            historyMapper.insert(history);
        }
    }
}
