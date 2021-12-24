package com.tianjian.property.dao;

import com.tianjian.property.bean.DoorType;
import com.tianjian.property.bean.Fingerprint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface FingerprintDao extends  BaseDao<Fingerprint> {
    @Select({"<script>" +
            " SELECT " +
            " f.id id , " +
            " f.door_id doorId, " +
            " f.lockKey_id lockKeyId, " +
            " f.add_person addPerso, " +
            " f.validend_time validEndTime, " +
            " f.validstart_time validStartTime, " +
            " f.add_time addTime, " +
            " f.added_key_groupId addedKeyGroupId, " +
            " f.`status` status, " +
            " f.remark remark, " +
            " u.`name`, " +
            " u.phone " +
            "FROM " +
            " tj_fingerprint f INNER JOIN tj_user u " +
            "   ON f.add_person = u.user_id " +
            "WHERE " +
            " ( f.validend_time <![CDATA[ >= ]]> unix_timestamp( now()) OR f.validend_time = 0 )  " +
            "<if test='doorId != null'> AND f.door_id = #{doorId} </if> " +
            "<if test='addPerson != null'> AND f.add_person = #{addPerson}</if>" +
            " AND f.`status` = 0" +
            "</script>"})
    List<Map> selectByuser(Integer doorId, Integer addPerson);
    @Update({"<script>" +
            "UPDATE tj_fingerprint " +
            "SET status = 1" +
            " WHERE door_id = #{doorId}  AND lockKey_id =#{lockKeyId}" +
            "</script>"})
    int updateStatus(Integer doorId,Integer lockKeyId);
}