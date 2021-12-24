package com.tianjian.property.management.service;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/6/22
 */
public interface NetworkCardService {
    //修改网卡设备状态
    void updateStatus(Integer id, Integer status);
    //查询设备
    List selectEquipment(Integer pid);
    //查询设备状态
    Map selectEquipmentStatus(String Imei);

    Map openLock(String imei, String userid, String pid);
}
