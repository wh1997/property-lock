package com.tianjian.property.web.service.impl;

import com.tianjian.property.bean.Address;
import com.tianjian.property.dao.AddressDao;
import com.tianjian.property.web.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/26
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    public List<Address> getListByIds(List<Integer> addressIds) {
        if(null == addressIds && addressIds.isEmpty()){
            return null;
        }
        List<Address> addresses = addressDao.selectWithParentByIds(addressIds);
        return addresses;
    }
}
