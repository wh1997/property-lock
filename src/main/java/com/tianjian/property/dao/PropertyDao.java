package com.tianjian.property.dao;

import com.tianjian.property.bean.Property;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/7/5
 */
@Mapper
@Repository
public interface PropertyDao extends BaseDao<Property> {
    @Select({"<script>" +
            " SELECT  tj_oldProperty_id" +
            " FROM tj_property WHERE bw_property_id = #{pid}"+
            "</script>"})
    Integer selectByPropertyId(Integer pid);
    @Select({"<script>" +
            "SELECT branch_id branchId, bw_property_id propertyId,property_name propertyName  " +
            " FROM tj_property WHERE status=0 AND bw_property_id IN "+
            "<foreach collection=\"lists\" item=\"list\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            "#{list} " +
            "</foreach>" +
            "</script>"})
    List<Map> selectProperty(@Param("lists") List<Integer> lists);
    @Select({"<script>" +
            "SELECT branch_id branchId, bw_property_id propertyId,property_name propertyName ,address_id addressId"  +
            " FROM tj_property WHERE status=0  "+
            "</script>"})
    List<Map> selectPropertyAll();
    @Select({"<script>" +
            "SELECT address_id addressId" +
            " FROM tj_property WHERE status=0 AND bw_property_id IN "+
            "<foreach collection=\"lists\" item=\"list\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            "#{list} " +
            "</foreach>" +
            "</script>"})
    List<Integer> selectPropertyAddressId(@Param("lists") List<Integer> lists);
    @Select({"<script>" +
            "SELECT address_id addressId"  +
            " FROM tj_property WHERE status=0  "+
            "</script>"})
    List<Integer> selectPropertyAllAddressId();
    @Select({"<script>" +
            " SELECT * "  +
            " FROM tj_property WHERE status=0  "+
            "</script>"})
    List<Property> selectByStatus();

}
