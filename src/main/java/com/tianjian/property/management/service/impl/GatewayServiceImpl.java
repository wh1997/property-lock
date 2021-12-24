package com.tianjian.property.management.service.impl;

import com.tianjian.property.bean.Gateway;
import com.tianjian.property.bean.Lock;
import com.tianjian.property.dao.DoorDao;
import com.tianjian.property.dao.GatewayDao;
import com.tianjian.property.dao.LockBaseInfoDao;
import com.tianjian.property.dao.LockDao;
import com.tianjian.property.management.service.GatewayService;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.error.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class GatewayServiceImpl extends HttpService implements GatewayService  {
    private static final Logger logger = LoggerFactory.getLogger(PhoneLoginServiceImpl.class);
    //门锁参数
    @Value("${apartment.appKey}")
    private  String appkey;
    //门锁参数
    @Value("${apartment.appSecret}")
    private  String appsecret;
    //门锁参数
    @Value("${apartment.url}")
    private  String apartmenturl;
    //获取token的请求
    @Value("${apartment.methodlogin}")
    private  String methodlogin;
    //绑定网关请求
    @Value("${apartment.gatewayBind}")
    private  String gatewayBind;
    //删除网关请求
    @Value("${apartment.DelGateway}")
    private  String DelGateway;
    //  #添加蓝牙锁请求
    @Value("${apartment.addBluetooth}")
    private  String addBluetooth;
    //蓝牙门锁绑定网关
    @Value("${apartment.bindingGateway}")
    private  String bindingGateway;
    //解除蓝牙门锁绑定网关
    @Value("${apartment.deleteGateway}")
    private  String deleteGateway;
    //解除蓝牙门锁绑定网关
    @Value("${apartment.getGatewayInfo}")
    private  String getGatewayInfo;
    @Autowired
    private LockDao lockDao;
    @Autowired
    private DoorDao doorDao;
    @Autowired
    private GatewayDao gatewayDao;
    @Autowired
    private LockBaseInfoDao lockBaseInfoDao;
    @Autowired
    private RedisTemplate redisTemplate;
    //绑定网关
    public  String getApartment(){
        String token = (String) redisTemplate.opsForValue().get("HXJtokenId");
        if (token!=null){
            return token;
        }
        //存储请求体
        HashMap<String, Object> map = new HashMap<>();
        //存储请求体中的data字段
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("appKey",appkey);
        map2.put("appSecret",appsecret);
        map.put("method",methodlogin);
        map.put("data",map2);
        Map result = (Map) postResult(apartmenturl, map);
        Map<String,String> data = (Map<String,String>)result.get("data");
        String tokenId = data.get("tokenId");
        logger.info("获取到的门锁API token:"+tokenId);
        redisTemplate.opsForValue().set("HXJtokenId",tokenId,1, TimeUnit.HOURS);
        return tokenId;
    }
    // 接口公共类
    @Override
    public  Map  bindinggateway (String method, Map<String,Object> data) {
        String tokenid = getApartment();
        HashMap<String, Object> map = new HashMap<>();
        map.put("method", method);
        map.put("data", data);
        map.put("tokenId", tokenid);
        Map result = (Map) postResult(apartmenturl, map);
        String reason= (String) result.get("reason");
        if ("用户已经过期".equals(reason)){
           redisTemplate.delete("HXJtokenId");
        }
        return result;
    }

    @Override
    @Transactional
    public LockResult deleteGateway(String gatewayId) {
        HashMap<String, Object> datamap = new HashMap<>();
        datamap.put("gatewayId",gatewayId);
        Map resutl = bindinggateway(DelGateway, datamap);
        int resultCode = (int) resutl.get("resultCode");
        if (resultCode==0){
            Integer id=gatewayDao.setectByGatewayId(gatewayId);
            int i = gatewayDao.updateByGatewayId(gatewayId);
            int o = lockDao.updateByGatewayId(id);
            return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(),ErrorEnum.SUCCESS.getCode(),"");
        }else {
            return new LockResult(false,"删除失败",ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }

    /**
    * @Description: 绑定网关
    * @Param:  
    * @return:  
    * @Date: 2021/6/18 
    */
    @Override
    @Transactional
    public Map gatewayBind(Integer project,String gatewaySeq) throws InterruptedException {
        HashMap<String, Object> datamap = new HashMap<>();
        final long l = System.currentTimeMillis()/1000;
        datamap.put("gatewaySeq",gatewaySeq);
        datamap.put("setUpTime",l);
        Map bindinggateway ;
        Date statrdate = new Date();
        long statrtime = statrdate.getTime();
        while (true){
        bindinggateway = bindinggateway(gatewayBind, datamap);
        Integer resultCode = (Integer) bindinggateway.get("resultCode");
            if (resultCode==0){
                break;
            }
            Thread.sleep(2000);
            Date stopdate = new Date();
            long stoptime = stopdate.getTime();
            if (stoptime-statrtime>1000*20){
                return bindinggateway;
            }
        }
        Map<String,Object> data = (Map<String, Object>) bindinggateway.get("data");
        Map<String,Object> gatewayInfoMap = (Map<String, Object>) data.get("gatewayInfo");
        //网关id
        String gatewayId = (String) gatewayInfoMap.get("gatewayId");
        //网关MAC
        String gatewayMac = (String) gatewayInfoMap.get("gatewayMac");
        //硬件版本号
        String hardwareVersion = (String) gatewayInfoMap.get("hardwareVersion");
        //软件版本
        String softwareVersion = (String) gatewayInfoMap.get("softwareVersion");
        //网关名称
        String gatewayName = (String) gatewayInfoMap.get("gatewayName");
        //网关类型
        Integer gatewayType = (Integer) gatewayInfoMap.get("gatewayType");
        //网关状态3在线 4离线
        Integer state = (Integer) gatewayInfoMap.get("state");
   /*     //网关ip
        String gatewayIp = (String) gatewayInfoMap.get("gatewayIp");
        //更新时间
        String updateTime = (String) gatewayInfoMap.get("updateTime");*/
        Gateway gateway = new Gateway(null, gatewayId, gatewaySeq, gatewayName, gatewayMac, gatewayType, hardwareVersion, softwareVersion, project, "慧享佳",null,null, state,null);
        //查询该网关是否绑定
        Gateway select = gatewayDao.selectbyGatewaySeq(gatewaySeq);
        if(select==null){
            gatewayDao.inster(gateway);
        }else{
            //更改网关的gatewayId参数(gatewayId会随网关的再次绑定二更改)
            gateway.setId(select.getId());
            gatewayDao.updateByPrimaryKeySelective(gateway);
        }
        return bindinggateway;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map LockBindingGateway(String lockId, String gatewayId, Integer doorID, Integer gateway, Integer lock) {
        Lock lock2 = lockDao.selectByDoorid(doorID);
        Integer lockGatewayId = lock2.getLockGatewayId();
        if (lockGatewayId==-1){
        HashMap<String, Object> datamap = new HashMap<>();
        //	是	string 门锁id
        datamap.put("lockId",lockId);
        //是	string	 网关id
        datamap.put("gatewayId",gatewayId);
        //发送请求
        Map bindinggateway = bindinggateway(bindingGateway, datamap);
        Integer resultCode = (Integer) bindinggateway.get("resultCode");
            if(resultCode==0){
                Lock lock1 = new Lock(null, doorID, 0, lock, gateway, 0, null, null, null);
                lock1.setDoorId(doorID);
                lock1.setLockGatewayId(gateway);
                //添加锁信息
                lockDao.updateByLockToGateway(doorID,lock,gateway);
                return bindinggateway;
            }
        return bindinggateway;
        }else {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("resultCode",10);
            hashMap.put("reason","重复绑定");
            return hashMap;
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map LockUnBindingGateway(String lockId, Integer lock, Integer bluetoothLockId) {
        HashMap<String, Object> datamap = new HashMap<>();
        //是	string 门锁id
        datamap.put("lockId",lockId);
        //发送请求
        Map  result= bindinggateway(deleteGateway, datamap);
        Integer resultCode = (Integer) result.get("resultCode");
        if(resultCode==0){
            //修改绑定网关的状态为未绑定网关
            lockDao.updateGatewayId(lock);
            //修改蓝牙锁状态
            lockBaseInfoDao.updateStatus(bluetoothLockId,1);
            return result;
        }
        return result;
    }

    @Override
    @Transactional
    public List<Lock> updateStatus(Integer id, Integer status) {
        //查看是否有关联的蓝牙门锁
        List<Lock> lock= lockDao.selectByGatewayId(id);
        if (lock.size()>0){
            //有蓝牙锁提示先删除蓝牙锁
            return lock;
        }else {
            //修改与网关的状态
            gatewayDao.updateStatus(id,status);
            return lock;
        }
    }
    @Override
    public Map selectGateway(String gatewayId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("gatewayId",gatewayId);
        Map retulsMap= bindinggateway(getGatewayInfo, map);
        Integer resultCode = (Integer) retulsMap.get("resultCode");
        if (resultCode==0){
            Map data = (Map) retulsMap.get("data");
            Map gatewayInfo = (Map) data.get("gatewayInfo");
            return gatewayInfo;
        }else {
            return null;
        }
    }

}
