package com.tianjian.property.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianjian.property.bean.Door;
import com.tianjian.property.bean.vo.DoorVo;
import com.tianjian.property.dao.DoorDao;
import com.tianjian.property.utils.PageResult;
import com.tianjian.property.web.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/4
 */
@Service
public class MonitorServiceImpl implements MonitorService {
    @Autowired
    private DoorDao doorDao;
    @Override
    public PageResult<DoorVo> selsctAll(Door door, List<Integer> list, Integer pageNum, Integer pageSize) {
        //查询小区下房间的具体信息
        PageHelper.startPage(pageNum,pageSize);
        List<DoorVo> all = doorDao.selectPageDoor(door,list);
        PageInfo<DoorVo> doorVoPageInfo = new PageInfo<>(all);
        List<DoorVo> Doorlist = doorVoPageInfo.getList();
        int pages = doorVoPageInfo.getPages();
        //总共多少条
        long total = doorVoPageInfo.getTotal();
        PageResult<DoorVo> doorVoPageResult = new PageResult<>(pageSize,pageNum,Doorlist,total,pages);
        return doorVoPageResult;
    }

    @Override
    @Transactional
    public int updateDoor(Door door) {
        return doorDao.updateByPrimaryKey(door);
    }

    @Override
    public PageResult<DoorVo> selectPublicDoor(Door door,List<Integer> types, List<Integer> list, Integer pageNum, Integer pageSize) {
        //查询小区下房间的具体信息
        PageHelper.startPage(pageNum,pageSize);
        List<DoorVo> all = doorDao.selectPublicDoor(door,types,list);
        PageInfo<DoorVo> doorVoPageInfo = new PageInfo<>(all);
        List<DoorVo> Doorlist = doorVoPageInfo.getList();
        int pages = doorVoPageInfo.getPages();
        //总共多少条
        long total = doorVoPageInfo.getTotal();
        PageResult<DoorVo> doorVoPageResult = new PageResult<>(pageSize,pageNum,Doorlist,total,pages);
        return doorVoPageResult;
    }
}
