package com.tianjian.property.management.controller;

import com.tianjian.property.bean.Door;
import com.tianjian.property.management.service.CommonDoorService;
import com.tianjian.property.utils.BeanChangeUtils;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.PageResult;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.utils.error.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/** 
* @Description: 公共门接口类
* @Param:  
* @return:  
* @Date: 2021/5/27s
*/
@RestController
@RequestMapping("/common/door")
public class CommonDoorController {
    @Autowired
    private CommonDoorService commonDoorService;
    /**
    * @Description: 添加公共门接口
    * @Param: [door]
    * @return: com.tagen.lock.utils.LockResult 
    * @Date: 2021/5/27
    */
    @PostMapping("/add")
    public LockResult addCommonDoor(@RequestBody Map doorMap , @RequestHeader String token){
        try {
            Integer appUID = TokenUtil.getAppUID(token);
            Door door = BeanChangeUtils.mapToBean(doorMap, Door.class);
            door.setCreatePerson(appUID.toString());
            int i= commonDoorService.addCommonDoor(door);
            if (i==200) {
                return new LockResult(false, "已添加请勿重复添加", 200, null);
            }else {
                return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
  /*  @PostMapping("/selectdoor")TODO 弃用与重复接口
    *//**
    * @Description:  分页选项卡搜索
    * @Param: [cityd 市id, areaid  区id, propertyid  小区id, doortype  门锁类型]
    * @return: com.tagen.lock.utils.LockResult
    * @Date: 2021/5/27
    *//*
    public LockResult showCommonDoor(@RequestBody Map map){
      try {
          Integer propertyid = (Integer) map.get("propertyId");
          Integer doortype = (Integer) map.get("doorType");
          Integer pageNum = (Integer) map.get("pageNum");
          Integer pageSize = (Integer) map.get("pageSize");
          List<Map<String,List<Door>>> result= commonDoorService.selectCommonDoor(propertyid,doortype,pageNum,pageSize);
          if (result != null){
              return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),result);
          }else{
              return new LockResult(true,"查询成功,没有数据",ErrorEnum.SUCCESS.getCode(),null);
          }
      }catch (Exception e){
          e.printStackTrace();
          return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
      }
    }*/
    @PostMapping("/fuzzyquery")
    /** 
    * @Description:  分页模糊搜索公共门
    * @Param:  
    * @return:  
    * @Date: 2021/6/16 
    */
    public LockResult fuzzyQueryCommonDoor(@RequestBody Map map){
        try{
            String doorName = (String) map.get("doorName");//门名称
            Integer doorType = (Integer) map.get("doortype");//门类型
            Integer pageNum = (Integer) map.get("pageNum");//门类型
            Integer pageSize = (Integer) map.get("pageSize");//门类型
            Integer propertyId = (Integer) map.get("propertyId");//门类型
            PageResult<Door> result=commonDoorService.fuzzyQueryCommonDoor(doorName,doorType, pageNum, pageSize,propertyId);
            if (result!=null){
                return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),result);
            }else {
                return new LockResult(true,"查询成功,没有数据",ErrorEnum.SUCCESS.getCode(),null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
    @PostMapping("/doortype")
      /**
      * @Description:查询门类型
      * @Param: [Doorid]
      * @return: com.tagen.lock.utils.LockResult
      * @Date: 2021/6/16
      */
    public LockResult doorParticulars (){
        try{
            List<Map> list= commonDoorService.selectDoorType();
            if (list!=null){
                return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),list);
            }else {
                return new LockResult(true,"查询成功,没有数据",ErrorEnum.SUCCESS.getCode(),null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
}
