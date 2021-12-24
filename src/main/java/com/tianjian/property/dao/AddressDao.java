package com.tianjian.property.dao;

import com.tianjian.property.bean.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/26
 */
@Mapper
@Repository
public interface AddressDao extends BaseDao<Address>{
    @Select({"<script>" +
            " WITH RECURSIVE TEMP AS ( " +
            " SELECT * FROM tj_address T WHERE 1 = 1 " +
            "<if test='addressIds != null and addressIds.size() > 0'>" +
            " AND T.id in " +
            "<foreach collection=\"addressIds\" item=\"addressId\" index=\"index\" open=\"(\" close=\")\" separator=\",\"> " +
            "#{addressId} " +
            "</foreach>" +
            "</if>" +
            " UNION ALL SELECT T0.* FROM TEMP,tj_address T0 WHERE TEMP.parent_id=T0.id ) " +
            " SELECT DISTINCT * FROM TEMP  ORDER BY level, sort DESC " +
            "</script>"})
    List<Address> selectWithParentByIds(@Param("addressIds") List<Integer> addressIds);
}
