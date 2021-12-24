package com.tianjian.property.management.controller;

import com.tianjian.property.management.service.PropertyService;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.utils.error.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description: 项目
 * @author: ManolinCoder
 * @time: 2021/7/2
 */
@RestController
@RequestMapping("/property")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
    @PostMapping("/select/all")
    
    /** 
    * @Description: 获取所有园区信息
    * @Param: [map] 
    * @return: com.tianjian.Property.utils.LockResult
    * @Date: 2021/7/5 
    */
    public LockResult getProperty(@RequestHeader String token){
        try {
            Integer appUID = TokenUtil.getAppUID(token);
            List resultList = propertyService.getProperty(appUID);
            return new LockResult(true,"成功",ErrorEnum.SUCCESS.getCode(),resultList);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }
    }
}
