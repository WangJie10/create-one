package com.mall.xiaomi.controller;

import com.mall.xiaomi.pojo.ProductComment;
import com.mall.xiaomi.service.ProductCommentService;
import com.mall.xiaomi.util.ResultMessage;
import com.mall.xiaomi.vo.ProductCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productComment")
public class ProductCommentController {
    @Autowired
    private ProductCommentService productCommentService;

    @GetMapping("/list/{productId}")
    public ResultMessage commentList(@PathVariable Integer productId) {
        List<ProductCommentVo> list = productCommentService.commentList(productId);
        return ResultMessage.success("001", list);
    }

    @PostMapping("")
    public ResultMessage addComment(@RequestBody ProductComment comment) {
        productCommentService.addComment(comment);
        return ResultMessage.success("001", "成功");
    }
}
