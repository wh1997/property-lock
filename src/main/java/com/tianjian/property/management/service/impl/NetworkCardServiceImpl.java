package com.tianjian.property.management.service.impl;

import com.tianjian.property.dao.PropertyDao;
import com.tianjian.property.dao.NetworkCardDao;
import com.tianjian.property.management.service.NetworkCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/6/22
 */
@Service
public class NetworkCardServiceImpl extends CardHttpService implements NetworkCardService {
    @Autowired
    private NetworkCardDao networkCardDao;
    @Autowired
    private PropertyDao propertyDao;
    @Value("${baiwei.BWLockURL}")
    private  String BWLockURL;
    @Value("${baiwei.open}")
    private  String open;
    @Value("${baiwei.status}")
    private  String status;
    @Value("${baiwei.query}")
    private  String query;
    @Override
    @Transactional
    public void updateStatus(Integer id, Integer status) {
        networkCardDao.updateStatus(id,status);
    }

    @Override
    public List selectEquipment(Integer pid) {
       Integer oldId= propertyDao.selectByPropertyId(pid);
        HashMap<String, String> map = new HashMap<>();
        map.put("Pid",oldId.toString());
        Map resultMap = postResult(BWLockURL + query, map);
        List resultList = (List) resultMap.get("result");
        return resultList;
    }
    @Override
    public Map selectEquipmentStatus(String Imei) {
        HashMap<String, String> map = new HashMap<>();
        map.put("imei",Imei);
        Map resultMap = postResult(BWLockURL + status, map);
        Map result = (Map) resultMap.get("result");
        return result;
    }

    @Override
    public Map openLock(String imei, String userid, String pid) {
        Integer oldId= propertyDao.selectByPropertyId(Integer.valueOf(pid));
        HashMap<String, String> map = new HashMap<>();
        map.put("imei",imei);
        map.put("userid",userid);
        map.put("Pid",oldId.toString());
        Map resultMap = postResult(BWLockURL + open, map);
        Map result = (Map) resultMap.get("result");
        return result;
    }
}
