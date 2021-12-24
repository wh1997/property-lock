package com.tianjian.property.management.service.impl;

import com.tianjian.property.dao.DoorDao;
import com.tianjian.property.dao.LockDao;
import com.tianjian.property.management.service.GatewayService;
import com.tianjian.property.management.service.SynService;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.error.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/12/16
 */
@Service
public class SynServiceImpl implements SynService {
    @Autowired
    private LockDao lockDao;
    @Autowired
    private GatewayService gatewayService;
    //删除钥匙
    @Value("${apartment.synLock}")
    private  String synLock;
    @Override
    public LockResult lockMessage(Integer doorId) {
        String lockId = lockDao.selsetlockId(doorId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("lockId", lockId);
        map.put("delLockUserId", 900);
        map.put("lockKeyId", 1);
        Map result = gatewayService.bindinggateway(synLock, map);
        Integer resultCode = (Integer) result.get("resultCode");
        String reason = (String) result.get("reason");
        if (resultCode == 0) {
            return new LockResult(true, ErrorEnum.SUCCESS.getErrorMsg(), ErrorEnum.SUCCESS.getCode(), result);
        }else {
            return new LockResult(false, reason, ErrorEnum.SYSTEM_ERROR.getCode(), "");
        }
    }
}
