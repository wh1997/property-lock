package com.tianjian.property.management.service.impl;

import com.tianjian.property.bean.Fingerprint;
import com.tianjian.property.dao.FingerprintDao;
import com.tianjian.property.dao.LockDao;
import com.tianjian.property.management.service.FingerprintService;
import com.tianjian.property.management.service.GatewayService;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.error.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/12/7
 */
@Service
public class FingerprintServiceImpl implements FingerprintService {
    @Autowired
    private FingerprintDao fingerprintDao;
    @Autowired
    private GatewayService gatewayService;
    @Autowired
    private LockDao lockDao;
    //删除钥匙
    @Value("${apartment.DelKey}")
    private  String DelKey;
    @Override
    @Transactional
    public LockResult addFingerprint(Fingerprint fingerprint) {
        Integer doorId = fingerprint.getDoorId();
        Integer addPerson = fingerprint.getAddPerson();
        List<Map> list= fingerprintDao.selectByuser(doorId,addPerson);
        if (list.size()>=2){
            return new LockResult(false,"超过允许添加的指纹数", ErrorEnum.OPERATION_ERROR.getCode(),"");
        }else {
            int i = fingerprintDao.insertSelective(fingerprint);
            if (i>0){
                return new LockResult(true,ErrorEnum.SUCCESS.getErrorMsg(), ErrorEnum.SUCCESS.getCode(),"");
            }else {
                return new LockResult(false,ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(),"");
            }
        }
    }

    @Override
    public LockResult deleteFingerprint(Integer doorId,Integer lockKeyId ) {
        String lockId = lockDao.selsetlockId(doorId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("lockId", lockId);
        map.put("delLockUserId", 900);
        map.put("deleteType", 2);
        map.put("lockKeyId", lockKeyId);
        map.put("keyType", 1);
        Map result = gatewayService.bindinggateway(DelKey, map);
        Integer resultCode = (Integer) result.get("resultCode");
        String reason = (String) result.get("reason");
        if (resultCode == 0) {
            int i = fingerprintDao.updateStatus(doorId,lockKeyId);
            if (i > 0) {
                return new LockResult(true, "删除成功", ErrorEnum.SUCCESS.getCode(), "");
            } else {
                return new LockResult(false, "删除失败", ErrorEnum.SYSTEM_ERROR.getCode(), "");
            }
        }else {
            return new LockResult(false, reason, ErrorEnum.SYSTEM_ERROR.getCode(), "");
        }
    }

    @Override
    public List<Map> selectFingerprint(Integer doorId,Integer appUID) {
        List<Map> list= fingerprintDao.selectByuser(doorId,appUID);
        return list;
    }
}
