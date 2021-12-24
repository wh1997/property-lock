package com.tianjian.property.web.controller;

import com.google.inject.internal.cglib.core.$ClassEmitter;
import com.tianjian.property.bean.Door;
import com.tianjian.property.bean.Gateway;
import com.tianjian.property.bean.LockBaseInfo;
import com.tianjian.property.bean.vo.Param;
import com.tianjian.property.management.service.GatewayService;
import com.tianjian.property.management.service.LockBaseInfoService;
import com.tianjian.property.utils.BeanChangeUtils;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.PageResult;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.utils.error.ErrorEnum;
import com.tianjian.property.web.service.ManageService;
import com.tianjian.property.web.service.SelectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:  设备管理
 * @author: ManolinCoder
 * @time: 2021/11/4
 */
@RestController
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private ManageService manageService;
    @Autowired
    private LockBaseInfoService lockBaseInfoService;
    @Autowired
    private SelectRoleService selectRoleService;
    @Autowired
    private GatewayService gatewayService;
    /**
    * @Description: 删除蓝牙门锁
    * @Param:  
    * @return:  
    * @Date: 2021/11/8 
    */
    @PostMapping("/bluetooth/delete")
    public LockResult deleteBluetooth(@RequestHeader String token ,@RequestBody Map map)  {
        try {
            //门锁设备id
            Integer id = (Integer) map.get("id");
            //要修改的状态
            Integer status= (Integer) map.get("status");
            //锁id
            Integer lock= (Integer) map.get("lock");
            //门锁id(厂家生成的id)
            String lockId= (String) map.get("lockId");
            LockResult result = lockBaseInfoService.updateStatus(lockId, lock, id, status);
            return result;
        }catch (Exception e){
            return new LockResult(false,"删除失败", ErrorEnum.OPERATION_ERROR.getCode(), "");
        }
    }
    /**
    * @Description: 修改蓝牙门锁
    * @Param:
    * @return:
    * @Date: 2021/11/8
    */
    @PostMapping("/bluetooth/updata")
    public LockResult updataBluetooth(@RequestHeader String token ,@RequestBody Map map) throws Exception {
        LockBaseInfo lockBaseInfo = BeanChangeUtils.mapToBean(map, LockBaseInfo.class);
        int i = manageService.updataBluetooth(lockBaseInfo);
        if (i>0){
            return new LockResult(true,"修改成功", ErrorEnum.SUCCESS.getCode(), "");
        }
        return new LockResult(false,"修改失败", ErrorEnum.OPERATION_ERROR.getCode(), "");
    }
    /**
    * @Description: 查看蓝牙门锁设备信息
    * @Param:
    * @return:
    * @Date: 2021/11/8
    */
    @PostMapping("/bluetooth/select")
    public LockResult selectBluetooth(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Integer appUID = TokenUtil.getAppUID(token);
            List<Integer> propertyList = selectRoleService.selectRole(appUID);
            if (propertyList==null){
                return new LockResult(false,"没有权限,请添加权限", ErrorEnum.RIGHT.getCode(), "");
            }
            LockBaseInfo lockBaseInfo = BeanChangeUtils.mapToBean(map, LockBaseInfo.class);
            String doorName = (String) map.get("doorName");
            //设备所在项目的id
            Integer pageNum = (Integer) map.get("pageNum");
            Integer pageSize = (Integer) map.get("pageSize");
            PageResult result= manageService.selectBluetooth(doorName,propertyList,lockBaseInfo,pageNum,pageSize);
            if (result==null){
                return new LockResult(true,"查询成功没有数据",ErrorEnum.SUCCESS.getCode(),result);
            }
            return new LockResult(true,"查询成功",ErrorEnum.SUCCESS.getCode(),result);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
    /**
    * @Description: 查看蓝牙锁详情
    * @Param:
    * @return:
    * @Date: 2021/11/8
    */
    @PostMapping("/bluetooth/details")
    public LockResult bluetoothdDetails(@RequestHeader String token ,@RequestBody Map map) {
        String lockId= (String) map.get("lockId");
        Map details = manageService.BluetoothdDetails(lockId);
        Integer resultCode = (Integer) details.get("resultCode");
        String reason = (String) details.get("reason");
        if (resultCode==0){
            return new LockResult(true,"查询成功",ErrorEnum.SUCCESS.getCode(),details);
        }else{
            return new LockResult(false,reason,ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
    /**
    * @Description: 查看蓝牙锁绑定网关详情
    * @Param:
    * @return:
    * @Date: 2021/11/8
    */
    @PostMapping("/lock/gateway/details")
    public LockResult lockGatewayDetails(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Integer lockId= (Integer) map.get("lockId");
            Map details = manageService.lockGatewayDetails(lockId);
            if (details==null){
                return new LockResult(false,"未绑定网关请绑定网关",ErrorEnum.OPERATION_ERROR.getCode(),details);
            }
            return new LockResult(true,"查询成功",ErrorEnum.SUCCESS.getCode(),details);
        } catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.COMMON_ERROR.getErrorMsg(), ErrorEnum.COMMON_ERROR.getCode(), "");
        }
    }
    /**
    * @Description: 设置蓝牙门锁
    * @Param:
    * @return:
    * @Date: 2021/11/8
    */
    @PostMapping("/lock/configuration")
    public LockResult configuration(@RequestHeader String token ,@RequestBody Map map) {
        try {
            LockResult result = manageService.configuration(map);
            return result;
        } catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,  ErrorEnum.COMMON_ERROR.getErrorMsg(), ErrorEnum.COMMON_ERROR.getCode(), "");
        }
    }
    /**
    * @Description: 读取蓝牙门锁设置信息
    * @Param:
    * @return:
    * @Date: 2021/11/8
    */
    @PostMapping("/lock/information")
    public LockResult information(@RequestHeader String token ,@RequestBody Map map) {
        try {
            LockResult result = manageService.information(map);
            return result;
        } catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,  ErrorEnum.COMMON_ERROR.getErrorMsg(), ErrorEnum.COMMON_ERROR.getCode(), "");
        }
    }
    /**
    * @Description: 查看网关信息
    * @Param:
    * @return:
    * @Date: 2021/11/8
    */
    @PostMapping("/gateway/select")
    public LockResult selectGateway(@RequestHeader String token ,@RequestBody Map map) throws Exception {
        try {
            Integer appUID = TokenUtil.getAppUID(token);
            List<Integer> propertyList = selectRoleService.selectRole(appUID);
            if (propertyList==null){
                return new LockResult(false,"没有权限,请添加权限", ErrorEnum.RIGHT.getCode(), "");
            }
            Gateway gateway = BeanChangeUtils.mapToBean(map, Gateway.class);
            //设备所在项目的id
            Integer pageNum = (Integer) map.get("pageNum");
            Integer pageSize = (Integer) map.get("pageSize");
            PageResult result= manageService.selectGateway(propertyList,gateway,pageNum,pageSize);
            if (result==null){
                return new LockResult(true,"查询成功没有数据",ErrorEnum.SUCCESS.getCode(),result);
            }
            return new LockResult(true,"查询成功",ErrorEnum.SUCCESS.getCode(),result);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
    /**
     * @Description: 编辑网关
     * @Param:
     * @return:
     * @Date: 2021/11/8
     */
    @PostMapping("/gateway/update")
    public LockResult updateGateway(@RequestHeader String token ,@RequestBody Map map) {
        try {
            Gateway gateway = BeanChangeUtils.mapToBean(map, Gateway.class);
            int i = manageService.updateGateway(gateway);
            if (i>0){
                return new LockResult(true,  "修改成功", ErrorEnum.SUCCESS.getCode(), "");
            }else {
                return new LockResult(false,  "修改失败", ErrorEnum.OPERATION_ERROR.getCode(), "");
            }
        } catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,  ErrorEnum.COMMON_ERROR.getErrorMsg(), ErrorEnum.COMMON_ERROR.getCode(), "");
        }
    }
    /**
     * @Description: 删除网关
     * @Param:
     * @return:
     * @Date: 2021/11/8
     */
    @PostMapping("/gateway/delete")
    public LockResult deleteGateway(@RequestHeader String token ,@RequestBody Map map) {
        try {
            String gatewayId = (String) map.get("gatewayId");
            return gatewayService.deleteGateway(gatewayId);
        } catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,  ErrorEnum.COMMON_ERROR.getErrorMsg(), ErrorEnum.COMMON_ERROR.getCode(), "");
        }
    }
    /**
     * @Description: 网关详情
     * @Param:
     * @return:
     * @Date: 2021/11/8
     */
    @PostMapping("/gateway/details")
    public LockResult gatewayDetails(@RequestHeader String token ,@RequestBody Map map) {
        try {
            String gatewayId = (String) map.get("gatewayId");
            Map resultMap=manageService.gatewayDetails(gatewayId);
            if (resultMap!=null){
                return new LockResult(true,ErrorEnum.SUCCESS.getErrorMsg(), ErrorEnum.SUCCESS.getCode(), resultMap);
            }else {
                return new LockResult(false,  ErrorEnum.COMMON_ERROR.getErrorMsg(), ErrorEnum.COMMON_ERROR.getCode(), "");
            }
        } catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,  ErrorEnum.COMMON_ERROR.getErrorMsg(), ErrorEnum.COMMON_ERROR.getCode(), "");
        }
    }
}
