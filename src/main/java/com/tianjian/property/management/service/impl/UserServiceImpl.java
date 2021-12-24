package com.tianjian.property.management.service.impl;

import com.tianjian.property.bean.Property;
import com.tianjian.property.bean.User;
import com.tianjian.property.dao.RolePropertyDao;
import com.tianjian.property.dao.UserDao;
import com.tianjian.property.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/9/27
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RolePropertyDao rolePropertyDao;
    @Override
    public List<Integer> selectUserByRole(Integer appUID) {
        ArrayList<Integer> integers = new ArrayList<>();
        List<Map<String, Object>> maps = userDao.selectUserByRole(appUID);
        for (int i = 0; i <maps.size() ; i++) {
            Map<String, Object> map = maps.get(i);
            Integer rId = (Integer) map.get("rId");
            integers.add(rId);
        }
        return integers;
    }

    @Override
    public List<Property> selectPropertyByRole(List<Integer> roleId) {
        List<Property> properties = rolePropertyDao.selectPropertyByRole(roleId,"property");
        return properties;
    }
}

