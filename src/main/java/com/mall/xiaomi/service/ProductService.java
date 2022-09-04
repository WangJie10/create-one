package com.mall.xiaomi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.xiaomi.exception.ExceptionEnum;
import com.mall.xiaomi.exception.XmException;
import com.mall.xiaomi.mapper.ProductMapper;
import com.mall.xiaomi.pojo.Product;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Auther: wdd
 * @Date: 2020-03-19 13:21
 * @Description:
 */
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> getProductByCategoryId(Integer categoryId) {
        List<Product> list = null;
        Example example = new Example(Product.class);
        example.orderBy("productSales").desc();
        example.createCriteria().andEqualTo("categoryId", categoryId);
        PageHelper.startPage(0, 8);
        try {
            list = productMapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_PRODUCT_ERROR);
        }
        return list;
    }

    public List<Product> getHotProduct() {
        Example example = new Example(Product.class);
        example.orderBy("productSales").desc();

        PageHelper.startPage(0, 8);
        List<Product> list = null;
        try {
            list = productMapper.selectByExample(example);
            if (ArrayUtils.isEmpty(list.toArray())) {
                throw new XmException(ExceptionEnum.GET_PRODUCT_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_PRODUCT_ERROR);
        }
        return list;
    }

    public Product getProductById(String productId) {
        Product product = null;
        try {
            product = productMapper.selectByPrimaryKey(productId);
            if (product == null) {
                throw new XmException(ExceptionEnum.GET_PRODUCT_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_PRODUCT_ERROR);
        }
        return product;
    }

    public PageInfo<Product> getProductByPage(String currentPage, String pageSize, String categoryId) {
        List<Product> list = null;
        PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize), true);
        if (categoryId.equals("0")) { // 为0，代表分页查询所有商品
            list = productMapper.selectAll();
        } else {
            // 分类分页查询商品
            Product product = new Product();
            if (StringUtils.isNotEmpty(categoryId)) {
                product.setCategoryId(Integer.parseInt(categoryId));
            }
            list = productMapper.select(product);
        }
        return new PageInfo<Product>(list);
    }

    public void addProduct(Product product) {
        if (product.getProductSales()==null){
            product.setProductSales(0);
        }
        productMapper.insert(product);
    }

    public void updateProduct(Product product) {
        productMapper.updateByPrimaryKey(product);
    }

    public void deleteProduct(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    public PageInfo<Product> getProductBySearch(String currentPage, String pageSize, String k) {
        PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize), true);
        Example example = new Example(Product.class);
        if (StringUtils.isNotBlank(k)) {
            example.createCriteria().andLike("productName", "%" + k + "%");
        }
        List<Product> list = productMapper.selectByExample(example);
        return new PageInfo<Product>(list);
    }
}
