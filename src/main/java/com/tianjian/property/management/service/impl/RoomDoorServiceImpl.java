package com.tianjian.property.management.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianjian.property.bean.Door;
import com.tianjian.property.bean.Lock;
import com.tianjian.property.bean.LockBaseInfo;
import com.tianjian.property.bean.vo.AddDoorVo;
import com.tianjian.property.bean.vo.DoorVo;
import com.tianjian.property.bean.vo.RoomVo;
import com.tianjian.property.dao.*;
import com.tianjian.property.management.service.RoomDoorService;
import com.tianjian.property.utils.BeanChangeUtils;
import com.tianjian.property.utils.PageResult;
import com.tianjian.property.utils.error.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RoomDoorServiceImpl implements RoomDoorService {
    @Autowired
    private DoorDao doorDao;
    @Autowired
    private LockDao lockDao;
    @Autowired
    private NetworkCardDao networkCardDao;
    @Autowired
    private LockBaseInfoDao lockBaseInfoDao;
    @Autowired
    private GatewayDao gatewayDao;
    //根据小区搜索房间(房间门列表)
    @Override
    public PageResult<DoorVo> selsctAll(Door door,Integer pageNum, Integer pageSize) {
        //List<(几栋)Map<String,(几单元)Map<String,()房间信息list<door>>>>
        //查询小区下房间的具体信息
        PageHelper.startPage(pageNum,pageSize);
        List<DoorVo> all = doorDao.selectall(door);
        PageInfo<DoorVo> doorVoPageInfo = new PageInfo<>(all);
        List<DoorVo> Doorlist = doorVoPageInfo.getList();
        int pages = doorVoPageInfo.getPages();
        //总共多少条
        long total = doorVoPageInfo.getTotal();
        PageResult<DoorVo> doorVoPageResult = new PageResult<>(pageSize,pageNum,Doorlist,total,pages);
        return doorVoPageResult;
    }
    //根据房间门搜索房间
    @Override
    public Map<String, List<DoorVo>> selsctRoomnoAndPropertyname(Integer propertyid, String roomno,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DoorVo> all = doorDao.RoomnoAndPropertyname( propertyid, roomno);
        PageInfo<DoorVo> doorVoPageInfo = new PageInfo<>(all);
        List<DoorVo> list = doorVoPageInfo.getList();
        if (all.size()>0){
            //查询公寓门有哪些楼栋
            List<String> bulidingnameList =doorDao.selectbuildingid(propertyid,3,roomno);
            if(bulidingnameList.size()<=0){
                return null;
            }else {
                ArrayList<String> siteList = new ArrayList<>();
                for (int j = 0; j <bulidingnameList.size() ; j++) {
                    String bulidingname = bulidingnameList.get(j);
                    //查询公寓门有哪些单元
                    List<String> unitnameList = doorDao.selectunitname( propertyid,bulidingname,roomno);
                    for (int i = 0; i <unitnameList.size() ; i++) {
                        String unitname = unitnameList.get(i);
                        siteList.add(bulidingname+"-"+unitname);
                    }
                }
                Map<String, List<DoorVo>> doorVoMap = new LinkedHashMap<>();
                for (int k = 0; k <siteList.size() ; k++) {
                    String site = siteList.get(k);
                    ArrayList<DoorVo> doorVos = new ArrayList<>();
                    for (int l = 0; l <list.size() ; l++) {
                        DoorVo doorVo = list.get(l);
                        String siteall= doorVo.getBuildingName()+"-"+doorVo.getUnitName();
                        if (site.equals(siteall)){
                            doorVos.add(doorVo);
                        }
                    }
                    doorVoMap.put(site,doorVos);
                }
                return doorVoMap;
            }
        }else {
            return null;
        }
    }
    @Override
    /**
    * @Description:  根据小区,楼栋,单元名称搜索房间(侧边栏搜索)
    * @Param: [cityid, areaid, propertyid, bulidingid, unitname]
    * @return: java.util.Map<java.lang.String,java.util.List<com.tagen.lock.bean.vo.DoorVo>>
    * @Date: 2021/5/31
    */
    public Map<String, List<DoorVo>> screenRoomDoor(Integer propertyid, Integer bulidingid, String unitname,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DoorVo> all = doorDao.screenRoomDoor(propertyid, bulidingid, unitname);
        PageInfo<DoorVo> doorVoPageInfo = new PageInfo<>(all);
        List<DoorVo> list = doorVoPageInfo.getList();
        if (all.size()>0){
            DoorVo doorVo = all.get(0);
            String s = doorVo.getBuildingName() + "-" + doorVo.getUnitName();
            Map<String, List<DoorVo>> map = new HashMap<>();
            map.put(s,list);
            return map;
        }else {
            return null;
        }

    }

    @Override
    
    /** 
    * @Description: 房间门锁详情
    * @Param: [doorid 门id]
    * @return: java.util.Map<java.lang.String,java.lang.String> 
    * @Date: 2021/5/31
    */
    public Map<String, Object> selectdoorparticulars(Integer doorid)  {
        Lock lock = lockDao.selectByDoorid(doorid);
        if (lock!=null){
            //获取门锁设备类型  0网关  1网卡
            Integer facilitytype = lock.getFacilityType();
            //获取到设备id
            Integer lockfacilityid = lock.getLockFacilityId();
            Map<String, Object> map= new HashMap<>();
            Map<String, Object> doorMap = doorDao.selectDoor(doorid);
            map.putAll(doorMap);
            if(1==facilitytype){
                    //查询网卡设备表中的网卡设备信息
                map = networkCardDao.selectById(lockfacilityid);
            }else{
                //根据设备id查门锁lock_mac信息
                LockBaseInfo lockBaseInfo = lockBaseInfoDao.selectByPrimaryKey(lockfacilityid);
                Map lockBaseInfoMap = JSON.parseObject(JSON.toJSONString(lockBaseInfo), Map.class);
                //获取到网关设备id
                Integer lockgatewayid = lock.getLockGatewayId();
                if (lockgatewayid==-1){
                    map.put("lockGatewayId",-1);
                }
                Map<String,Object> gatewayMap = gatewayDao.findById(lockgatewayid);
                if (gatewayMap!=null){
                    map.putAll(gatewayMap);
                }
                //返回门锁的Mac信息
                map.putAll(lockBaseInfoMap);
                //锁表的id
                map.put("lId",lock.getId());
            }
            return map;
        }else {
            return null;
        }
    }

    @Override
    public List<Door> fuzzySearch(Integer propertyid,String fuzzy, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List <Door> doorList=doorDao.fuzzySearch(propertyid,"%"+fuzzy+"%");
        PageInfo<Door> doorPageInfo = new PageInfo<>(doorList);
        List<Door> list = doorPageInfo.getList();
        return list;
    }

    @Override
    @Transactional
    public Map addDoor(List<Map> door,Integer appUID) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        List<Door> doors = new ArrayList<>();
        for (int i = 0; i< door.size(); i++) {
            Map doorMap =door.get(i);
            Door bean = BeanChangeUtils.mapToBean(doorMap, Door.class);
            bean.setCreatePerson(appUID.toString());
            AddDoorVo addDoor = BeanChangeUtils.mapToBean(doorMap, AddDoorVo.class);
            Door door1 = BeanChangeUtils.beanChange(addDoor, Door.class);
            Door selectOne = doorDao.selectOne(door1);
            if (selectOne!=null){
                map.put("code",200);
                map.put("error",bean.getNumName()+bean.getBuildingName()+bean.getUnitName()+bean.getRoomNo()+"添加重复");
                return map;
            }
            doors.add(bean);
        }
        doorDao.addDoor(doors);
        map.put("code", ErrorEnum.SUCCESS.getCode());
        map.put("error","成功");
        return  map;
    }

    @Override
    public List<Integer> selectRoom(List<Integer> roomVos) {
       List<Integer> doors= doorDao.selectRoom(roomVos);
       if (doors.size()==0){
           return null;
       }
        return doors;
    }

}
