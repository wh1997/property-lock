package com.tianjian.property.management.service;

import com.tianjian.property.bean.Property;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/7/2
 */
public interface PropertyService {
    /*List getBuildings(Integer parkId);

    List getApartments(Integer buildingId);*/

    List getProperty(Integer appUID);

    List<Property> getListBySelect();
}
