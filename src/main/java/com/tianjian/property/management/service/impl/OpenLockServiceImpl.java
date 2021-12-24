package com.tianjian.property.management.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianjian.property.bean.Lock;
import com.tianjian.property.bean.LockLog;
import com.tianjian.property.bean.Module;
import com.tianjian.property.bean.UseropenLock;
import com.tianjian.property.bean.vo.LockLogVo;
import com.tianjian.property.bean.vo.PasswordLock;
import com.tianjian.property.dao.DoorDao;
import com.tianjian.property.dao.LockDao;
import com.tianjian.property.dao.LockLogDao;
import com.tianjian.property.dao.UseropenLockDao;
import com.tianjian.property.management.service.GatewayService;
import com.tianjian.property.management.service.OpenLockService;
import com.tianjian.property.utils.*;
import com.tianjian.property.utils.error.BusinessException;
import com.tianjian.property.utils.error.ErrorEnum;
import com.tianjian.property.web.service.SelectRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/24
 */
@Service
@Slf4j
public class OpenLockServiceImpl implements OpenLockService  {
    @Autowired
    private GatewayService gatewayService;
    @Autowired
    private DoorDao doorDao;
    @Autowired
    private LockDao lockDao;
    @Autowired
    private LockLogDao lockLogDao;
    @Autowired
    private UseropenLockDao useropenLockDao;
    @Value("${apartment.theRemoteUnlock}")
    private  String theRemoteUnlock;
    //密码开锁
    @Value("${apartment.PasswordKey}")
    private  String passwordKey;
    //删除钥匙
    @Value("${apartment.DelKey}")
    private  String DelKey;


    @Override
    @Transactional
    public LockResult openLock(Integer userId, Integer doorId, Integer lockUserId) throws ParseException, BusinessException {
        List<Map> selectlock = doorDao.selectlock(doorId);
        if (selectlock.size()==0){
            return new LockResult(false, "开门失败,请确认门锁状态正常",ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
        Map map = selectlock.get(0);
        Integer id = (Integer) map.get("id");
        Integer propertyId = (Integer) map.get("propertyId");
        Integer doorType = (Integer) map.get("doorType");
        String lockMac = (String) map.get("lockMac");
        String lockId = (String) map.get("lockId");
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
        }

    @Override
    public PageResult<LockLogVo> openLockLog(LockLog lockLog,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<LockLogVo> rows = lockLogDao.openLockLog(lockLog);
        PageInfo<LockLogVo> staffPageInfo = new PageInfo<>(rows);
        List<LockLogVo> row = staffPageInfo.getList();
        int pages = staffPageInfo.getPages();
        //总共多少条
        long total = staffPageInfo.getTotal();
        PageResult<LockLogVo> PageResult = new PageResult<>(pageSize,pageNum,row,total,pages);
        return PageResult;
    }

    @Override
    public LockResult passwordKey(PasswordLock map, Integer doorId, Integer appUID) throws Exception {
        Map<String, Object> map1 = doorDao.selectDoor(doorId);
        List<Map> selectlock = doorDao.selectlock(doorId);
        String lockId=null;
        if (selectlock.size()>0){
            Map map2 = selectlock.get(0);
            lockId = (String) map2.get("lockId");
        }else {
            return new LockResult(false, "没有查询到门锁信息，无法设置密码",ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
        Integer doorStatus = (Integer) map1.get("doorStatus");
        if (doorStatus==0){
            return new LockResult(false, "房屋已有住户,无法看房",ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
        String tokenid = gatewayService.getApartment();
        String keyContent = map.getKeyContent();
        Long validStartTime = map.getValidStartTime();
        Long validEndTime=map.getValidEndTime();
        if (validStartTime==null || validEndTime==null){
            return new LockResult(false, "请添加起始时间和有效时间",ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
        UseropenLock selectUseropenLock =useropenLockDao.selectPassword(doorId);
        if (selectUseropenLock!=null){
            useropenLockDao.updateStatus(doorId,appUID);
        }
        String password = AESUtil.encryptKey(tokenid, keyContent);
        Map newMap = JSON.parseObject(JSON.toJSONString(map), Map.class);
        newMap.keySet().remove("keyContent");
        newMap.put("delLockUserId",900);
        newMap.put("keyContent",password);
        newMap.put("lockId",lockId);
        Map result = gatewayService.bindinggateway(passwordKey,newMap);
        Integer resultCode = (Integer) result.get("resultCode");
        String reason = (String) result.get("reason");
        Map data = (Map) result.get("data");
        Integer lockKeyId = (Integer) data.get("lockKeyId");
        if (resultCode==0){
            UseropenLock useropenLock = new UseropenLock();
            useropenLock.setDoorId(doorId);
            useropenLock.setLockId(lockId);
            useropenLock.setLockUserId(map.getLockUserId());
            useropenLock.setKeyContent(keyContent);
            useropenLock.setAddPerson(appUID);
            useropenLock.setStatus(0);
            useropenLock.setValidstartTime(validStartTime);
            useropenLock.setValidendTime(validEndTime);
            useropenLock.setLockKeyId(lockKeyId);
            int i = useropenLockDao.insertUseropenLock(useropenLock);
            Integer id = useropenLock.getId();
            useropenLock.setId(id);
            if (i>0){
                return new LockResult(true, "添加成功",ErrorEnum.SUCCESS.getCode(),useropenLock);
            }else {
                return new LockResult(false, "添加失败",ErrorEnum.SYSTEM_ERROR.getCode(),"");
            }

        }else {
            return new LockResult(false, reason,ErrorEnum.SYSTEM_ERROR.getCode(),"");
        }
    }

    @Override
    public LockResult selectPassword(Integer doorId) {
        UseropenLock useropenLock = useropenLockDao.selectPassword(doorId);
        if(useropenLock!=null){
            return new LockResult(true,ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),useropenLock);
        }else {
            return new LockResult(true,"查询成功没有设置密码",ErrorEnum.SUCCESS.getCode(),"");
        }
    }

    @Override
    public LockResult deletePassword(Integer appUID, Integer doorId, Integer lockKeyId) throws Exception {
        String lockId = lockDao.selsetlockId(doorId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("lockId",lockId);
        map.put("delLockUserId",900);
        map.put("deleteType",2);
        map.put("lockKeyId",lockKeyId);
        map.put("keyType",2);
        Map result = gatewayService.bindinggateway(DelKey,map);
        Integer resultCode = (Integer) result.get("resultCode");
        String reason = (String) result.get("reason");
        if (resultCode==0){
            int i = useropenLockDao.updateStatus(doorId,appUID);
            if (i>0){
                return new LockResult(true, "删除成功",ErrorEnum.SUCCESS.getCode(),"");
            }else {
                return new LockResult(false, "删除失败",ErrorEnum.SYSTEM_ERROR.getCode(),"");
            }
        }else {
            return new LockResult(false, reason,ErrorEnum.SYSTEM_ERROR.getCode(),"");
        }
    }

}
