package com.tianjian.property.dao;

import com.tianjian.property.bean.LockLog;
import com.tianjian.property.bean.vo.LockLogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LockLogDao extends BaseDao<LockLog> {
    @Select({"<script>" +
            " SELECT  l.id id, " +
            " l.door_id doorId, " +
            " l.lock_type lockType, " +
            " l.lock_mac lockMac, " +
            " l.record_time recordTime, " +
            " l.property_id propertyId, " +
            " l.user_id userId, " +
            " l.add_time addTime, " +
            " l.`status` status, " +
            " l.remark remark, " +
            " u.`name`  name" +
            " FROM tj_lock_log  l "+
            " INNER JOIN tj_user u "+
            " ON l.user_id=u.user_id "+
            " WHERE  1=1 "+
            "<if test='lockLog != null'> " +
            "<if test='lockLog.id != null'> AND id = #{lockLog.id}</if>" +
            "<if test='lockLog.doorId != null'> AND door_id = #{lockLog.doorId}</if>" +
            "<if test='lockLog.lockType != null'> AND lock_type = #{lockLog.lockType}</if>" +
            "<if test='lockLog.lockMac != null'> AND lock_mac  like CONCAT('%',#{lockLog.lockMac},'%')</if>" +
            "<if test='lockLog.recordTime != null'> AND record_time <![CDATA[ >= ]]> #{lockLog.recordTime}</if>" +
            "<if test='lockLog.propertyId != null'> AND property_id  = #{lockLog.propertyId}</if>" +
            "<if test='lockLog.userId != null'> AND user_id  = #{lockLog.userId}</if>" +
            "<if test='lockLog.addTime != null'> AND add_time  <![CDATA[ >= ]]> #{lockLog.addTime}</if>" +
            "<if test='lockLog.status != null'> AND status  = #{lockLog.status}</if>" +
            "</if>" +
            "ORDER BY record_time DESC"+
            "</script>"})
    List<LockLogVo> openLockLog(@Param("lockLog") LockLog lockLog);
}