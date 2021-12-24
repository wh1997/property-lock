package com.tianjian.property.dao;

import com.tianjian.property.bean.LockUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
@Repository
public interface LockUserDao extends BaseDao<LockUser> {
    @Select({"<script>" +
            " SELECT " +
            " l.id id," +
            " l.user_id userId, " +
            " l.lock_user_id lockUserId,  " +
            " l.add_person addPerson,  " +
            " d.id dId,  " +
            " d.property_id propertyId,  " +
            " d.property_name propertyName,  " +
            " d.num_name numName,  " +
            " d.building_name buildingName,  " +
            " d.unit_name unitName,  " +
            " d.floor_no floorNo,  " +
            " d.room_no roomNo,  " +
            " d.door_name doorName,  " +
            " d.door_type doorType,  " +
            " d.status dStatus, "+
            " u.`name` name "+
            " FROM tj_lock_user  l " +
            " INNER JOIN tj_door d " +
            " ON l.door_id=d.id " +
            " INNER JOIN tj_user u " +
            " ON u.user_id= l.user_id  " +
            " WHERE l.`status`=0 AND d.status!=3 " +
            "<if test='lockUser != null'> " +
            "<if test='lockUser.userId != null'> AND l.user_id = #{lockUser.userId}</if>" +
            "<if test='lockUser.doorId != null'> AND l.door_id = #{lockUser.doorId}</if>" +
            "</if>" +
            " ORDER BY " +
            " l.lock_user_id "+
            "</script>"})
    List<Map> lockUserDao(@Param("lockUser") LockUser lockUser);
    @Select({"<script>" +
            " SELECT *" +
            " FROM tj_lock_user WHERE 1=1 AND status = 0  " +
            "<if test='lockUser != null'> " +
            "<if test='lockUser.userId != null'> AND user_id = #{lockUser.userId}</if>" +
            "<if test='lockUser.doorId != null'> AND door_id = #{lockUser.doorId}</if>" +
            "</if>" +
            "</script>"})
    List<LockUser> selectRepetition(@Param("lockUser")LockUser lockUser);
    @Update({"<script>" +
            "UPDATE `tj_lock_user` SET status = 1 ,add_person = #{lockUser.addPerson}" +
            " WHERE user_id= #{lockUser.userId} AND door_id = #{lockUser.doorId}"+
            "</script>"})
    int deleteLockuser(@Param("lockUser")LockUser lockUser);
}