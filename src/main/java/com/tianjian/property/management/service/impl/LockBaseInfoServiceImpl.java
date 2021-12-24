package com.tianjian.property.management.service.impl;

import com.tianjian.property.bean.Door;
import com.tianjian.property.bean.Lock;
import com.tianjian.property.bean.LockLog;
import com.tianjian.property.dao.*;
import com.tianjian.property.management.service.GatewayService;
import com.tianjian.property.management.service.LockBaseInfoService;
import com.tianjian.property.utils.DateUtils;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.error.BusinessException;
import com.tianjian.property.utils.error.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/6/22
 */
@Service
@Slf4j
public class LockBaseInfoServiceImpl implements LockBaseInfoService {
    @Autowired
    public LockBaseInfoDao lockBaseInfoDao;
    @Autowired
    public LockDao lockDao;
    @Autowired
    public DoorDao doorDao;
    @Autowired
    public UserDao userDao;
    @Autowired
    public LockLogDao lockLogDao;
    @Autowired
    public GatewayService gatewayService;
    @Value("${apartment.theRemoteUnlock}")
    private  String theRemoteUnlock;
    @Override
    @Transactional(rollbackFor=Exception.class)
    public LockResult updateStatus(String lockId, Integer lock, Integer id, Integer status) {
        Lock lock1 = lockDao.selectByLockFacilityId(lock);
        Integer doorId = lock1.getDoorId();
        Integer lockFacilityId = lock1.getLockFacilityId();
        Integer id1 = lock1.getId();
        Integer lockGatewayId = lock1.getLockGatewayId();
        //判断是否绑定网关
        if (lockGatewayId==-1){
            //修改门的状态为无锁
            int i = doorDao.updateDoorStatus(doorId,3);
            //修改门锁网关绑定
            int o = lockDao.updateStatus(id1);
            //修改锁的状态为废弃
            int p = lockBaseInfoDao.updateStatus(lockFacilityId,2);
            if (i>=0&&o>=0&&p>=0){
                return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),"");
            }else {
                return new LockResult(false, ErrorEnum.OPERATION_ERROR.getErrorMsg(),ErrorEnum.OPERATION_ERROR.getCode(),"");
            }
        }else {
            //先删除蓝牙门锁绑定的网关
            Map map = gatewayService.LockUnBindingGateway(lockId, lock, id);
            Integer resultCode = (Integer) map.get("resultCode");
            if(resultCode==0){
                //修改门的状态为无锁
                int i = doorDao.updateDoorStatus(doorId,3);
                //修改门锁网关绑定
                int o = lockDao.updateStatus(id1);
                //修改锁的状态为废弃
                int p = lockBaseInfoDao.updateStatus(lockFacilityId,2);
                if (i>=0&&o>=0&&p>=0){
                    return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),"");
                }else {
                    return new LockResult(false, ErrorEnum.OPERATION_ERROR.getErrorMsg(),ErrorEnum.OPERATION_ERROR.getCode(),"");
                }
            }
            return new LockResult(false, ErrorEnum.OPERATION_ERROR.getErrorMsg(),ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }

    @Override
    public LockResult openLock(String lockId, Integer lockUserId, Integer doorID,Integer userId) throws Exception {
        List<Map> selectlock= doorDao.selectlock(doorID);
        if (selectlock.size()==0){
            return new LockResult(false,"开门失败,请确认门锁状态正常",ErrorEnum.SYSTEM_ERROR.getCode(),"");
        }
        Map map = selectlock.get(0);
        Integer id = (Integer) map.get("id");
        Integer propertyId = (Integer) map.get("propertyId");
        Integer doorType = (Integer) map.get("doorType");
        String lockMac = (String) map.get("lockMac");
        Integer status = (Integer) map.get("status");
        if(status!=0){
            HashMap<String, Object> datamap = new HashMap<>();
            //是	string 门锁id
            datamap.put("lockId",lockId);
            //是	int 门锁用户ID （操作开锁的门锁用户ID，开锁日志上报时，根据该值，可以知道是哪个门锁用户进行开锁操作详情见附录字段说明）
            //900	超级管理员（一般分配给门锁所有者）
            //901~2000	普通管理员
            //2001~49999	普通用户
            //50000~52000	普通用户（人脸识别）
            datamap.put("lockUserId",lockUserId);
            //开锁
            Map result = gatewayService.bindinggateway(theRemoteUnlock,datamap);
            Integer resultCode = (Integer) result.get("resultCode");
            String reason = (String) result.get("reason");
            Date date = new Date();
            String s = DateUtils.dateToString(date);
            LockLog lockLog = new LockLog();
            lockLog.setDoorId(id);
            lockLog.setLockType(doorType);
            lockLog.setLockMac(lockMac);
            lockLog.setRecordTime(s);
            lockLog.setPropertyId(propertyId);
            lockLog.setUserId(userId);
            lockLog.setAddTime(s);
            if (resultCode==0){
                lockLog.setStatus(0);
                int i = lockLogDao.insertSelective(lockLog);
                if (i<=0){
                    log.warn("开锁数据添加失败数据为: "+lockLog.toString());
                }
                return new LockResult(true, "开门成功",ErrorEnum.SUCCESS.getCode(),"");
            }else {
                lockLog.setStatus(1);
                lockLogDao.insertSelective(lockLog);
                return new LockResult(false, reason,ErrorEnum.SYSTEM_ERROR.getCode(),"");
            }
        }else {
            return new LockResult(false, "房间有人请勿开锁",ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }

    @Override
    public LockResult deleteLock(Integer doorId) {
        //将门的状态改为无锁
        doorDao.updateDoorStatus(doorId,3);
        //获取门锁信息
        Lock lock = lockDao.selectByDoorid(doorId);
        Integer id = lock.getId();
        Integer lockFacilityId = lock.getLockFacilityId();
        //修改锁的状态
        lockDao.updateStatus(id);
        //修改锁的状态
        lockBaseInfoDao.updateStatus(lockFacilityId,2);
        return  new LockResult(true, "删除成功",ErrorEnum.SUCCESS.getCode(),"");
    }
}
