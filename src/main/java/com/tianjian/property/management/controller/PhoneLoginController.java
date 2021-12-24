package com.tianjian.property.management.controller;

import com.tianjian.property.bean.vo.WechatVo;
import com.tianjian.property.management.service.PhoneLoginService;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.error.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/** 
* @Description: 登录接口
* @Param:  
* @Date: 2021/5/27
*/
@RestController
@RequestMapping("/wechat")
public class PhoneLoginController {
    @Autowired
    private PhoneLoginService phoneLoginService;
    //微信授权登陆
    @PostMapping("/login")
    public LockResult wechatLogin(@RequestBody WechatVo WechatVo)   {
        try {
            //微信的三个参数
            String code = WechatVo.getCode();
            String iv = WechatVo.getIv();
            String encryptedData = WechatVo.getEncryptedData();
            return phoneLoginService.wechatLogin(encryptedData, iv, code);
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false, ErrorEnum.OPERATION_ERROR.getErrorMsg(),ErrorEnum.OPERATION_ERROR.getCode(),null);
        }

    }
    
    /** 
    * @Description: 退出登录
    * @Param:  
    * @return:  
    * @Date: 2021/6/9 
    */
    @PostMapping("/log/out")
    public LockResult wechatLogOut(@RequestHeader String token)   {
        try {
            boolean b = phoneLoginService.wechatLogOut(token);
            if (b){
                return new LockResult(true,"退出成功",ErrorEnum.SUCCESS.getCode(),"");
            }else {
                return new LockResult(false,"退出失败",ErrorEnum.OPERATION_ERROR.getCode(),"");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,ErrorEnum.SYSTEM_ERROR.getErrorMsg(),ErrorEnum.SYSTEM_ERROR.getCode(),null);
        }

    }
}
