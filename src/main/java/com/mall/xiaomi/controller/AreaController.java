package com.mall.xiaomi.controller;

import com.mall.xiaomi.domain.AreaResp;
import com.mall.xiaomi.service.AreaService;
import com.mall.xiaomi.util.ResultMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 区域
 *
 * @author lihaoyang
 * @since 2021/3/28
 */
@RestController
@RequestMapping("/area")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }


    @GetMapping("/province")
    public ResultMessage province() {
        List<AreaResp> areaResp = areaService.provinceAll();
        return ResultMessage.success("001", areaResp);
    }

    @GetMapping("/city")
    public ResultMessage city(@RequestParam String province) {
        List<AreaResp> areaResp = areaService.city(province);
        return ResultMessage.success("001", areaResp);
    }

    @GetMapping("/district")
    public ResultMessage district(@RequestParam String city) {
        List<AreaResp> areaResp = areaService.district(city);
        return ResultMessage.success("001", areaResp);
    }


}
