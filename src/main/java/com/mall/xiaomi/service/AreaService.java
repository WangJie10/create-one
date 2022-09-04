package com.mall.xiaomi.service;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mall.xiaomi.domain.AreaReq;
import com.mall.xiaomi.domain.AreaResp;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

/**
 * @description: 行政地区查询实现
 * @author: lhy
 * @since: 1.0.0
 */
@Service
public class AreaService {
    private static final RestTemplate restTemplate = new RestTemplate();

    private final static String url = "https://restapi.amap.com/v3/config/district";

    public List<AreaResp> provinceAll() {
        AreaReq req = new AreaReq();
        req.setKeywords("100000");
        req.setExtensions("base");
        req.setSubdistrict("1");
        return findArea(req);
    }

    public List<AreaResp> city(String province) {
        AreaReq req = new AreaReq();
        req.setKeywords(province);
        req.setExtensions("base");
        req.setSubdistrict("1");
        return findArea(req);
    }

    public List<AreaResp> district(String city) {
        AreaReq req = new AreaReq();
        req.setKeywords(city);
        req.setExtensions("base");
        req.setSubdistrict("1");
        return findArea(req);
    }


    private List<AreaResp> findArea(AreaReq req) {
        Map<String, Object> reqMap = BeanUtil.beanToMap(req);
        reqMap.put("key", "d2d573ad47926dd820ea7c145ce98bcf");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        reqMap.forEach(builder::queryParam);
        String uriString = builder.build().encode().toUriString();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uriString, String.class, reqMap);
        JSONArray districts = JSONObject.parseObject(responseEntity.getBody()).getJSONArray("districts").getJSONObject(0).getJSONArray("districts");
        return districts.toJavaList(AreaResp.class);
    }
}
