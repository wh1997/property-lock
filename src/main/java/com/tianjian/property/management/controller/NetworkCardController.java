package com.tianjian.property.management.controller;

import com.tianjian.property.management.service.NetworkCardService;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.utils.error.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.spi.http.HttpHandler;
import java.util.List;
import java.util.Map;

/**
 * @description:网卡接口
 * @author: ManolinCoder
 * @time: 2021/6/22
 */
@RestController
@RequestMapping("/NetworkCard")
public class NetworkCardController {
    @Autowired
    private NetworkCardService networkCardService;
    @PostMapping("/update/NetworkCard")
    
    /** 
    * @Description: 修改网卡设备状态
    * @Param: [map] 
    * @return: com.tianjian.Property.utils.LockResult
    * @Date: 2021/7/1 
    */
    public LockResult updateStatus(@RequestBody Map  map){
        try {
            //网卡设备id
            Integer id = (Integer) map.get("id");
            //要修改的状态
            Integer status= (Integer) map.get("status");
            networkCardService.updateStatus(id,status);
            return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),null);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
  @PostMapping("/select/equipment")
    /**
    * @Description:  查询控制器
    * @Param:
    * @return:
    * @Date: 2021/7/2
    */
    public LockResult selectEquipment(@RequestBody Map  map){
        try {
            //小区编号
            Integer Pid = (Integer) map.get("propertyId");
            List resultMap= networkCardService.selectEquipment(Pid);
            return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
  @PostMapping("/open/lock")
    /**
    * @Description:  开锁
    * @Param:
    * @return:
    * @Date: 2021/7/2
    */
    public LockResult openLock(@RequestBody Map  map ,@RequestHeader String token){
        try {
            //设备IMEI号码
            String imei = (String) map.get("imei");
            Integer appUID = TokenUtil.getAppUID(token);
            //请求用户所属的物业编号
            Integer Pid = (Integer) map.get("propertyId");
            Map resultMap= networkCardService.openLock(imei,appUID.toString(),Pid.toString());
            if ("TRUE".equals(resultMap.get("status"))){
                return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),resultMap);
            }else {
                return new LockResult(false, "开锁失败",10,"");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
  @PostMapping("/equipment/status") //TODO  接口存在错误
    /**
    * @Description:  查询设备状态
    * @Param:
    * @return:
    * @Date: 2021/7/2
    */
    public LockResult selectEquipmentStatus(@RequestBody Map  map){
        try {
            //设备IMEI号码
            String Imei = (String) map.get("imei");
            Map resultMap= networkCardService.selectEquipmentStatus(Imei);
            return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
}