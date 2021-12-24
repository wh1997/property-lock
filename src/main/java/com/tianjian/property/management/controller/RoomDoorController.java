package com.tianjian.property.management.controller;

import com.tianjian.property.bean.Door;
import com.tianjian.property.bean.vo.DoorVo;
import com.tianjian.property.bean.vo.RoomVo;
import com.tianjian.property.management.service.RoomDoorService;
import com.tianjian.property.utils.*;
import com.tianjian.property.utils.error.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
* @Description:房间门接口
* @Param:
* @return:
* @Date: 2021/5/27
*/
@RestController
@RequestMapping("/room/door")
public class RoomDoorController {
    @Autowired
    private RoomDoorService roomDoorService;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
    * @Description:根据小区搜索房间(房间门列表)
    * @Param: [cityid 城市id, areaid  区域id, propertyid  小区id]
    * @return: com.tagen.lock.utils.LockResult
    * @Date: 2021/5/25
    */
    @PostMapping("/all")
    public LockResult roomDoorList(@RequestBody Map map){
        try {
            Door door = BeanChangeUtils.mapToBean(map, Door.class);
            Integer pageNum = (Integer) map.get("pageNum");
            Integer pageSize = (Integer) map.get("pageSize");
            PageResult<DoorVo> doors = roomDoorService.selsctAll(door, pageNum, pageSize);
            if (doors==null){
                return new LockResult(true,"获取成功,没有数据", ErrorEnum.SUCCESS.getCode(),null);
            }else {
                return new LockResult(true,"获取成功",ErrorEnum.SUCCESS.getCode(),doors);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
    @PostMapping("/Search")
    /**
    * @Description:  关键字搜素
    * @Param: [map]
    * @return: com.tianjian.Property.utils.LockResult
    * @Date: 2021/7/6
    */
    public  LockResult fuzzySearch(@RequestBody Map map){
        try {
            //项目id
            Integer propertyid = (Integer) map.get("propertyId");
            //搜索关键字
            String fuzzy = (String) map.get("fuzzy");
            Integer pageNum = (Integer) map.get("pageNum");
            Integer pageSize = (Integer) map.get("pageSize");
            List<Door> list= roomDoorService.fuzzySearch(propertyid,fuzzy,pageNum,pageSize);
            if (list!=null){
                return new LockResult(true,ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),list);
            }
            return new LockResult(true,"获取成功,没有数据",ErrorEnum.SUCCESS.getCode(),null);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
    @PostMapping("/particulars")
    /**
    * @Description:查询门锁详情
    * @Param: [doorid  门id]
    * @return: com.tagen.lock.utils.LockResult
    * @Date: 2021/5/31
    */
    public LockResult roomParticulars(@RequestBody Map map){
        try {
            Integer doorid = (Integer) map.get("doorId");
            Map<String,Object>  resultmap=roomDoorService.selectdoorparticulars(doorid);
            if(resultmap!=null){
                return new LockResult(true,"获取成功",ErrorEnum.SUCCESS.getCode(),resultmap);
            }else {
                return new LockResult(true,"获取成功,没有数据",ErrorEnum.SUCCESS.getCode(),null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
    //添加房间
   @PostMapping("/add")
    public LockResult addDoor(@RequestBody Map map, @RequestHeader String token)  {
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
    //筛选已添加的房间
   @PostMapping("/select/room")
    public LockResult selectRoom(@RequestBody Map map, @RequestHeader String token)  {
        try {
            List<Integer> list1 = (List<Integer>) map.get("list");
            List<Integer> list=roomDoorService.selectRoom(list1);
            return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),list);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
   }
}
