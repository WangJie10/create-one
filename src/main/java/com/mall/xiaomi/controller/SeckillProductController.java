package com.mall.xiaomi.controller;

import com.mall.xiaomi.pojo.SeckillProduct;
import com.mall.xiaomi.pojo.SeckillTime;
import com.mall.xiaomi.service.SeckillProductService;
import com.mall.xiaomi.util.ResultMessage;
import com.mall.xiaomi.vo.SeckillProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: wdd
 * @Date: 2020-03-28 19:58
 * @Description:
 */
@RestController
@RequestMapping("/seckill/product")
public class SeckillProductController {

    @Autowired
    private SeckillProductService seckillProductService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据时间id获取对应时间的秒杀商品列表
     *
     * @param timeId
     * @return
     */
    @GetMapping("/time/{timeId}")
    public ResultMessage getProduct(@PathVariable String timeId) {
        List<SeckillProductVo> seckillProductVos = seckillProductService.getProduct(timeId);
        return ResultMessage.success("001", seckillProductVos);
    }

    /**
     * 获取秒杀商品
     *
     * @param seckillId
     * @return
     */
    @GetMapping("/{seckillId}")
    public ResultMessage getSeckill(@PathVariable String seckillId) {
        SeckillProductVo seckillProductVo = seckillProductService.getSeckill(seckillId);
        return ResultMessage.success("001", seckillProductVo);
    }

    /**
     * 删除秒杀商品
     *
     * @param seckillId
     * @return
     */
    @DeleteMapping("/{seckillId}")
    public ResultMessage deleteSeckill(@PathVariable String seckillId) {
        seckillProductService.deleteSeckill(seckillId);
        return ResultMessage.success("001", "成功");
    }

    /**
     * 获取时间段
     *
     * @return
     */
    @GetMapping("/time")
    public ResultMessage getTime() {
        List<SeckillTime> seckillTimes = seckillProductService.getTime();
        return ResultMessage.success("001", seckillTimes);
    }

    @DeleteMapping("time/{timeId}")
    public ResultMessage deleteTime(@PathVariable Integer timeId) {
        seckillProductService.deleteTime(timeId);
        return ResultMessage.success("001", "成功");
    }

    @PostMapping("time")
    public ResultMessage addTime(@RequestBody SeckillTime seckillTime) {
        seckillProductService.addTime(seckillTime);
        return ResultMessage.success("001", "成功");
    }

    /**
     * 添加秒杀商品
     *
     * @param seckillProduct
     * @return
     */
    @PostMapping("")
    public ResultMessage addSeckillProduct(@RequestBody SeckillProduct seckillProduct) {
        seckillProductService.addSeckillProduct(seckillProduct);
        return ResultMessage.success("001", "添加成功");
    }

    /**
     * 开始秒杀
     *
     * @param seckillId
     * @return
     */
    @PostMapping("/seckill/{seckillId}")
    public ResultMessage seckillProduct(@PathVariable String seckillId, @CookieValue("XM_TOKEN") String cookie) {
        // 先判断cookie是否存在，和redis校验
        Integer userId = (Integer) redisTemplate.opsForHash().get(cookie, "userId");
        seckillProductService.seckillProduct(seckillId, userId);
        return ResultMessage.success("001", "排队中");
    }


}
