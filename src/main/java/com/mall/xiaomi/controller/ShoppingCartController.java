package com.mall.xiaomi.controller;

import com.mall.xiaomi.service.ShoppingCartService;
import com.mall.xiaomi.util.ResultMessage;
import com.mall.xiaomi.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: wdd
 * @Date: 2020-03-19 13:27
 * @Description:
 */
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    /**
     * 获取购物车信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    public ResultMessage cart(@PathVariable String userId) {
        List<CartVo> carts = cartService.getCartByUserId(userId);
        return ResultMessage.success("001", carts);
    }

    /**
     * 添加购物车
     *
     * @param productId
     * @param userId
     * @return
     */
    @PostMapping("/product/user/{productId}/{userId}")
    public ResultMessage cart(@PathVariable String productId, @PathVariable String userId) {
        CartVo cartVo = cartService.addCart(productId, userId);
        if (cartVo != null) {
            return ResultMessage.success("001", "添加购物车成功", cartVo);
        } else {
            return ResultMessage.success("002", "该商品已经在购物车，数量+1");
        }
    }

    @PutMapping("/user/num/{cartId}/{userId}/{num}")
    public ResultMessage cart(@PathVariable String cartId, @PathVariable String userId, @PathVariable String num) {
        cartService.updateCartNum(cartId, userId, num);
        return ResultMessage.success("001", "更新成功");
    }

    @DeleteMapping("/user/{cartId}/{userId}")
    public ResultMessage deleteCart(@PathVariable String cartId, @PathVariable String userId) {
        cartService.deleteCart(cartId, userId);
        return ResultMessage.success("001", "删除成功");
    }
}
