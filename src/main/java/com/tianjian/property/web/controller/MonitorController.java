package com.tianjian.property.web.controller;

import com.tianjian.property.bean.Door;
import com.tianjian.property.bean.vo.DoorVo;
import com.tianjian.property.management.service.RoomDoorService;
import com.tianjian.property.utils.BeanChangeUtils;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.PageResult;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.utils.error.ErrorEnum;
import com.tianjian.property.web.service.MonitorService;
import com.tianjian.property.web.service.SelectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:    门禁监控
 * @author: ManolinCoder
 * @time: 2021/11/4
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {
    @Autowired
    private MonitorService monitorServices;
    @Autowired
    private SelectRoleService selectRoleService;
    @Autowired
    private RoomDoorService roomDoorService;
    @PostMapping("/select/Property")
    public LockResult selecProperty(@RequestHeader String token ,@RequestBody(required = false) Map map) throws Exception {
        try {
            Integer appUID = TokenUtil.getAppUID(token);
            Integer status = (Integer) map.get("status");
            List<Map> list = selectRoleService.selecProperty(appUID,status);
            if (list==null){
                return new LockResult(false,"没有小区查询权限,请添加权限", ErrorEnum.RIGHT.getCode(), "");
            }
            return new LockResult(true,"查询成功", ErrorEnum.SUCCESS.getCode(), list);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.RIGHT.getCode(), "");
        }
    }
    
    /** 
    * @Description: 门禁监控分页查看
    * @Param:  
    * @return:  
    * @Date: 2021/11/15 
    */
    @PostMapping("/select/door")
    public LockResult selectDoor(@RequestHeader String token ,@RequestBody Map map) throws Exception {
        Integer appUID = TokenUtil.getAppUID(token);
        List<Integer> list = selectRoleService.selectRole(appUID);
        if (list==null){
            return new LockResult(false,"没有权限,请添加权限", ErrorEnum.RIGHT.getCode(), "");
        }
        Door door = BeanChangeUtils.mapToBean(map, Door.class);
        Integer propertyId = door.getPropertyId();
        if (propertyId!=null){
            if (list.contains(propertyId)){
                list.clear();
                list.add(propertyId);
            }else{
                return new LockResult(false,"没有权限,请添加权限", ErrorEnum.RIGHT.getCode(), "");
            }
        }
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        PageResult<DoorVo> doors = monitorServices.selsctAll(door,list, pageNum, pageSize);
        if (doors==null){
            return new LockResult(true,"获取成功,没有数据", ErrorEnum.SUCCESS.getCode(), "");
        }else {
            return new LockResult(true,"获取成功",ErrorEnum.SUCCESS.getCode(),doors);
        }
    }
    @PostMapping("/select/publicDoor")
    public LockResult selectPublicDoor(@RequestHeader String token ,@RequestBody Map map) throws Exception {
        Integer appUID = TokenUtil.getAppUID(token);
        List<Integer> list = selectRoleService.selectRole(appUID);
        if (list==null){
            return new LockResult(false,"没有权限,请添加权限", ErrorEnum.RIGHT.getCode(), "");
        }
        Door door = BeanChangeUtils.mapToBean(map, Door.class);
        Integer propertyId = door.getPropertyId();
        if (propertyId!=null){
            if (list.contains(propertyId)){
                list.clear();
                list.add(propertyId);
            }else{
                return new LockResult(false,"没有权限,请添加权限", ErrorEnum.RIGHT.getCode(), "");
            }
        }
        List<Integer> types = (List<Integer>) map.get("types");
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        PageResult<DoorVo> doors = monitorServices.selectPublicDoor(door,types,list, pageNum, pageSize);
        if (doors==null){
            return new LockResult(true,"获取成功,没有数据", ErrorEnum.SUCCESS.getCode(), "");
        }else {
            return new LockResult(true,"获取成功",ErrorEnum.SUCCESS.getCode(),doors);
        }
    }
    /**
    * @Description:  修改门禁监控
    * @Param:
    * @return:  
    * @Date: 2021/11/8 
    */
    @PostMapping("/update/door")
    public LockResult updateDoor(@RequestHeader String token ,@RequestBody Map map) throws Exception {
        Door door = BeanChangeUtils.mapToBean(map, Door.class);
        int i= monitorServices.updateDoor(door);
        if (i>=0){
            return new LockResult(true,"修改成功",ErrorEnum.SUCCESS.getCode(),"");
        }else{
            return new LockResult(false,"修改失败",ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
    /**
    * @Description:  添加门禁监控
    * @Param:
    * @return:
    * @Date: 2021/11/8
    */
    @PostMapping("/add/door")
    public LockResult addDoor(@RequestHeader String token ,@RequestBody Map map)  {
        try {
            Integer appUID = TokenUtil.getAppUID(token);
            List<Map> door = (List<Map>) map.get("list");
            Map resultMap = roomDoorService.addDoor(door,appUID);
            Integer code = (Integer) resultMap.get("code");
            if (code==200){
                String error = (String) resultMap.get("error");
                return new LockResult(false, "添加失败,房间"+error+"重复添加",200,"");
            }else{
                return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }

}

