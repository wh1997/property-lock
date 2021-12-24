package com.tianjian.property.web.service;

import com.tianjian.property.bean.Door;
import com.tianjian.property.bean.vo.DoorVo;
import com.tianjian.property.utils.PageResult;

import java.util.List;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/4
 */
public interface MonitorService {
    PageResult<DoorVo> selsctAll(Door door, List<Integer> list, Integer pageNum, Integer pageSize);

    int updateDoor(Door door);

    PageResult<DoorVo> selectPublicDoor(Door door,List<Integer> types, List<Integer> list, Integer pageNum, Integer pageSize);
}
