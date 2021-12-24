package com.tianjian.property.dao;

import com.tianjian.property.bean.Auth;
import com.tianjian.property.bean.Module;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/18
 */
@Mapper
@Repository
public interface AuthDao extends BaseDao<Auth>{
    @Select({"<script>" +
            " SELECT " +
            "  m.*  " +
            " FROM " +
            "  `tj_auth` a " +
            "  INNER JOIN tj_module m ON a.resources_id = m.id   " +
            "   WHERE a.type='module' AND a.status=0 AND m.status=0 AND a.role_id  = #{roleId} "+
            "</script>"})
     List<Module> selectModuleAccredit(Integer roleId) ;
    @Update({"<script>" +
            "UPDATE `tj_auth` SET status = 1 " +
            " WHERE  type = #{type} " +
            " AND id IN  "+
            "<foreach collection=\"lists\" item=\"list\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            "#{list} " +
            "</foreach>" +
            "</script>"})
    int deleteRight(@Param("lists") List<Integer> lists,String type);
    @Select({"<script>" +
            " SELECT  " +
            " a.id aId,  " +
            " p.*   " +
            "FROM  " +
            " tj_auth a  " +
            " INNER JOIN tj_property p ON a.resources_id = p.bw_property_id   " +
            "WHERE  " +
            " a.type ='property'  " +
            " AND a.`status` = 0   " +
            " AND p.`status` = 0   " +
            " AND  a.role_id IN   " +
            "<foreach collection=\"lists\" item=\"list\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            "#{list} " +
            "</foreach>" +
            "</script>"})
    List<Map> propertyRight(@Param("lists") List<Integer> lists);
    @Insert({"<script> " +
            " INSERT INTO tj_auth (role_id, resources_id, person_id,type)" +
            " VALUES <foreach collection=\"auths\" item=\"auth\"  separator=\",\"> " +
            " (#{auth.roleId}, #{auth.resourcesId},#{auth.personId}, #{auth.type}) " +
            " </foreach> " +
            " </script> "
    })
    int insertAuthList(@Param("auths") ArrayList<Auth> auths);
}
