package com.tianjian.property.dao;

import com.tianjian.property.bean.LockBaseInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LockBaseInfoDao extends BaseDao<LockBaseInfo> {
    @Select({"<script>" +
            " SELECT lock_mac " +
            "FROM tj_lockbaseinfo WHERE id = #{id}"+
            "</script>"})
    String findById(Integer id);
    @Insert({"<script>" +
            " INSERT INTO tj_lockbaseinfo" +
            "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"+
            " <if test='lockId != null'>lock_id ,</if> " +
            " <if test='lockTag != null'>lock_tag ,</if> " +
            " <if test='lockMac != null'>lock_mac ,</if> " +
            " <if test='hardwareVersion != null'>hardware_version ,</if> " +
            " <if test='softwareVersion != null'>software_version ,</if> " +
            " <if test='lockType != null'>lock_type ,</if> " +
            " <if test='initStatus != null'>init_status ,</if> " +
            " <if test='maxVolume != null'>max_volume ,</if> " +
            " <if test='maxUser != null'>max_user ,</if> " +
            " <if test='bleprotocolVer != null'>bleprotocol_ver ,</if> " +
            " <if test='rfModuleType != null'>rfmodule_type ,</if> " +
            " <if test='rfModuleMac != null'>rfmodule_mac ,</if> " +
            " <if test='aesKey != null'>aes_key ,</if> " +
            " <if test='adminAuthCode != null'>admin_auth_code ,</if> " +
            " <if test='generalAuthCode != null'>general_auth_code ,</if> " +
            " <if test='tempAuthCode != null'>temp_auth_code ,</if> " +
            " <if test='createTime != null'>create_time ,</if> " +
            " <if test='updateTime != null'>update_time ,</if> " +
            " <if test='addPeople != null'>add_people ,</if> " +
            " <if test='vendor != null'>vendor ,</if> " +
            " <if test='status != null'>status ,</if> " +
            " <if test='remark != null'>remark </if> " +
            "</trim>"+
            "<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">"+
            " <if test='lockId  != null'> #{lockId}, </if> " +
            " <if test='lockTag  != null'> #{lockTag}, </if>" +
            " <if test='lockMac  != null'> #{lockMac},</if> " +
            " <if test='hardwareVersion   != null'>#{hardwareVersion}, </if> " +
            " <if test='softwareVersion  != null'>#{softwareVersion} ,</if> " +
            " <if test='lockType != null'>#{lockType} ,</if> " +
            " <if test='initStatus  != null'>#{initStatus} ,</if> " +
            " <if test='maxVolume != null'> #{maxVolume} ,</if> " +
            " <if test='maxUser != null'> #{maxUser} ,</if> " +
            " <if test='bleprotocolVer != null'> #{bleprotocolVer} ,</if> " +
            " <if test='rfModuleType != null'> #{rfModuleType} ,</if> " +
            " <if test='rfModuleMac != null'> #{rfModuleMac} ,</if> " +
            " <if test='aesKey != null'> #{aesKey} ,</if> " +
            " <if test='adminAuthCode != null'> #{adminAuthCode} ,</if> " +
            " <if test='generalAuthCode != null'> #{generalAuthCode} ,</if> " +
            " <if test='tempAuthCode != null'> #{tempAuthCode} ,</if> " +
            " <if test='createTime != null'> #{createTime} ,</if> " +
            " <if test='updateTime != null'> #{updateTime} ,</if> " +
            " <if test='addPeople != null'> #{addPeople} ,</if> " +
            " <if test='vendor != null'> #{vendor} ,</if> " +
            " <if test='status != null'> #{status} ,</if> " +
            " <if test='remark != null'> #{remark} ,</if> " +
            "</trim>"+
            "</script>"})
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void inster(LockBaseInfo lockBaseInfo);
    @Select({"<script>"+
            "SELECT" +
            " id bluetoothLockId,lock_id lockId,lock_tag lockTag,lock_mac lockMac, a.`status` ,door_id doorId,property_name propertyName," +
            " num_name numName,building_name buildingName,unit_name unitName,room_no roomNo,door_name doorName, property_id propertyId,b.lId lock_id" +
            " FROM " +
            " tj_lockbaseinfo a " +
            " INNER JOIN ( " +
            " SELECT " +
            " door_id, " +
            " lock_status, " +
            " lock_facility_id, " +
            " Lock_gateway_id, " +
            " property_id, " +
            " property_name, " +
            " num_id, " +
            " num_name, " +
            " building_id, " +
            " building_name, " +
            " unit_no, " +
            " unit_name, " +
            " floor_no, " +
            " room_no, " +
            " door_name, " +
            " door_type, " +
            "  `status`,  " +
            "   l.id lId  " +
            " FROM " +
            "  tj_lock l " +
            "  INNER JOIN tj_door d ON l.door_id = d.id   " +
            " ) b ON a.id = b.lock_facility_id   WHERE " +
            "<if test='keyWord !=null'> lock_tag LIKE #{keyWord} OR lock_mac LIKE #{keyWord} OR lock_id LIKE #{keyWord} AND </if>" +
            "  a.status != 2  AND b.lock_status=0 AND b.`status`!=3 AND property_id = #{propertyId} "+
            " ORDER  BY building_name DESC ,unit_name DESC ,room_no DESC  "+
            "</script>"})
    List<LinkedHashMap<String, Object>> selectByPropertyId(Integer propertyId,String keyWord);
    @Select({"<script>" +
            " SELECT "+
            " a.id id, a.lock_id lockId,a.lock_tag lockTag,a.lock_mac lockMac ,a.hardware_version hardwareVersion,"+
            " a.software_version softwareVersion,a.lock_type lockType,a.init_status initStatus,a.max_volume maxVolume,"+
            " a.max_user maxUser,a.bleprotocol_ver bleprotocolVer,a.rfModule_type rfmodule_type,a.rfmodule_mac rfModuleMac, "+
            " a.aes_key aesKey,a.admin_auth_code adminAuthCode,a.general_auth_code generalAuthCode,a.temp_auth_code tempAuthCode, "+
            " a.create_time createTime,a.update_time updateTime,a.add_people addPeople,a.vendor,a.status,a.remark, "+
            " b.property_id propertyId,b.property_name propertyName,b.num_id numId,b.num_name numName,b.building_id buildingId,"+
            " b.building_name buildingName,b.unit_no unitNo,b.unit_name unitName, b.floor_no floorNo,b.room_no roomNo,b.door_name doorName,"+
            " b.door_id doorId, b.lock_gateway_id lockGatewayId,"+
            " b.lId lId "+
            " FROM "+
            " tj_lockbaseinfo a " +
            " LEFT JOIN ( " +
            " SELECT " +
            " door_id, " +
            " lock_status, " +
            " lock_facility_id, " +
            " Lock_gateway_id, " +
            " property_id, " +
            " property_name, " +
            " num_id, " +
            " num_name, " +
            " building_id, " +
            " building_name, " +
            " unit_no, " +
            " unit_name, " +
            " floor_no, " +
            " room_no, " +
            " door_name, " +
            " door_type, " +
            "  `status`,  " +
            "   l.id lId  " +
            " FROM " +
            "  tj_lock l " +
            "  LEFT JOIN tj_door d ON l.door_id = d.id   " +
            " ) b ON a.id = b.lock_facility_id  " +
            " WHERE "+
            " a.`status`  !=2 AND b.lock_status=0 AND b.`status` != 3 AND "+
            " a.id = #{equipmentId} "+
            "</script>"})
    Map selectById(Integer equipmentId);
    @Update({"UPDATE tj_lockbaseinfo SET `status`= #{status} WHERE id = #{bluetoothLockId}"})
    int updateStatus(Integer bluetoothLockId,Integer status);
    @Select({"SELECT" +
            " id,lock_id,lock_tag,lock_mac, a.`status` ,door_id,property_name,num_name,building_name,unit_name,room_no,door_name , property_id " +
            "FROM " +
            " tj_lockbaseinfo a " +
            " LEFT JOIN ( " +
            " SELECT " +
            " door_id, " +
            " lock_status, " +
            " lock_facility_id, " +
            " Lock_gateway_id, " +
            " property_id, " +
            " property_name, " +
            " num_id, " +
            " num_name, " +
            " building_id, " +
            " building_name, " +
            " unit_no, " +
            " unit_name, " +
            " floor_no, " +
            " room_no, " +
            " door_name, " +
            " door_type, " +
            "  `status`  " +
            " FROM " +
            "  tj_lock l " +
            "  LEFT JOIN tj_door d ON l.door_id = d.id   " +
            " ) b ON a.id = b.lock_facility_id  " +
            "WHERE lock_tag LIKE #{keyWord} OR lock_mac LIKE #{keyWord} OR lock_id LIKE #{keyWord} AND a.`status` != 2 AND b.lock_status=0 AND b.`status` != 3  AND property_id = #{propertyId}   " +
            "ORDER  BY building_name DESC ,unit_name DESC ,room_no DESC  "})
    List<LinkedHashMap<String, Object>> fuzzySearch(Integer propertyId,String keyWord);
    @Select({"<script>"+
            "SELECT" +
            " id bluetoothLockId,lock_id lockId,lock_tag lockTag,lock_mac lockMac, a.`status` ,door_id doorId,property_name propertyName," +
            " num_name numName,building_name buildingName,unit_name unitName,floor_no floorNo,room_no roomNo,door_name doorName, property_id propertyId,b.lId lock_id" +
            " FROM " +
            " tj_lockbaseinfo a " +
            " INNER JOIN ( " +
            " SELECT " +
            " door_id, " +
            " lock_status, " +
            " lock_facility_id, " +
            " Lock_gateway_id, " +
            " property_id, " +
            " property_name, " +
            " num_id, " +
            " num_name, " +
            " building_id, " +
            " building_name, " +
            " unit_no, " +
            " unit_name, " +
            " floor_no, " +
            " room_no, " +
            " door_name, " +
            " door_type, " +
            "  `status`,  " +
            "   l.id lId  " +
            " FROM " +
            "  tj_lock l " +
            "  INNER JOIN tj_door d ON l.door_id = d.id   " +
            " ) b ON a.id = b.lock_facility_id   WHERE " +
            " a.`status`  !=2 AND b.lock_status=0 AND b.`status` != 3  "+
            "<if test='lockBaseInfo != null'> " +
            "<if test='lockBaseInfo.lockMac != null'> AND a.lock_mac like CONCAT('%',#{lockBaseInfo.lockMac},'%')</if>" +
            "<if test='lockBaseInfo.status != null'> AND a.status = #{lockBaseInfo.status}  </if>" +
            "</if>" +
            "<if test='doorName != null'> AND b.door_name = CONCAT('%',#{lockBaseInfo.doorName},'%')</if>" +
            " AND b.property_id IN"+
            "<foreach collection=\"lists\" item=\"list\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            "#{list} " +
            "</foreach>" +
            " ORDER  BY b.building_name DESC ,b.unit_name DESC ,b.room_no DESC  "+
            "</script>"})
    List<LinkedHashMap<String, Object>> selectBluetooth( List<Integer> lists, LockBaseInfo lockBaseInfo,String doorName);
    @Select({"<script>" +
            " SELECT * " +
            " FROM tj_lockbaseinfo WHERE status!=2 AND lock_mac = #{lockMac}"+
            "</script>"})
    LockBaseInfo selectByMac(String lockMac);
}
