package com.tianjian.property.management.service;

import com.tianjian.property.utils.LockResult;

import java.util.HashMap;

public interface PhoneLoginService {
    //微信授权登陆
    LockResult wechatLogin(String encryptedData, String iv, String code) throws Exception;

    boolean wechatLogOut(String token);
}
