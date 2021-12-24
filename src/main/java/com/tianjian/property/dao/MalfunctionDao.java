package com.tianjian.property.dao;

import com.tianjian.property.bean.Malfunction;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MalfunctionDao extends BaseDao<Malfunction> {
}