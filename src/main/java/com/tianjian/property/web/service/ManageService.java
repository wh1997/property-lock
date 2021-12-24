package com.tianjian.property.web.service;

import com.tianjian.property.bean.Gateway;
import com.tianjian.property.bean.LockBaseInfo;
import com.tianjian.property.bean.vo.Param;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/4
 */
public interface ManageService {
    int deleteBluetooth(Integer id);

    int updataBluetooth(LockBaseInfo lockBaseInfo);

    PageResult selectBluetooth(String doorName, List<Integer> propertyList, LockBaseInfo lockBaseInfo, Integer pageNum, Integer pageSize);

    Map BluetoothdDetails(String lockId);

    Map lockGatewayDetails(Integer lockId);

    LockResult configuration(Map param);

    PageResult selectGateway(List<Integer> propertyList, Gateway gateway, Integer pageNum, Integer pageSize);

    int updateGateway(Gateway gateway);

    Map gatewayDetails(String gatewayId);

    LockResult information(Map map);
}
