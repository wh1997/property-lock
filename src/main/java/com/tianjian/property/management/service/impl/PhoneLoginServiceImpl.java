package com.tianjian.property.management.service.impl;

import com.tianjian.property.bean.User;
import com.tianjian.property.dao.RoleDao;
import com.tianjian.property.dao.UserDao;
import com.tianjian.property.management.service.PhoneLoginService;
import com.tianjian.property.utils.HttpUtils;
import com.tianjian.property.utils.LockConstants;
import com.tianjian.property.utils.LockResult;
import com.tianjian.property.utils.TokenUtil;
import com.tianjian.property.utils.error.ErrorEnum;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class PhoneLoginServiceImpl extends HttpService implements PhoneLoginService {
    private static final Logger logger = LoggerFactory.getLogger(PhoneLoginServiceImpl.class);
    @Value("${baiwei.WXurl}")
    private String WXurl;
    @Value("${lock.appid}")
    private String appid;
    @Value("${baiwei.url}")
    private String url;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    //进行微信授权登陆
    @Override
    public LockResult wechatLogin(String encryptedData, String iv, String code) throws Exception {
        //存储返回结果
        HashMap<String, Object> resultMap = new HashMap<>();
        Map<String, String> phoneMap = getPhone(encryptedData, iv, code);
        String phone = phoneMap.get("phone");
        String token = phoneMap.get("token");
        HashMap<String, String> map = new HashMap<>();
        map.put("Identity",phone);
        map.put("Token",token);
        //判断用户是否存在 如果是204则表示用户已存在如果是404则表示用户不存在需要注册
        Integer status = HttpUtils.doGetExists(url + "/Security/Users/Exists/*:"+phone);
        if (404==status){
            return new LockResult(false,"登录失败,请到管理处登记", ErrorEnum.REGISTER_ERROR.getCode(),phone);
        }
        //登录接口
        Map hashMap = (Map) postResult(url + "/Security/Authentication/Signin/wechat?scenario=Mobile" + token, map);
        Map<String,Object> identity = (Map<String, Object>) hashMap.get("Identity");
        if(null ==identity){
            return new LockResult(false,"登录失败", ErrorEnum.REGISTER_ERROR.getCode(),phone);
        }
        Integer userId = (Integer) identity.get("UserId");
        String FullName = (String) identity.get("FullName");
        String Phone = (String) identity.get("Phone");
        Integer BranchId = (Integer) identity.get("BranchId");
        if (BranchId==null){
            return new LockResult(false,"登录失败", ErrorEnum.REGISTER_ERROR.getCode(),phone);
        }
        Map roletype= null;
        //判断角色表有没有添加没有添加进行添加
        roletype=userDao.selectByUserId(userId);
        HashMap<String, Object> roleMap = new HashMap<>();
        if (roletype==null){
            User newrole = new User();
            newrole.setUserId(userId);
            newrole.setRole(2);
            newrole.setName(FullName);
            newrole.setPhone(Phone);
            newrole.setBranchId(BranchId);
            //往用户表里面添加数据
            int i = userDao.insert(newrole);
            //如果没有添加角色后返回
            roleMap.put("role",2);
            //roleMap.put("Property_id",0);
        }else{
            roleMap.put("role",roletype.get("role"));
        }
        String userToken = TokenUtil.createToken(userId);
        redisTemplate.opsForValue().set(LockConstants.USER_TOKEN+userToken,hashMap,1, TimeUnit.DAYS);
        logger.info("用户的token为"+userToken);
        HashMap<String, Object> datemap = new HashMap<>();
        datemap.put("role",roleMap.get("role"));
        datemap.put("token",userToken);
        datemap.put("datemap",hashMap);
        resultMap.put("code",200);
        resultMap.put("errorMessage","登录成功");
        resultMap.put("date",datemap);
        return new LockResult(true,"登录成功", ErrorEnum.SUCCESS.getCode(),resultMap);
    }

    @Override
    public boolean wechatLogOut(String token) {
        return redisTemplate.delete(LockConstants.USER_TOKEN + token);
    }

    //获取手机号以及注册登陆要用到的token参数(百为)
    public Map<String,String> getPhone(String encryptedData,String iv,String code)   {
        String encryptedDataA = encryptedData.replaceAll(" ", "+");
        //获取sessionKey参数
        Map resultMap = (Map) getResult(WXurl + "/wx/auth/" + appid + "/code2Session/" + code);
        String sessionKey = (String) resultMap.get("sessionKey");
        String encodeEncryptedData = encodeURL(encryptedDataA);
        String encodeSessionKey = encodeURL(sessionKey);
        String encodeIv = encodeURL(iv);
        String url=WXurl + "/wx/auth/" + appid + "/phone?sessionKey="+encodeSessionKey+"&encryptedData="+encodeEncryptedData+"&iv="+encodeIv;
        Map jsonMap = (Map) getResult(url + code);
        //获取手机号
        String phone = (String) jsonMap.get("purePhoneNumber");
        //获取token值
        String token =(String) jsonMap.get("token");
        HashMap<String, String> resultMap1 = new HashMap<>();
        resultMap1.put("phone",phone);
        resultMap1.put("token",token);
        logger.info("登录手机号:"+phone+"  它的(百为)token为"+token);
        return resultMap1;
    }
    public static String encodeURL(String url){
        return url.replace("%", "%25").replace("+", "%2B")
                .replace(" ", "%20").replace("/", "%2F")
                .replace("?", "%3F").replace("#", "%23")
                .replace("&", "%26").replace("=", "%3D")
                .replace(":", "%3A");
    }

}
