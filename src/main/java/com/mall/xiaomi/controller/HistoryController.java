package com.mall.xiaomi.controller;

import com.mall.xiaomi.pojo.Product;
import com.mall.xiaomi.service.HistoryService;
import com.mall.xiaomi.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;


    @GetMapping("/user/{userId}")
    public ResultMessage getHistory(@PathVariable String userId) {
        List<Product> list = historyService.getHistory(userId);
        return ResultMessage.success("001", list);

    }

    @PostMapping("/user/{productId}/{userId}")
    public ResultMessage addHistory(@PathVariable Integer userId, @PathVariable Integer productId) {
        historyService.addHistroy(userId, productId);
        return ResultMessage.success("001", "商品收藏成功");
    }
}
