package com.tianjian.property.management.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.tianjian.property.bean.Fingerprint;
import com.tianjian.property.management.service.FingerprintService;
import com.tianjian.property.utils.BeanChangeUtils;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.utils.error.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/12/7
 */
@RestController
@RequestMapping("/fingerprint")
public class FingerprintController {
    @Autowired
    private FingerprintService fingerprintService;
    @PostMapping("/add")
    public LockResult addFingerprint(@RequestBody Map map, @RequestHeader String token){
        try {
            Fingerprint fingerprint = BeanChangeUtils.mapToBean(map,Fingerprint.class);
            Integer appUID = TokenUtil.getAppUID(token);
            fingerprint.setAddPerson(appUID);
            return fingerprintService.addFingerprint(fingerprint);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
    @PostMapping("/delete")
    public LockResult deleteFingerprint(@RequestBody Map map, @RequestHeader String token){
        try {
            Integer doorId = (Integer) map.get("doorId");
            Integer lockKeyId = (Integer) map.get("lockKeyId");
            return fingerprintService.deleteFingerprint(doorId,lockKeyId);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(),"");
        }

    }
    @PostMapping("/select")
    public LockResult selectFingerprint(@RequestBody Map map, @RequestHeader String token){
        try {
            Integer doorId = (Integer) map.get("doorId");
            Integer userId = (Integer) map.get("userId");
            Integer appUID;
            if (userId==null){
                appUID = TokenUtil.getAppUID(token);
            } else if (userId==0){
                appUID= null;
            }else {
                appUID=userId;
            }
            List<Map> list = fingerprintService.selectFingerprint(doorId,appUID);
            if (list.size()==0){
                return new LockResult(false,"查询成功没有数据", ErrorEnum.SUCCESS.getCode(),"");
            }else {
                return new LockResult(true,ErrorEnum.SUCCESS.getErrorMsg(), ErrorEnum.SUCCESS.getCode(),list);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
}
