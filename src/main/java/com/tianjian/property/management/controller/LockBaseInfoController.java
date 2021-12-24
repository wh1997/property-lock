package com.tianjian.property.management.controller;

import com.tianjian.property.management.service.LockBaseInfoService;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.utils.error.ErrorEnum;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description:蓝牙门锁接口
 * @author: ManolinCoder
 * @time: 2021/6/22
 */
@RestController
@RequestMapping("/LockBaseInfo")
public class LockBaseInfoController {
    @Autowired
    public LockBaseInfoService lockBaseInfoService;
    @PostMapping("/update/baseinfo")
    
    /** 
    * @Description: 删除蓝牙门锁
    * @Param: [map] 
    * @return: com.tagen.lock.utils.LockResult 
    * @Date: 2021/6/22 
    */
    public LockResult updateStatus(@RequestBody Map map){
        try {
            //门锁设备id
            Integer id = (Integer) map.get("id");
            //要修改的状态
            Integer status= (Integer) map.get("status");
            //锁id
            Integer lock= (Integer) map.get("lock");
            //门锁id(厂家生成的id)
            String lockId= (String) map.get("lockId");
            return lockBaseInfoService.updateStatus(lockId, lock, id, status);
        }catch (Exception e){
            return new LockResult(false, ErrorEnum.OPERATION_ERROR.getErrorMsg(),ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
    @PostMapping("/open/lock")
    
    /** 
    * @Description: 管理员开锁 
    * @Param: [map] 
    * @return: com.tagen.lock.utils.LockResult 
    * @Date: 2021/6/23 
    */
    public LockResult openLock(@RequestHeader String token, @RequestBody Map map){
        try {
            Integer appUID = TokenUtil.getAppUID(token);
            //门锁id(厂家生成的id)
            String lockId = (String) map.get("lockId");
            //门锁用户ID
            Integer lockUserId= (Integer) map.get("lockUserId");
            //门id
            Integer doorId= (Integer) map.get("doorId");
            return lockBaseInfoService.openLock(lockId, lockUserId, doorId,appUID);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
    @PostMapping("/delete/lock")

    /**
    * @Description: 门锁呗初始化后删除绑定的门
    * @Param: [map]
    * @return: com.tagen.lock.utils.LockResult
    * @Date: 2021/6/23
    */
    public LockResult deleteLock(@RequestHeader String token, @RequestBody Map map){
        try {
            Integer doorId = (Integer) map.get("doorId");
            return lockBaseInfoService.deleteLock(doorId);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
}
