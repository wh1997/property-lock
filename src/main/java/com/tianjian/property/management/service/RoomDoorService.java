package com.tianjian.property.management.service;


import com.tianjian.property.bean.Door;
import com.tianjian.property.bean.vo.DoorVo;
import com.tianjian.property.bean.vo.RoomVo;
import com.tianjian.property.utils.PageResult;
import com.tianjian.property.utils.error.BusinessException;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface RoomDoorService {
    PageResult<DoorVo> selsctAll(Door door, Integer pageNum, Integer pageSize) ;
    Map<String, List<DoorVo>> selsctRoomnoAndPropertyname(Integer propertyid,String roomno,Integer pageNum,Integer pageSize);
    Map<String, List<DoorVo>> screenRoomDoor(Integer propertyid,Integer bulidingid ,String unitname,Integer pageNum,Integer pageSize);
    Map<String,Object> selectdoorparticulars(Integer doorid) throws ParseException, BusinessException;
    //模糊搜索
    List<Door> fuzzySearch(Integer propertyid, String fuzzy, Integer pageNum, Integer pageSize);

    Map addDoor(List<Map> door, Integer appUID) throws Exception;

    List<Integer> selectRoom(List<Integer> roomVo);
}
