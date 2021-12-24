package com.tianjian.property.management.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianjian.property.bean.Door;
import com.tianjian.property.dao.DoorDao;
import com.tianjian.property.dao.DoorTypeDao;
import com.tianjian.property.management.service.CommonDoorService;
import com.tianjian.property.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonDoorServiceImpl implements CommonDoorService {
    @Autowired
    private DoorDao doorDao;
    @Autowired
    private DoorTypeDao doorTypeDao;
    @Override
    @Transactional
    public int addCommonDoor(Door door) {
        Door door1 = doorDao.selectOne(door);
        if (door1!=null){
            return 200;
        }else{
            return  doorDao.insert(door);
        }
    }

    @Override
    public List<Map<String, List<Door>>> selectCommonDoor( Integer propertyid, Integer doortype,Integer pageNum,Integer pageSize) {
        //查询有哪些楼栋
       List<String> bulidingnameList =doorDao.selectbuildingid(propertyid,doortype,null);
        //存储返回值
        ArrayList<Map<String, List<Door>>> maps = new ArrayList<>();
        if (bulidingnameList.size()<=0){
           return null;
       }else{
            //设置分页参数
            PageHelper.startPage(pageNum,pageSize);
           //查询小区门锁的具体信息
           List<Door> doorList=  doorDao.selectCommonDoor(propertyid,doortype);
            PageInfo<Door> doorPageInfo = new PageInfo<>(doorList);
            List<Door> list = doorPageInfo.getList();
            for (int a = 0; a <bulidingnameList.size() ; a++) {
                   //获取到有哪些门锁
                   String Bulidingname = bulidingnameList.get(a);
                   //遍历所有房间信息
                   ArrayList<Door> doors = new ArrayList<>();
                   for (int i = 0; i <list.size() ; i++) {
                       Door door = list.get(i);
                       //判断楼栋下门锁信息
                       if (Bulidingname.equals(door.getBuildingName())){
                           doors.add(door);
                       }
                   }
                   //键为楼栋，值为楼栋里门锁信息
                   HashMap<String, List<Door>> map = new HashMap<>();
                   map.put(Bulidingname,doors);
                   maps.add(map);
               }
           return maps;
       }
    }

    @Override
    public PageResult<Door> fuzzyQueryCommonDoor(String doorName, Integer doorType, Integer pageNum, Integer pageSize, Integer propertyId) {
        PageHelper.startPage(pageNum,pageSize);
        List<Door> list=doorDao.fuzzyQueryCommonDoor("%"+doorName+"%",doorType,propertyId);
        PageInfo<Door> doorPageInfo = new PageInfo<>(list);
        List<Door> doorList = doorPageInfo.getList();
        int pages = doorPageInfo.getPages();
        long total = doorPageInfo.getTotal();
        return new PageResult<>(pageSize,pageNum,doorList,total,pages);
    }

    @Override
    public List<Map> selectDoorType() {
        return  doorTypeDao.selectDoorType();
    }
}
