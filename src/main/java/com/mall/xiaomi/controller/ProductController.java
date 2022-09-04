package com.mall.xiaomi.controller;

import com.github.pagehelper.PageInfo;
import com.mall.xiaomi.pojo.Product;
import com.mall.xiaomi.service.ProductService;
import com.mall.xiaomi.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/category/limit/{categoryId}")
    public ResultMessage getProductByCategoryId(@PathVariable Integer categoryId) {
        List<Product> list = productService.getProductByCategoryId(categoryId);
        return ResultMessage.success("001", list);
    }

    @GetMapping("/category/hot")
    public ResultMessage getHotProduct() {
        List<Product> list = productService.getHotProduct();
        return ResultMessage.success("001", list);

    }

    @GetMapping("/{productId}")
    public ResultMessage getProduct(@PathVariable String productId) {
        Product product = productService.getProductById(productId);
        return ResultMessage.success("001", product);
    }

    @GetMapping("/page/{currentPage}/{pageSize}/{categoryId}")
    public Map<String, Object> getProductByPage(@PathVariable String currentPage, @PathVariable String pageSize, @PathVariable String categoryId) {
        PageInfo<Product> pageInfo = productService.getProductByPage(currentPage, pageSize, categoryId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "001");
        map.put("data", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @GetMapping("/getProductBySearch/{currentPage}/{pageSize}")
    public Map<String, Object> getProductBySearch(@PathVariable String currentPage, @PathVariable String pageSize,@RequestParam String k) {
        PageInfo<Product> pageInfo = productService.getProductBySearch(currentPage, pageSize, k);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "001");
        map.put("data", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @PostMapping("")
    public ResultMessage addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResultMessage.success("001","成功");
    }

    @PutMapping("")
    public ResultMessage updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return ResultMessage.success("001","成功");
    }

    @DeleteMapping("/{productId}")
    public ResultMessage deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ResultMessage.success("001","成功");
    }


}
