package com.tianjian.property.dao;

import com.tianjian.property.bean.Module;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@Repository
public interface ModuleDao extends BaseDao<Module>  {
    @Update({"<script>" +
            "UPDATE `tj_module` SET status = 1 " +
            " WHERE id= #{id} "+
            "</script>"})
    int deleteModule(Integer id);
    @Select({"<script>" +
            " SELECT *  " +
            "FROM tj_module WHERE  status=0 "+
            "<if test='module != null'> " +
            "<if test='module.id != null'> AND id = #{module.id}</if>" +
            "<if test='module.parentId != null'> AND parent_id = #{module.parentId}</if>" +
            "<if test='module.name != null'> AND name  like CONCAT('%',#{module.name},'%')</if>" +
            "<if test='module.url != null'> AND url like CONCAT('%',#{module.url},'%')</if>" +
            "<if test='module.icon != null'> AND icon like CONCAT('%',#{module.icon},'%')</if>" +
            "<if test='module.base != null'> AND base  = #{module.base}</if>" +
            "<if test='module.status != null'> AND status  = #{module.status}</if>" +
            "<if test='module.sort != null'> AND sort = #{module.sort}</if>" +
            "<if test='module.createBy != null'> AND create_by = #{module.createBy}</if>" +
            "<if test='module.updateBy != null'> AND update_by = #{module.updateBy}</if>" +
            "</if>" +
            "</script>"})
    List<Module> selectModule(@Param("module") Module module);
    @Select({"<script>" +
            " SELECT m.* FROM `tj_auth` a " +
            " INNER JOIN tj_module m ON " +
            " a.resources_id = m.id WHERE " +
            " a.`status` = 0 AND m.`status` = 0 " +
            " AND a.role_id in  " +
            " <foreach collection=\"lists\" item=\"list\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            " #{list} " +
            " </foreach>" +
            " GROUP BY m.id " +
            "</script>"})
    List<Module> selectLimitModule(@Param("lists")List lists);
}