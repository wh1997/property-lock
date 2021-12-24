package com.tianjian.property.dao;

import com.tianjian.property.bean.DoorType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/6/16
 */
@Mapper
@Repository
public interface DoorTypeDao extends BaseDao<DoorType> {
    @Select({"<script>" +
            "SELECT id,door_type FROM tj_door_type "+
            "</script>"})
    List<Map> selectDoorType();
}
