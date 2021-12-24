package com.tianjian.property.dao;

import com.tianjian.property.bean.UserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserRoleDao extends BaseDao<UserRole> {
    @Update({"<script>" +
            "UPDATE `tj_user_role` SET status = 1 " +
            " WHERE id IN "+
            "<foreach collection=\"lists\" item=\"list\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            "#{list} " +
            "</foreach>" +
            "</script>"})
    int updateStatus(@Param("lists") List<Integer> lists);
    @Select({"<script>" +
            "SELECT u.id urId, r.* " +
            " FROM tj_user_role u " +
            " INNER JOIN tj_role r " +
            " ON  u.role_id=r.id " +
            " WHERE "+
            " u.`status` = 0 " +
            " AND r.`status` = 0 " +
            " AND u.user_id = #{userId}" +
            "</script>"})
    List<Map> selectRole(Integer userId);
    @Insert(" <script> " +
            " INSERT INTO tj_user_role (user_id, role_id,add_person,status)  VALUES " +
            " <foreach collection=\"lists\" item=\"list\" index=\"index\"  separator=\",\">  " +
            " (#{list.userId} , #{list.roleId}, #{list.addPerson}, #{list.status}) " +
            " </foreach> " +
            " </script> ")
    int batchInsert(@Param("lists") ArrayList<UserRole> lists);
}