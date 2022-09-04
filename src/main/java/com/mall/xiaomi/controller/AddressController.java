package com.mall.xiaomi.controller;

import com.mall.xiaomi.pojo.Address;
import com.mall.xiaomi.service.AddressService;
import com.mall.xiaomi.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/all")
    public ResultMessage getOrder(@CookieValue("XM_TOKEN") String cookie) {
        // 先判断cookie是否存在，和redis校验
        Integer userId = (Integer) redisTemplate.opsForHash().get(cookie, "userId");
        List<Address> list = addressService.all(userId);
        return ResultMessage.success("001", list);
    }

    @GetMapping("/{id}")
    public ResultMessage getAddress(@PathVariable Integer id) {
        Address address = addressService.getAddressById(id);
        return ResultMessage.success("001", address);
    }

    @PostMapping("")
    public ResultMessage addAddress(@RequestBody Address address, @CookieValue("XM_TOKEN") String cookie) {
        Integer userId = (Integer) redisTemplate.opsForHash().get(cookie, "userId");
        address.setUserId(userId);
        addressService.addAddress(address);
        return ResultMessage.success("001", "成功");
    }

    @PutMapping("")
    public ResultMessage updateAddress(@RequestBody Address address) {
        addressService.updateAddress(address);
        return ResultMessage.success("001", "成功");
    }

    @DeleteMapping("/{addressId}")
    public ResultMessage deleteAddress(@PathVariable Integer addressId) {
        addressService.deleteAddress(addressId);
        return ResultMessage.success("001", "成功");
    }

}
