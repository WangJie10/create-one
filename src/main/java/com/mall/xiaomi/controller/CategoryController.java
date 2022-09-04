package com.mall.xiaomi.controller;

import com.mall.xiaomi.pojo.Category;
import com.mall.xiaomi.service.CategoryService;
import com.mall.xiaomi.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResultMessage category() {
        List<Category> categories = categoryService.getAll();
        return ResultMessage.success("001", categories);
    }

}
