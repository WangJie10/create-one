package com.mall.xiaomi.service;

import com.mall.xiaomi.mapper.AddressMapper;
import com.mall.xiaomi.pojo.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Auther: wdd
 * @Date: 2020-03-19 13:16
 * @Description:
 */
@Service
public class AddressService {

    @Autowired
    private AddressMapper addressMapper;

    public List<Address> all(Integer userId) {
        Example example = new Example(Address.class);
        example.createCriteria().andEqualTo("userId", userId);
        return addressMapper.selectByExample(example);
    }

    public Address getAddressById(Integer id) {
        return addressMapper.selectByPrimaryKey(id);
    }

    public void addAddress(Address address) {
        addressMapper.insert(address);
    }

    public void updateAddress(Address address) {
        addressMapper.updateByPrimaryKey(address);
    }

    public void deleteAddress(Integer addressId) {
        addressMapper.deleteByPrimaryKey(addressId);
    }
}
