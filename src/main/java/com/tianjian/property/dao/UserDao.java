package com.tianjian.property.dao;

import com.tianjian.property.bean.User;
import com.tianjian.property.bean.vo.AuthorizationVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserDao extends BaseDao<User> {
    @Select({"<script>" +
            " SELECT role  " +
            "FROM tj_user WHERE user_id = #{userId} AND 1 = 1"+
            "</script>"})
    Map selectByUserId(Integer userId);
    @Select({"<script>" +
            " SELECT u.id ,u.user_id,u.role,r.id rId, r.parent_id,r.name,r.sort,r.status" +
            " FROM `tj_user` u LEFT JOIN tj_user_role t ON u.user_id= t.user_id " +
            "LEFT JOIN tj_role r ON t.role_id=r.id  WHERE u.user_id= #{appUID}" +
            "</script>"})
    List<Map<String,Object>> selectUserByRole(Integer  appUID);
    @Insert({"<script>" +
            " INSERT INTO tj_user" +
            "<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">"+
            " <if test='userId != null'>user_id ,</if> " +
            " <if test='role != null'>role ,</if> " +
            " <if test='phone != null'>phone ,</if> " +
            " <if test='name != null'>name ,</if> " +
            " <if test='branchId != null'>branch_id ,</if> " +
            " <if test='addTime != null'>add_time ,</if> " +
            " <if test='updateTime != null'>update_time ,</if> " +
            " <if test='remark != null'>remark </if> " +
            "</trim>"+
            "<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">"+
            " <if test='userId  != null'> #{userId}, </if> " +
            " <if test='role  != null'> #{role}, </if>" +
            " <if test='phone != null'> #{phone},</if> " +
            " <if test='name != null'> #{name},</if> " +
            " <if test='branchId != null'> #{branchId},</if> " +
            " <if test='addTime   != null'>#{addTime}, </if> " +
            " <if test='updateTime  != null'>#{updateTime} ,</if> " +
            " <if test='remark != null'>#{remark} </if> " +
            "</trim>"+
            "</script>"})
    int insert(User role);
    @Select({"<script>" +
            "SELECT r.role_id  FROM  `tj_user` u " +
            " INNER JOIN  tj_user_role r ON u.user_id = r.user_id " +
            " WHERE r.status=0 AND u.user_id = #{userId}" +
            "</script>"})
    List<Integer> selectRoleByUserId(Integer userId);
    @Select({"<script>" +
            "SELECT  " +
            " a.id aId,  " +
            " a.user_status userStatus,  " +
            " a.add_person addPerson,  " +
            " a.enter_time enterTime,  " +
            " a.leava_time leavaTime,  " +
            " a.opendoor_status opendoorStatus,  " +
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
            " d.status dStatus "+
            "FROM `tj_user` u  " +
            " INNER JOIN tj_lock_authorization a  " +
            " ON u.user_id = a.user_id  " +
            " INNER JOIN tj_door d " +
            " ON d.id=a.door_id   " +
            " WHERE " +
            "  d.`status`!=3 AND a.user_status = 0  AND u.user_id = #{userId} "+
            "</script>"})
    List<AuthorizationVo> selectRight(@Param("userId")Integer userId);
    @Select({"<script>" +
            "SELECT  " +
            " a.id aId,  " +
            " a.user_status userStatus,  " +
            " a.add_person addPerson,  " +
            " a.enter_time enterTime,  " +
            " a.leava_time leavaTime,  " +
            " a.opendoor_status opendoorStatus,  " +
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
            " d.status dStatus "+
            "FROM `tj_user` u  " +
            " INNER JOIN tj_lock_authorization a  " +
            " ON u.user_id = a.user_id  " +
            " INNER JOIN tj_door d " +
            " ON d.id=a.door_id   " +
            " WHERE " +
            " ( a.leava_time <![CDATA[ <= ]]>  CURDATE() OR a.leava_time IS null )  "+
            " AND  d.`status`!=3 AND a.user_status = 0  AND u.user_id = #{userId} "+
            "</script>"})
    List<Map<String,Object>> ordinarySelectRight(@Param("userId")Integer userId);
    @Select({"<script>" +
            "SELECT " +
            "  u.id uId, " +
            "  u.user_id userId, " +
            "  u.phone phone, " +
            "  u.name name, " +
            "  u.role role,  " +
            "u.branch_id branchId ," +
            "p.property_name propertyName"+
            " FROM " +
            "  `tj_user` u " +
            "  LEFT JOIN tj_property p ON u.branch_id = p.branch_id  " +
            " WHERE u.role=1 "+
            "<if test='user != null'> " +
            "<if test='user.id != null'> AND u.id = #{user.id}</if>" +
            "<if test='user.userId != null'> AND u.user_id = #{user.userId}</if>" +
            "<if test='user.phone != null'> AND u.phone like CONCAT('%',#{user.phone},'%') </if>" +
            "<if test='user.name != null'> AND u.name  like CONCAT('%',#{user.name},'%')</if>" +
            "</if>" +
            "</script>"})
    List<Map> selectStaff( @Param("user") User user);
    @Update({"<script>" +
            "UPDATE `tj_user` SET role = 2 " +
            " WHERE user_id= #{userId} "+
            "</script>"})
    int deleteByUserId(Integer userId);
    @Select({"<script>" +
            "SELECT " +
            "  u.id uId, " +
            "  u.user_id userId, " +
            "  u.phone phone, " +
            "  u.name name, " +
            "  u.role role , " +
            " u.branch_id branchId ," +
            " p.property_name propertyName "+
            " FROM " +
            "  `tj_user` u " +
            "  INNER JOIN tj_property p ON u.branch_id = p.branch_id  " +
            " WHERE " +
            "  p.bw_property_id IN " +
            "<foreach collection=\"lists\" item=\"list\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            "#{list} " +
            "</foreach>" +
            "<if test='phone != null'> AND u.phone like CONCAT('%',#{phone},'%') </if>" +
            "<if test='name != null'> AND u.name  like CONCAT('%',#{name},'%')</if>" +
            "</script>"})
    List<User> selectUser(@Param("lists") List<Integer> lists,@Param("name")String name,@Param("phone")String phone);
    @Update({"<script>" +
            "UPDATE `tj_user` SET role = 1 " +
            " WHERE user_id= #{userId} "+
            "</script>"})
    int updateByUserId(Integer userId);
}