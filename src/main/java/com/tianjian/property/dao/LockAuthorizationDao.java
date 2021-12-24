package com.tianjian.property.dao;

import com.tianjian.property.bean.LockAuthorization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LockAuthorizationDao extends BaseDao<LockAuthorization> {
    @Update({"<script>" +
            "UPDATE tj_lock_authorization  SET user_status =1  WHERE id=#{aId}"+
            "</script>"})
    int updateStatus(Integer aId);
    @Select({"<script>" +
            " SELECT *" +
            " FROM tj_lock_authorization WHERE user_status = 0  AND door_id = #{doorId}" +
            "</script>"})
    List<LockAuthorization> selectByDoorId(Integer doorId);
    @Select({"<script>" +
            "SELECT " +
            " a.id aId, " +
            " a.enter_time enterTime, " +
            " a.leava_time leavaTime, " +
            " u.id uId, " +
            " u.user_id userId, " +
            " u.phone, " +
            " u.`name`  " +
            "FROM " +
            " `tj_lock_authorization` a " +
            " INNER JOIN tj_user u ON a.user_id = u.user_id  " +
            "WHERE " +
            " user_status = 0 AND a.door_id = #{doorId}" +
            "</script>"})
    List<Map> selectDoorToUser(Integer doorId);
    @Select({"<script>" +
            "SELECT *  " +
            "FROM " +
            " `tj_lock_authorization` a " +
            "WHERE user_status = 0 AND  door_id = #{doorid} " +
            "</script>"})
    LockAuthorization selectByDoorid(Integer doorid);
}