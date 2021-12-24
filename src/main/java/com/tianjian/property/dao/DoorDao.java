package com.tianjian.property.dao;

import com.tianjian.property.bean.Door;
import com.tianjian.property.bean.User;
import com.tianjian.property.bean.vo.DoorVo;
import com.tianjian.property.bean.vo.RoomVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DoorDao extends BaseDao<Door> {
    @Select({"<script>" +
            " SELECT id, property_id propertyId,num_id,num_name,building_id,building_name,floor_no ,room_no,door_name,status ,unit_no ,unit_name " +
            "FROM tj_door WHERE 1=1 " +
            "<if test='door != null'> " +
            "<if test='door.id != null'> AND id = #{door.id}</if>" +
            "<if test='door.propertyId != null'> AND property_id = #{door.propertyId}</if>" +
            "<if test='door.numId != null'> AND num_id = #{door.numId}</if>" +
            "<if test='door.buildingId != null'> AND building_id = #{door.buildingId}</if>" +
            "<if test='door.unitNo != null'> AND unit_no = #{door.unitNo}</if>" +
            "<if test='door.floorNo != null'> AND floor_no = #{door.floorNo}</if>" +
            "<if test='door.roomNo != null'> AND room_no = #{door.roomNo}</if>" +
            "<if test='door.doorType != null'> AND door_type =  #{door.doorType}</if>" +
            "<if test='door.status != null'> AND status = #{door.status}</if>" +
            "<if test='door.addTime != null'> AND add_time = #{door.addTime}</if>" +
            "<if test='door.updateTime != null'> AND update_time = #{door.updateTime}</if>" +
            "<if test='door.createPerson != null'> AND create_person = #{door.createPerson}</if>" +
            "<if test='door.remark != null'> AND remark = #{door.remark}</if>" +
            "<if test='door.propertyName != null'> AND property_name  like CONCAT('%',#{door.propertyName},'%')</if>" +
            "<if test='door.numName != null'> AND num_name like CONCAT('%',#{door.numName},'%') </if>" +
            "<if test='door.buildingName != null'> AND building_name like CONCAT('%',#{door.buildingName},'%') </if>" +
            "<if test='door.unitName != null'> AND unit_name like CONCAT('%',#{door.unitName},'%') </if>" +
            "<if test='door.doorName != null'> AND door_name like CONCAT('%',#{door.doorName},'%')</if>" +
            "</if>" +
            "ORDER  BY property_id ASC ,building_id ASC ,num_id ASC,unit_no ASC ,floor_no ASC , room_no ASC"+
            "</script>"})
    List<DoorVo> selectall(@Param("door") Door door);
    @Select({"<script>" +
            " SELECT id ,num_id,num_name,building_id,building_name,floor_no ,room_no,door_name,status ,unit_no ,unit_name " +
            "FROM tj_door WHERE 1=1 " +
            "<if test='propertyId !=null'>AND property_id = #{propertyId} </if> " +
            "<if test='roomno !=null'>AND room_no = #{roomno} </if> " +
            "</script>"})
    List<DoorVo> RoomnoAndPropertyname(Integer propertyId,String roomno);
    @Select({"<script>" +
            " SELECT id ,num_id,num_name,building_id,building_name,floor_no ,room_no,door_name,status ,unit_no ,unit_name " +
            "FROM tj_door WHERE 1=1" +
            "<if test='propertyId !=null'> AND property_id = #{propertyId} </if>" +
            "<if test='buildingId !=null'> AND building_id = #{buildingId} </if>" +
            "<if test='unitName !=null'>AND unit_name = #{unitName} </if>" +
            "</script>"})
    List<DoorVo> screenRoomDoor(Integer propertyId, Integer buildingId, String unitName);
    //添加公共门
   @Insert({"<script>" +
           " INSERT INTO tj_door" +
           "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"+
           " <if test='propertyId != null'>property_id ,</if> " +
           " <if test='propertyName != null'>property_name ,</if> " +
           " <if test='numId != null'>num_id ,</if> " +
           " <if test='numName != null'>num_name ,</if> " +
           " <if test='buildingId != null'>building_id ,</if> " +
           " <if test='buildingName != null'>building_name ,</if> " +
           " <if test='unitNo != null'>unit_no ,</if> " +
           " <if test='unitName != null'>unit_name ,</if> " +
           " <if test='floorNo != null'> floor_no ,</if> " +
           " <if test='roomNo != null'> room_no ,</if> " +
           " <if test='doorType != null'>door_type ,</if> " +
           " <if test='doorName != null'>door_name ,</if> " +
           " <if test='addTime != null'>add_time ,</if> " +
           " <if test='status != null'>status ,</if> " +
           " <if test='createPerson != null'>create_person ,</if> " +
           " <if test='remark != null'>remark </if> " +
           "</trim>"+
           "<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">"+
           " <if test='propertyId  != null'>#{propertyId} ,</if> " +
           " <if test='propertyName != null'>#{propertyName} ,</if> " +
           " <if test='numId  != null'>#{numId} ,</if> " +
           " <if test='numName != null'> #{numName} ,</if> " +
           " <if test='buildingId != null'> #{buildingId} ,</if> " +
           " <if test='buildingName != null'> #{buildingName} ,</if> " +
           " <if test='unitNo != null'> #{unitNo} ,</if> " +
           " <if test='unitName != null'> #{unitName} ,</if> " +
           " <if test='floorNo != null'> #{floorNo} ,</if> " +
           " <if test='roomNo != null'> #{roomNo} ,</if> " +
           " <if test='doorType != null'> #{doorType} ,</if> " +
           " <if test='doorName != null'> #{doorName} ,</if> " +
           " <if test='addTime != null'> #{addTime} ,</if> " +
           " <if test='status != null'> #{status} ,</if> " +
           " <if test='createPerson != null'> #{createPerson} ,</if> " +
           " <if test='remark != null'> #{remark} </if> " +
           "</trim>"+
           "</script>"})
   int insert(Door door);
    @Select({"<script>" +
            " SELECT * " +
            "FROM tj_door WHERE 1=1" +
            "<if test='propertyId !=null'>AND property_id = #{propertyId} </if>" +
            "<if test='doorType !=null'>AND door_type = #{doorType} </if>" +
            "</script>"})
    List<Door> selectCommonDoor(Integer propertyId, Integer doorType);
    //根据楼栋分组查出该楼栋下有哪些楼栋
    @Select({"<script>" +
            " SELECT building_name " +
            "FROM tj_door WHERE 1=1" +
            "<if test='propertyId !=null'> AND property_id = #{propertyId} </if>" +
            "<if test='doorType !=null'>AND door_type = #{doorType} </if>" +
            "<if test='roomNo !=null'>AND room_no = #{roomNo} </if>" +
            " GROUP   BY building_name"+
            "</script>"})
    List<String> selectbuildingid( Integer propertyId, Integer doorType,String roomNo);
    //根据楼栋分组查出该楼栋下有哪些单元
    @Select({"<script>" +
            " SELECT unit_name " +
            "FROM tj_door WHERE 1=1 " +
            "<if test='propertyId !=null'> AND property_id = #{propertyId} </if>" +
            "<if test='buildingName !=null'>AND building_name = #{buildingName} </if>" +
            "<if test='roomNo !=null'>AND room_no = #{roomNo} </if>" +
            "AND 1 = 1 GROUP   BY unit_name ORDER BY unit_name"+
            "</script>"})
    List<String> selectunitname(Integer propertyid,String buildingName,String roomNo);
    @Select({"<script>" +
            " SELECT * " +
            "FROM tj_door WHERE " +
            "property_name LIKE #{doorName} OR " +
            "building_name LIKE #{doorName} OR " +
            "num_name LIKE #{doorName} OR " +
            "unit_name LIKE #{doorName} OR " +
            "door_name LIKE #{doorName}  " +
            "AND door_type = #{doorType} AND property_id = #{propertyId}" +
            " ORDER BY door_name desc,unit_name desc,building_name desc "+
            "</script>"})
    List<Door> fuzzyQueryCommonDoor(String doorName, Integer doorType,Integer propertyId);
    @Select({"<script>" +
            " SELECT * " +
            "FROM tj_door WHERE " +
            "property_name LIKE #{fuzzy} OR " +
            "building_name LIKE #{fuzzy} OR " +
            "unit_name LIKE #{fuzzy} OR " +
            "room_no LIKE #{fuzzy} OR " +
            "door_name LIKE #{fuzzy}  " +
            "AND door_type = 3 AND property_id = #{propertyId}  ORDER BY room_no desc,unit_name desc,building_name desc ,floor_no desc"+
            "</script>"})
    List<Door> fuzzySearch(Integer  propertyId,String fuzzy);
   @Insert({"<script>" +
           " INSERT INTO tj_door" +
           " (property_id ,property_name,num_id,num_name,building_id,building_name,unit_no,unit_name,floor_no,room_no,door_name," +
           " door_type,status,create_person) values "+
           " <foreach collection=\"list\" item=\"door\" index=\"index\"  separator=\",\"> " +
           "(" +
           "#{door.propertyId}," +
           "#{door.propertyName}," +
           "#{door.numId}," +
           "#{door.numName}," +
           "#{door.buildingId}," +
           "#{door.buildingName}," +
           "#{door.unitNo}," +
           "#{door.unitName}," +
           "#{door.floorNo}," +
           "#{door.roomNo}," +
           "#{door.doorName}," +
           "#{door.doorType}," +
           "#{door.status}," +
           "#{door.createPerson}" +
           ")" +
           "</foreach>"+
           "</script>"})
    void addDoor(@Param("list") List<Door> list);
    @Select({"SELECT * FROM  tj_door  WHERE id = #{doorID}"})
    Door selectById(Integer doorID);
    @Update({"<script>" +
            "UPDATE tj_door SET status =#{status}  WHERE id=#{doorID}"+
            "</script>"})
    int updateDoorStatus(Integer doorID,Integer status);
    @Select({"<script>" +
            "SELECT  status FROM tj_door WHERE id=#{doorID}"+
            "</script>"})
    Integer selectSutats(Integer doorID);
    @Select({"<script>" +
            "SELECT id  doorId ,property_name propertyName,num_name numName,building_name buildingName,unit_name unitName, floor_no floorNo," +
            " room_no roomNo,door_name  doorName, status doorStatus"+
            " FROM tj_door WHERE id=#{doorID}"+
            "</script>"})
    Map<String,Object> selectDoor(Integer doorid);
    @Select({"<script>" +
            " SELECT d.id, d.property_id ,d.property_name ,d.num_id,d.num_name,d.building_id,d.building_name,d.floor_no ,d.room_no,d.door_name,d.status ,d.unit_no ,d.unit_name, d.door_type ,l.facility_type" +
            " FROM tj_door d" +
            " LEFT JOIN  (select * FROM  tj_lock WHERE lock_status=0) l ON  d.id=l.door_id"+
            " WHERE 1=1" +
            "<if test='door != null'> " +
            "<if test='door.id != null'> AND d.id = #{door.id}</if>" +
            "<if test='door.numId != null'> AND d.num_id = #{door.numId}</if>" +
            "<if test='door.buildingId != null'> AND d.building_id = #{door.buildingId}</if>" +
            "<if test='door.unitNo != null'> AND d.unit_no = #{door.unitNo}</if>" +
            "<if test='door.floorNo != null'> AND d.floor_no = #{door.floorNo}</if>" +
            "<if test='door.roomNo != null'> AND d.room_no = #{door.roomNo}</if>" +
            "<if test='door.doorType != null'> AND d.door_type =  #{door.doorType}</if>" +
            "<if test='door.status != null'> AND d.status = #{door.status}</if>" +
            "<if test='door.addTime != null'> AND d.add_time = #{door.addTime}</if>" +
            "<if test='door.updateTime != null'> AND d.update_time = #{door.updateTime}</if>" +
            "<if test='door.createPerson != null'> AND d.create_person = #{door.createPerson}</if>" +
            "<if test='door.remark != null'> AND d.remark = #{door.remark}</if>" +
            "<if test='door.propertyName != null'> AND d.property_name  like CONCAT('%',#{door.propertyName},'%')</if>" +
            "<if test='door.numName != null'> AND d.num_name like CONCAT('%',#{door.numName},'%') </if>" +
            "<if test='door.buildingName != null'> d.AND building_name like CONCAT('%',#{door.buildingName},'%') </if>" +
            "<if test='door.unitName != null'> AND d.unit_name like CONCAT('%',#{door.unitName},'%') </if>" +
            "<if test='door.doorName != null'> AND d.door_name like CONCAT('%',#{door.doorName},'%')</if>" +
            "</if>" +
            " AND d.property_id IN"+
            "<foreach collection=\"lists\" item=\"list\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            "#{list} " +
            "</foreach>" +
            "ORDER  BY property_id ASC ,building_id ASC ,num_id ASC,unit_no ASC ,floor_no ASC , room_no ASC"+
            "</script>"})
    List<DoorVo> selectPageDoor(Door door, List<Integer> lists);
    @Select({"<script>" +
            "SELECT * " +
            " FROM tj_door WHERE status!=3 "+
            "<if test='door != null'> " +
            "<if test='door.id != null'> AND id = #{door.id}</if>" +
            "<if test='door.propertyId == null'> AND property_id IN " +
            "<foreach collection=\"lists\" item=\"list\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            "#{list} " +
            "</foreach>" +
            "</if>" +
            "<if test='door.propertyId != null'> AND property_id = #{door.propertyId}</if>" +
            "<if test='door.numId != null'> AND num_id = #{door.numId}</if>" +
            "<if test='door.buildingId != null'> AND building_id = #{door.buildingId}</if>" +
            "<if test='door.unitNo != null'> AND unit_no = #{door.unitNo}</if>" +
            "<if test='door.floorNo != null'> AND floor_no = #{door.floorNo}</if>" +
            "<if test='door.roomNo != null'> AND room_no = #{door.roomNo}</if>" +
            "<if test='door.status != null'> AND status = #{door.status}</if>" +
            "<if test='door.addTime != null'> AND add_time = #{door.addTime}</if>" +
            "<if test='door.updateTime != null'> AND update_time = #{door.updateTime}</if>" +
            "<if test='door.createPerson != null'> AND create_person = #{door.createPerson}</if>" +
            "<if test='door.remark != null'> AND remark = #{door.remark}</if>" +
            "<if test='door.propertyName != null'> AND property_name  like CONCAT('%',#{door.propertyName},'%')</if>" +
            "<if test='door.numName != null'> AND num_name like CONCAT('%',#{door.numName},'%') </if>" +
            "<if test='door.buildingName != null'> AND building_name like CONCAT('%',#{door.buildingName},'%') </if>" +
            "<if test='door.unitName != null'> AND unit_name like CONCAT('%',#{door.unitName},'%') </if>" +
            "<if test='door.doorName != null'> AND door_name like CONCAT('%',#{door.doorName},'%')</if>" +
            "</if>" +
            "</script>"})
    List<Door> selectDoorByProperty(@Param("lists") List<Integer> lists,@Param("door")Door door);
    @Select({"<script>" +
            " SELECT id, property_id,property_name,num_id,num_name,building_id,building_name,floor_no ,room_no,door_name,status ,unit_no ,unit_name ,door_type " +
            "FROM tj_door WHERE 1=1 " +
            "<if test='door != null'> " +
            "<if test='door.id != null'> AND id = #{door.id}</if>" +
            "<if test='door.numId != null'> AND num_id = #{door.numId}</if>" +
            "<if test='door.buildingId != null'> AND building_id = #{door.buildingId}</if>" +
            "<if test='door.unitNo != null'> AND unit_no = #{door.unitNo}</if>" +
            "<if test='door.floorNo != null'> AND floor_no = #{door.floorNo}</if>" +
            "<if test='door.roomNo != null'> AND room_no = #{door.roomNo}</if>" +
            "<if test='door.status != null'> AND status = #{door.status}</if>" +
            "<if test='door.addTime != null'> AND add_time = #{door.addTime}</if>" +
            "<if test='door.updateTime != null'> AND update_time = #{door.updateTime}</if>" +
            "<if test='door.createPerson != null'> AND create_person = #{door.createPerson}</if>" +
            "<if test='door.remark != null'> AND remark = #{door.remark}</if>" +
            "<if test='door.propertyName != null'> AND property_name  like CONCAT('%',#{door.propertyName},'%')</if>" +
            "<if test='door.numName != null'> AND num_name like CONCAT('%',#{door.numName},'%') </if>" +
            "<if test='door.buildingName != null'> AND building_name like CONCAT('%',#{door.buildingName},'%') </if>" +
            "<if test='door.unitName != null'> AND unit_name like CONCAT('%',#{door.unitName},'%') </if>" +
            "<if test='door.doorName != null'> AND door_name like CONCAT('%',#{door.doorName},'%')</if>" +
            "</if>" +
            " AND door_type IN " +
            "<foreach collection=\"types\" item=\"type\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            "#{type} " +
            "</foreach>" +
            " AND property_id IN"+
            "<foreach collection=\"lists\" item=\"list\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            "#{list} " +
            "</foreach>" +
            "ORDER  BY property_id ASC ,building_id ASC ,num_id ASC,unit_no ASC ,floor_no ASC , room_no ASC"+
            "</script>"})
    List<DoorVo> selectPublicDoor(@Param("door")Door door,@Param("types")List<Integer> types,@Param("lists") List<Integer> lists);
    @Select({"<script>" +
            " SELECT " +
            "  d.id , " +
            "  d.property_id propertyId, " +
            "  d.door_type doorType, " +
            "  d.status status, "+
            "  b.lock_mac lockMac, " +
            "  b.lock_id lockId " +
            " FROM tj_door d " +
            " INNER JOIN tj_lock l " +
            " ON d.id=l.door_id " +
            " INNER JOIN tj_lockbaseinfo b " +
            " ON l.lock_facility_id=b.id " +
            " WHERE d.`status` !=3 " +
            " AND l.lock_status=0  " +
            " AND b.`status` =0 " +
            " AND d.id=#{doorId}" +
            "</script>"})
    List<Map> selectlock(Integer doorId);
    @Select({"<script>" +
            " SELECT room_no roomNo " +
            " FROM tj_door  " +
            " WHERE  room_no IN "+
            "<foreach collection=\"lists\" item=\"list\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            "#{list} " +
            "</foreach>" +
            "</script>"})
    List<Integer> selectRoom(@Param("lists")List<Integer> lists);
}
