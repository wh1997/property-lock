package com.tianjian.property.management.service;


import com.tianjian.property.bean.LockAuthCode;
import com.tianjian.property.bean.vo.LockBaseInfoVo;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.PageResult;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

public interface EquipmentService {
    //添加蓝牙门锁
    LockResult addBluetooth (Map lockBaseInfo, Map lockAuthCode, String hardwareVersion, String softwareVersion, Integer doorid, String addpeople) throws Exception;
    //设备列表
    PageResult<? extends Object> selectList(Integer equipmentType, Integer propertyId, Integer pageNum, Integer pageSize,String keyWord);
    //设备详情
    Object selectEquipment(Integer equipmentType, Integer equipmentId);
    //模糊搜素设备
    Object fuzzySearch(Integer propertyId,Integer equipmentType, String KeyWord,Integer pageNum,Integer pageSize);
}
