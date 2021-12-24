package com.tianjian.property.dao;

import org.apache.poi.ss.formula.functions.T;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/9/26
 */
public interface BaseDao<T> extends Mapper<T> , MySqlMapper<T> {
}
