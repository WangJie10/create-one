package com.mall.xiaomi.service;

import com.mall.xiaomi.exception.ExceptionEnum;
import com.mall.xiaomi.exception.XmException;
import com.mall.xiaomi.mapper.ProductCommentMapper;
import com.mall.xiaomi.mapper.UserMapper;
import com.mall.xiaomi.pojo.ProductComment;
import com.mall.xiaomi.pojo.User;
import com.mall.xiaomi.vo.ProductCommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCommentService {

    @Autowired
    private ProductCommentMapper productCommentMapper;

    @Autowired
    private UserMapper userMapper;

    public List<ProductCommentVo> commentList(int productId) {
        List<ProductCommentVo> list = new ArrayList<>();
        Example example = new Example(ProductComment.class);
        example.orderBy("createTime").desc();
        example.createCriteria().andEqualTo("productId", productId);
        try {
            List<ProductComment> productComments = productCommentMapper.selectByExample(example);
            if (productComments != null && productComments.size() > 0) {
                productComments.forEach(productComment -> {
                    ProductCommentVo productCommentVo = new ProductCommentVo();
                    BeanUtils.copyProperties(productComment, productCommentVo);
                    User user = userMapper.selectByPrimaryKey(productComment.getUserId());
                    if (user != null) {
                        productCommentVo.setUsername(user.getUsername());
                    }
                    list.add(productCommentVo);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_PRODUCT_ERROR);
        }
        return list;
    }

    public void addComment(ProductComment comment) {
        comment.setCreateTime(LocalDateTime.now());
        productCommentMapper.insert(comment);
    }
}
