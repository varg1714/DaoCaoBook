package com.daocao.userservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.daocao.dao.AddressMapper;
import com.daocao.entity.Address;
import com.daocao.entity.AddressExample;
import com.daocao.myentity.PageResult;
import com.daocao.user.service.AddressService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;
import java.util.List;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    /**
     * 查询全部
     */
    @Override
    public List<Address> findAll() {
        return addressMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Address> page = (Page<Address>) addressMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(Address address) {
        address.setIsdefault("0");
        addressMapper.insert(address);
    }


    /**
     * 修改
     */
    @Override
    public void update(Address address) {
        AddressExample example = new AddressExample();
        AddressExample.Criteria criteria = example.createCriteria();

        criteria.andOwnerEqualTo(address.getOwner());
        criteria.andIdEqualTo(address.getId());
        addressMapper.updateByExampleSelective(address, example);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Address findOne(Integer id) {
        return addressMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Integer[] ids, String username) {
        AddressExample example = new AddressExample();
        AddressExample.Criteria criteria = example.createCriteria();
        criteria.andOwnerEqualTo(username);
        for (Integer id : ids) {
            criteria.andIdEqualTo(id);
            addressMapper.deleteByExample(example);
        }
    }

    @Override
    public PageResult findPage(Address address, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        AddressExample example = new AddressExample();
        AddressExample.Criteria criteria = example.createCriteria();

        if (address != null) {
            if (address.getOwner() != null && address.getOwner().length() > 0) {
                criteria.andOwnerLike("%" + address.getOwner() + "%");
            }
            if (address.getProvince() != null && address.getProvince().length() > 0) {
                criteria.andProvinceLike("%" + address.getProvince() + "%");
            }
            if (address.getCity() != null && address.getCity().length() > 0) {
                criteria.andCityLike("%" + address.getCity() + "%");
            }
            if (address.getTown() != null && address.getTown().length() > 0) {
                criteria.andTownLike("%" + address.getTown() + "%");
            }
            if (address.getAddress() != null && address.getAddress().length() > 0) {
                criteria.andAddressLike("%" + address.getAddress() + "%");
            }
            if (address.getPhone() != null && address.getPhone().length() > 0) {
                criteria.andPhoneLike("%" + address.getPhone() + "%");
            }
            if (address.getContact() != null && address.getContact().length() > 0) {
                criteria.andContactLike("%" + address.getContact() + "%");
            }
            if (address.getIsdefault() != null && address.getIsdefault().length() > 0) {
                criteria.andIsdefaultLike("%" + address.getIsdefault() + "%");
            }

        }

        Page<Address> page = (Page<Address>) addressMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<Address> findByUser(String username) {

        AddressExample example = new AddressExample();
        AddressExample.Criteria criteria = example.createCriteria();
        criteria.andOwnerEqualTo(username);
        example.setOrderByClause("isDefault DESC");

        return addressMapper.selectByExample(example);
    }

    @Override
    public int setDefaultAddress(int id, String username) {

        // 首先将原来默认地址信息重置
        Address address = new Address();
        address.setIsdefault("0");

        AddressExample example = new AddressExample();
        AddressExample.Criteria criteria = example.createCriteria();
        criteria.andOwnerEqualTo(username);
        criteria.andIsdefaultEqualTo("1");
        addressMapper.updateByExampleSelective(address, example);

        // 更新新的默认地址
        address.setIsdefault("1");
        address.setId(id);
        addressMapper.updateByPrimaryKeySelective(address);
        return 0;
    }


}
