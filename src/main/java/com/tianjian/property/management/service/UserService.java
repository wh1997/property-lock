package com.tianjian.property.management.service;

import com.tianjian.property.bean.Property;

import java.util.List;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/9/27
 */
public interface UserService {
    List<Integer> selectUserByRole(Integer appUID);
    List<Property> selectPropertyByRole(List<Integer> roleId);
}
