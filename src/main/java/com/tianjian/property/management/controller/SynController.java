package com.tianjian.property.management.controller;

import com.tianjian.property.bean.Fingerprint;
import com.tianjian.property.management.service.FingerprintService;
import com.tianjian.property.management.service.SynService;
import com.tianjian.property.utils.BeanChangeUtils;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.utils.error.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/12/16
 */
@RestController
@RequestMapping("/synchronization")
public class SynController {
    @Autowired
    private SynService synService;
    @PostMapping("/lock/message")
    public LockResult lockMessage(@RequestBody Map map, @RequestHeader String token){
        try {
            Integer doorId = (Integer) map.get("doorId");
            return synService.lockMessage(doorId);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.OPERATION_ERROR.getErrorMsg(), ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
}
