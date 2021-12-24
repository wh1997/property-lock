package com.tianjian.property.web.service;

import com.tianjian.property.bean.Address;

import java.util.List;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/26
 */
public interface AddressService {
    List<Address> getListByIds(List<Integer> addressIds);
}
