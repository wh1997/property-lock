package com.tianjian.property.dao;

import com.tianjian.property.bean.Lock;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LockDao extends BaseDao<Lock> {
    @Select({"<script>" +
            " SELECT * " +
            "FROM tj_lock WHERE door_id = #{doorid} AND lock_status=0 "+
            "</script>"})
    Lock selectByDoorid(Integer doorid);
    @Select({"<script>" +
            " SELECT * " +
            "FROM tj_lock WHERE lock_facility_id = #{lockFacilityId} AND lock_status=0 "+
            "</script>"})
    Lock selectByLockFacilityId(Integer lockFacilityId);
    @Insert({"<script>" +
            " INSERT INTO tj_lock" +
            "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"+
            " <if test='doorId != null'>door_id ,</if> " +
            " <if test='lockStatus != null'>lock_status,</if> " +
            " <if test='lockFacilityId != null'>lock_facility_id ,</if> " +
            " <if test='lockGatewayId != null'>Lock_gateway_id ,</if> " +
            " <if test='facilityType != null'>facility_type ,</if> " +
            " <if test='addTime != null'>addtime ,</if> " +
            " <if test='updateTime != null'>updatetime ,</if> " +
            " <if test='remark != null'>remark </if> " +
            "</trim>"+
            "<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">"+
            " <if test='doorId  != null'> #{doorId}, </if> " +
            " <if test='lockStatus  != null'> #{lockStatus}, </if>" +
            " <if test='lockFacilityId  != null'> #{lockFacilityId},</if> " +
            " <if test='lockGatewayId   != null'>#{lockGatewayId}, </if> " +
            " <if test='facilityType  != null'>#{facilityType} ,</if> " +
            " <if test='addTime != null'>#{addTime} ,</if> " +
            " <if test='updateTime  != null'>#{updateTime} ,</if> " +
            " <if test='remark != null'> #{remark} ,</if> " +
            "</trim>"+
            "</script>"})
    void inster(Lock lock);
    @Update({"UPDATE tj_lock SET `Lock_gateway_id`=-1  WHERE id = #{lock}"})
    void updateGatewayId(Integer lock);
    @Select({"SELECT * FROM  tj_lock  WHERE Lock_gateway_id = #{id}"})
    List<Lock> selectByGatewayId(Integer id);
    @Update({"<script>" +
            "UPDATE tj_lock " +
            "SET Lock_gateway_id = #{gateway}" +
            " WHERE door_id= #{doorID} " +
            " AND lock_facility_id= #{lock} " +
            " AND lock_status = 0"+
            "</script>"})
    int updateByLockToGateway(Integer doorID, Integer lock, Integer gateway);
    @Select({"<script>" +
            " select l.door_id doorId,g.gateway_id gatewayId   FROM tj_lock l " +
            " INNER JOIN tj_gateway g ON " +
            " l.lock_gateway_id=g.id " +
            " WHERE l.lock_status=0 AND g.status!=5 AND l.lock_facility_id= #{lockId}" +
            "</script>"})
    Map lockSelectGateway(Integer lockId);
    @Update({"UPDATE tj_lock SET `lock_status`=1  WHERE id = #{lock}"})
    int updateStatus(Integer lock);
    @Update({"UPDATE tj_lock SET `lock_gateway_id`=-1  WHERE lock_gateway_id = #{id}"})
    int updateByGatewayId(Integer id);
    @Select({"<script>" +
            " select * FROM tj_lock l " +
            " WHERE lock_status=0 AND door_id= #{doorid} AND lock_facility_id= #{id}" +
            "</script>"})
    Lock selectLock(Integer doorid, Integer id);
    @Select({"<script>" +
            " SELECT b.lock_id lockId " +
            " FROM `tj_lock` l " +
            " INNER JOIN tj_lockbaseinfo b " +
            " ON l.lock_facility_id=b.id " +
            " WHERE l.lock_status = 0 AND b.`status` = 0 " +
            " AND l.door_id = #{doorId} " +
            "</script>"})
    String selsetlockId(Integer doorId);
}
