package com.tianjian.property.management.controller;

import com.tianjian.property.bean.Lock;
import com.tianjian.property.management.service.GatewayService;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.error.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/** 
* @Description: 网关接口 
* @Param:  
* @return:  
* @Date: 2021/6/30 
*/
@RestController
@RequestMapping("/gateway")
public class GatewayController {
    @Autowired
    private GatewayService gatewayService;
    
    @PostMapping("/binding")
    
    /** 
    * @Description: 向汇享家绑定网关 
    * @Param: [map] 
    * @return: com.tianjian.Property.utils.LockResult
    * @Date: 2021/7/1 
    */
    public LockResult bindingGateway(@RequestBody Map map){
        try {
            Integer project = (Integer) map.get("projectId");
            String gatewaySeq = (String) map.get("gatewaySeq");
            Map result = gatewayService.gatewayBind(project, gatewaySeq);
            Integer resultCode = (Integer) result.get("resultCode");
            String reason = (String) result.get("reason");
            if(resultCode==0){
                return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),result);
            }
            return new LockResult(false,"绑定失败:"+reason,ErrorEnum.OPERATION_ERROR.getCode(),result);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }

    }
    @PostMapping("/hxjDelete")

    /**
    * @Description: 删除网关
    * @Param: [map]
    * @return: com.tianjian.Property.utils.LockResult
    * @Date: 2021/7/1
    */
    public LockResult deleteGateway(@RequestBody Map map){
        try {
            String gatewayId = (String) map.get("gatewayId");
            return gatewayService.deleteGateway(gatewayId);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }

    @PostMapping("/lock/gateway")
    
    /** 
    * @Description: 蓝牙锁绑定网关
    * @Param: [map] 
    * @return: com.tagen.lock.utils.LockResult 
    * @Date: 2021/6/22 
    */
    public LockResult LockBindingGateway(@RequestBody Map map){
        try {
            //门锁id
            String lockId = (String) map.get("lockId");
            //网关id
            String gatewayId = (String) map.get("gatewayId");
            //门id
            Integer doorID = (Integer) map.get("doorId");
            //网关设备id
            Integer gateway = (Integer) map.get("gateway");
            //蓝牙锁设备id
            Integer lock = (Integer) map.get("lock");
            Map result = gatewayService.LockBindingGateway(lockId, gatewayId,doorID,gateway,lock);
            Integer resultCode = (Integer) result.get("resultCode");
            String reason = (String) result.get("reason");
            if(resultCode==0){
                return new LockResult(true,ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),result);
            }
            return new LockResult(false,"绑定失败:"+reason,ErrorEnum.OPERATION_ERROR.getCode(),result);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),"");
        }
    }
    @PostMapping("/delete")
    
    /** 
    * @Description: 解除门锁绑定网关 
    * @Param: [map] 
    * @return: com.tianjian.Property.utils.LockResult
    * @Date: 2021/7/1 
    */
    public LockResult LockUnBindingGateway(@RequestBody Map map){
        try {
            //门锁id
            String lockId = (String) map.get("lockId");
            //锁id
            Integer lock = (Integer) map.get("lock_id");
            //蓝牙锁设备id
            Integer bluetoothLockId = (Integer) map.get("bluetoothLockId");
            Map result = gatewayService.LockUnBindingGateway(lockId,lock,bluetoothLockId);
            Integer resultCode = (Integer) result.get("resultCode");
            String reason = (String) result.get("reason");
            if(resultCode==0){
                return new LockResult(true,ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),result);
            }
            return new LockResult(false,"解除绑定失败:"+reason,ErrorEnum.OPERATION_ERROR.getCode(),result);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }

    }
    @PostMapping("/update/status")
    
    /** 
    * @Description: 修改蓝牙网关的设备状态
    * @Param: [map] 
    * @return: com.tianjian.Property.utils.LockResult
    * @Date: 2021/7/1 
    */
    public LockResult UpdateGatewayStatus(@RequestBody Map map){
        try {
            //网关设备id
            Integer id = (Integer) map.get("id");
            //要修改的状态
            Integer status= (Integer) map.get("status");
            List<Lock> list = gatewayService.updateStatus(id, status);
            if(list!=null){
                return   new LockResult(false,"请先解除蓝牙锁的绑定",ErrorEnum.OPERATION_ERROR.getCode(),null);
            }else {
                return   new LockResult(true,ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }

    }
}
