package com.tianjian.property.dao;

import com.tianjian.property.bean.Role;
import com.tianjian.property.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/6/7
 */
@Mapper
@Repository
public interface RoleDao extends BaseDao<Role> {
    @Select({"<script>" +
            " SELECT *  " +
            "FROM tj_role WHERE 1=1 "+
            "<if test='role != null'> " +
            "<if test='role.id != null'> AND id = #{role.id}</if>" +
            "<if test='role.status != null'> AND status = #{role.status}</if>" +
            "<if test='role.parentId != null'> AND parent_id = #{role.parentId}</if>" +
            "<if test='role.name != null'> AND name  like CONCAT('%',#{role.name},'%')</if>" +
            "</if>" +
            "</script>"})
    List<Role> selectRole(@Param("role") Role role);
}
