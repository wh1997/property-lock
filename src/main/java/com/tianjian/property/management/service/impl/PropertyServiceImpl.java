package com.tianjian.property.management.service.impl;

import com.tianjian.property.bean.Property;
import com.tianjian.property.dao.PropertyDao;
import com.tianjian.property.management.service.PropertyService;
import com.tianjian.property.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/7/2
 */
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyDao propertyDao;
    @Autowired
    private UserService userService;
    @Override
    public List getProperty(Integer appUID) {
        //根据登录用户id查询角色
        List<Integer> roleId = userService.selectUserByRole(appUID);
        //获取能获取的园区信息
        List<Property> list = userService.selectPropertyByRole(roleId);
        return  list;
    }

    @Override
    public List<Property> getListBySelect() {
        return propertyDao.selectByStatus();
    }
}
