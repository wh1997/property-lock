package com.tianjian.property.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianjian.property.bean.Gateway;
import com.tianjian.property.bean.LockBaseInfo;
import com.tianjian.property.bean.NetworkCard;
import com.tianjian.property.bean.vo.Param;
import com.tianjian.property.dao.DoorDao;
import com.tianjian.property.dao.GatewayDao;
import com.tianjian.property.dao.LockBaseInfoDao;
import com.tianjian.property.dao.LockDao;
import com.tianjian.property.management.service.GatewayService;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.PageResult;
import com.tianjian.property.utils.error.ErrorEnum;
import com.tianjian.property.web.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/4
 */
@Service
public class ManageServiceImpl implements ManageService {
    @Value("${apartment.BluetoothdDetails}")
    private  String bluetoothdDetails;
    @Value("${apartment.configuration}")
    private  String configuration;
    @Value("${apartment.information}")
    private  String information;
    @Autowired
    private LockBaseInfoDao lockBaseInfoDao;
    @Autowired
    private GatewayDao gatewayDao;
    @Autowired
    private LockDao lockDao;
    @Autowired
    private DoorDao doorDao;
    @Autowired
    private GatewayService gatewayService;

    @Override
    public int deleteBluetooth(Integer id) {

        return lockBaseInfoDao.updateStatus(id,2);
    }

    @Override
    public int updataBluetooth(LockBaseInfo lockBaseInfo) {
        return lockBaseInfoDao.updateByPrimaryKeySelective(lockBaseInfo);
    }

    @Override
    public PageResult selectBluetooth(String doorName, List<Integer> propertyList, LockBaseInfo lockBaseInfo, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<LinkedHashMap<String, Object>> list= lockBaseInfoDao.selectBluetooth(propertyList,lockBaseInfo,doorName);
        PageInfo<LinkedHashMap<String, Object>> pageInfo=new PageInfo<>(list);
        List<LinkedHashMap<String, Object>> pageList = pageInfo.getList();
        int pages = pageInfo.getPages();
        long total = pageInfo.getTotal();
        PageResult<LinkedHashMap<String, Object>> pageResult = new PageResult<LinkedHashMap<String, Object>>(pageSize,pageNum,pageList,total,pages);
        return pageResult;
    }
    @Override
    public Map BluetoothdDetails(String lockId) {
        HashMap<String, Object> datamap = new HashMap<>();
        //	是	string 门锁id
        datamap.put("lockId",lockId);
        datamap.put("delLockUserId",900);
        //发送请求
        Map bindinggateway = gatewayService.bindinggateway(bluetoothdDetails, datamap);
        return bindinggateway;
    }
    @Override
    public Map lockGatewayDetails(Integer lockId) {
        HashMap<String, Object> resultMap = new HashMap<>();
        Map map=  lockDao.lockSelectGateway(lockId);
        if(map==null){
           return null;
        }
        Integer doorId = (Integer) map.get("doorId");
        String gatewayId = (String) map.get("gatewayId");
        Map<String, Object> door = doorDao.selectDoor(doorId);
        resultMap.put("door",door);
        if (gatewayId==null){
            resultMap.put("gateway","未绑定网关");
            return resultMap;
        }
        Map<String,Object> GatewayMap = gatewayService.selectGateway(gatewayId);
        if (GatewayMap==null){
            resultMap.put("gateway","未绑定网关");
            return resultMap;
        }
        resultMap.put("gateway",GatewayMap);
        return resultMap;
    }

    @Override
    public LockResult configuration(Map param) {
        //发送请求
        Map result = gatewayService.bindinggateway(configuration, param);
        Integer resultCode = (Integer) result.get("resultCode");
        String reason = (String) result.get("reason");
        if (resultCode==0){
            return new LockResult(true,"设置成功", ErrorEnum.SUCCESS.getCode(),"");
        }else{
            return new LockResult(false,"设置失败: "+ reason, ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
    @Override
    public LockResult information(Map map) {
        //发送请求
        Map result = gatewayService.bindinggateway(information, map);
        Integer resultCode = (Integer) result.get("resultCode");
        String reason = (String) result.get("reason");
        Map data = (Map) result.get("data");
        if (resultCode==0){
            return new LockResult(true,"获取成功", ErrorEnum.SUCCESS.getCode(),data);
        }else{
            return new LockResult(false,"获取失败: "+ reason, ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
    @Override
    public PageResult selectGateway(List<Integer> propertyList, Gateway gateway, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Gateway> list=  gatewayDao.selectGateway(propertyList,gateway);
        PageInfo<Gateway> pageInfo=new PageInfo<>(list);
        List<Gateway> pageList = pageInfo.getList();
        int pages = pageInfo.getPages();
        long total = pageInfo.getTotal();
        PageResult<Gateway> pageResult = new PageResult<Gateway>(pageSize,pageNum,pageList,total,pages);
        return pageResult;
    }

    @Override
    @Transactional
    public int updateGateway(Gateway gateway) {
        int update = gatewayDao.updateByPrimaryKeySelective(gateway);
        return update;
    }

    @Override
    public Map gatewayDetails(String gatewayId) {
        Map<String,Object> GatewayMap = gatewayService.selectGateway(gatewayId);
        return GatewayMap;
    }
}
