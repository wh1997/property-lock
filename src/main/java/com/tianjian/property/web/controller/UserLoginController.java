package com.tianjian.property.web.controller;

import com.tianjian.property.bean.Auth;
import com.tianjian.property.bean.UserRole;
import com.tianjian.property.config.RequestResult;
import com.tianjian.property.utils.BeanChangeUtils;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.RSAUtils;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.utils.error.ErrorEnum;
import com.tianjian.property.web.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.KeyPair;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/12
 */
@Slf4j
@RestController
@RequestMapping("/web/user")
public class UserLoginController {
    private  static String USER_KEY="userKey_";
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private RedisTemplate redisTemplate;

    /** 
    * @Description: 登录接口 
    * @Param:  
    * @return:  
    * @Date: 2021/11/18 
    */
    @PostMapping("/login")
    public LockResult login(@RequestBody Map map) throws Exception {
        try {
            String phone = (String) map.get("phone");
            String Password = (String) map.get("password");
            // 获取私钥(暂时固定密码)
            String privateKey = (String) redisTemplate.opsForValue().get(USER_KEY + phone);
            // 解析密码
            String password = new String(RSAUtils.decryptByPrivateKey(Password, privateKey));
            LockResult login = userLoginService.login(phone, password);
            return  login;
        }catch (Exception e){
            return new LockResult(false,"登录失败,请重新登录", ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
    
    /** 
    * @Description: 用户添加角色
    * @Param:  
    * @return:  
    * @Date: 2021/11/18 
    */
    @PostMapping("/add/role")
    public LockResult addRole(@RequestHeader String token, @RequestBody Map map)  {
        try {
            Integer appUID = TokenUtil.getAppUID(token);
            List<Integer> roleId = (List<Integer>) map.get("roleId");
            Integer userId = (Integer) map.get("userId");
            int i =userLoginService.addRole(appUID,roleId,userId);
            if (i>0){
                return new LockResult(true,"添加成功", ErrorEnum.SUCCESS.getCode(),"");
            }else if (i==-1){
                return new LockResult(false,"添加失败，该角色已存在", ErrorEnum.OPERATION_ERROR.getCode(),"");
            }else {
                return new LockResult(false,"添加失败", ErrorEnum.OPERATION_ERROR.getCode(),"");
            }
        }catch (Exception e){
            return new LockResult(false,"添加失败", ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
    /**
    * @Description: 删除用户角色
    * @Param:
    * @return:
    * @Date: 2021/11/18
    */
    @PostMapping("/delete/role")
    public LockResult deleteRole(@RequestHeader String token, @RequestBody Map map)  {
        try {
            List<Integer> urId = (List<Integer>) map.get("urId");
            int i =userLoginService.deleteRole(urId);
            if (i>0){
                return new LockResult(true,"删除成功", ErrorEnum.SUCCESS.getCode(),"");
            }else {
                return new LockResult(false,"删除失败", ErrorEnum.OPERATION_ERROR.getCode(),"");
            }
        }catch (Exception e){
            return new LockResult(false,"删除失败", ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
    /**
    * @Description: 查询用户的所有角色
    * @Param:
    * @return:
    * @Date: 2021/11/18
    */
    @PostMapping("/select/role")
    public LockResult selectRole(@RequestHeader String token, @RequestBody Map map)  {
        try {
            Integer userId = (Integer) map.get("userId");
            List<Map> result =userLoginService.selectRole(userId);
            if (result!=null){
                return new LockResult(true,"查询成功", ErrorEnum.SUCCESS.getCode(),result);
            }else {
                return new LockResult(true,"查询成功，没有角色", ErrorEnum.SUCCESS.getCode(),"");
            }
        }catch (Exception e){
            return new LockResult(false,"查询失败", ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
   /* *//**
    * @Description: 角色添加权限
    * @Param:
    * @return:
    * @Date: 2021/11/18
    *//*
    @PostMapping("/add/right")
    public LockResult addRight(@RequestHeader String token, @RequestBody Map map)  {
        try {
            Integer appUID = TokenUtil.getAppUID(token);
            Integer roleId = (Integer) map.get("roleId");
            List<Integer> resourcesId = (List<Integer>) map.get("resourcesId");
            int i=userLoginService.addRight(appUID,roleId,resourcesId);
            if (i>0){
                return new LockResult(true,"添加成功", ErrorEnum.SUCCESS.getCode(),"");
            }else {
                return new LockResult(false,"添加失败", ErrorEnum.OPERATION_ERROR.getCode(),"");
            }
        }catch (Exception e){
            return new LockResult(false,"添加失败", ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }*/
    /**
    * @Description: 角色删除权限
    * @Param:
    * @return:
    * @Date: 2021/11/18
    */
    @PostMapping("/delete/right")
    public LockResult deleteRight(@RequestHeader String token, @RequestBody Map map)  {
        try {
            List<Integer> aId = (List<Integer>) map.get("aId");
            String type = (String) map.get("type");
            int i=userLoginService.deleteRight(aId,type);
            if (i>0){
                return new LockResult(true,"删除成功", ErrorEnum.SUCCESS.getCode(),"");
            }else {
                return new LockResult(false,"删除失败", ErrorEnum.OPERATION_ERROR.getCode(),"");
            }
        }catch (Exception e){
            return new LockResult(false,"删除失败", ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
    /**
    * @Description: 角色查询权限
    * @Param:
    * @return:
    * @Date: 2021/11/18
    */
    @PostMapping("/select/property/right")
    public LockResult propertyRight(@RequestHeader String token, @RequestBody Map map)  {
        try {
            List<Integer> roleId = (List) map.get("roleId");
            List<Map> result=userLoginService.propertyRight(roleId);
            if (result!=null){
                return new LockResult(true,"查询成功", ErrorEnum.SUCCESS.getCode(),result);
            }else {
                return new LockResult(true,"查询成功,没有数据", ErrorEnum.SUCCESS.getCode(),"");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new LockResult(false,"查询失败", ErrorEnum.OPERATION_ERROR.getCode(),"");
        }
    }
    @GetMapping(value = "/getloginkey/{phone}")
    public LockResult getLoginPublicKey(@PathVariable(required = false) String phone) throws Exception {
        // 数据转换
    /*    if(null == userName){
            log.error("没有输入用户名");
            throw new BusinessException(ErrorEnum.PARAMETER_NONE_ERROR, "请输入用户名");
        }*/
        // 初始化秘钥
        KeyPair key = RSAUtils.initKey();
        // 私钥存入缓存
        redisTemplate.opsForValue().set(USER_KEY + phone, RSAUtils.getPrivateKey(key));
        // 设置缓存时间十分钟
        redisTemplate.expire(USER_KEY + phone, 60 * 10, TimeUnit.SECONDS);
        // 公钥返回前端
        return new LockResult(true,"获取成功",0,RSAUtils.getPublicKey(key));
    }
}
